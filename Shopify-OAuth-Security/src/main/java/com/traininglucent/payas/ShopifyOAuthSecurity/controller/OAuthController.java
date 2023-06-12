package com.traininglucent.payas.ShopifyOAuthSecurity.controller;

import com.traininglucent.payas.ShopifyOAuthSecurity.io.entity.ShopifyAdminAccessTokenEntity;
import com.traininglucent.payas.ShopifyOAuthSecurity.model.request.ShopLoginRequestModel;
import com.traininglucent.payas.ShopifyOAuthSecurity.model.response.ShopLoginRest;
import com.traininglucent.payas.ShopifyOAuthSecurity.repository.ShopifyAdminAccessTokenRepo;
import com.traininglucent.payas.ShopifyOAuthSecurity.service.ShopifyAccessTokenService;
import com.traininglucent.payas.ShopifyOAuthSecurity.shared.Utils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class OAuthController {

    @Value("${shopify.payascustomapp.client.id}")
    private String clientId;

    @Value("${shopify.payascustomapp.client.secret}")
    private String clientSecret;
    @Value("${shopify.payascustomapp.scopes}")
    private String scopes;

    @Value("${shopify.oauth.callback.uri}")
    private String redirect_uri;

    @Value("${shopify.payascustomapp.nonce}")
    private String nonce;

    @Value("${shopify.payascustomapp.access_mode}")
    private String access_mode[];

    @Autowired
    Utils utils;

    @Autowired
    ShopifyAdminAccessTokenRepo shopifyAdminAccessTokenRepo;

    @Autowired
    ShopifyAccessTokenService accessTokenService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/")
    public RedirectView getVerifiedClient(@RequestParam Map<String, String> requestParams, HttpServletRequest request) throws NoSuchAlgorithmException, InvalidKeyException {
        String hmac = requestParams.get("hmac");
        requestParams.remove("hmac");

        Map<String, String> sortedMap = new TreeMap<>(requestParams);

        UriComponentsBuilder builder = UriComponentsBuilder.newInstance();

        sortedMap.forEach(builder::queryParam);

        String verifyUrl = builder.build().getQuery();

        String hexDigest = utils.generateHmac(verifyUrl, clientSecret);

        System.out.println(hmac +"  "+ hexDigest);
        String redirectUrl = "https://" + requestParams.get("shop") + "/admin/oauth/authorize?client_id=" + clientId + "&scope="
                + scopes + "&redirect_uri=" + redirect_uri + "&state=" + nonce + "&grant_options[]=" + access_mode.toString();

        if (!hexDigest.equals(hmac))
            return new RedirectView("https://www.shopify.com");

//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("Headers:\n");
//
//        Enumeration<String> headerNames = request.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
//            String headerName = headerNames.nextElement();
//            String headerValue = request.getHeader(headerName);
//            stringBuilder.append(headerName).append(": ").append(headerValue).append("\n");
//        }
//        System.out.println(stringBuilder.toString());

        return new RedirectView(redirectUrl);

    }

    @GetMapping("/api/auth")
    public RedirectView getAuthorization(@RequestParam Map<String, String> requestParams, HttpServletRequest request) throws NoSuchAlgorithmException, InvalidKeyException {

        String hmac = requestParams.get("hmac");
        requestParams.remove("hmac");

        Map<String, String> sortedMap = new TreeMap<>(requestParams);

        UriComponentsBuilder builder = UriComponentsBuilder.newInstance();

        sortedMap.forEach(builder::queryParam);

        String verifyUrl = builder.build().getQuery();

        String hexDigest = utils.generateHmac(verifyUrl, clientSecret);

        Pattern regexPattern = Pattern.compile("^[a-zA-Z0-9][a-zA-Z0-9\\-]*\\.myshopify\\.com$");

        Matcher matcher = regexPattern.matcher(requestParams.get("shop"));

        if(!hexDigest.equals(hmac) || !matcher.matches() || !requestParams.get("state").equals(nonce))
            return new RedirectView("https://www.shopify.com");

        WebClient webClient = WebClient.create();
        Map<String, String> queryPar = new TreeMap<>();
        queryPar.put("client_id", clientId);
        queryPar.put("client_secret", clientSecret);
        queryPar.put("code", requestParams.get("code"));

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance();

        queryPar.forEach(uriComponentsBuilder::queryParam);

        String accessUrl = uriComponentsBuilder.build().getQuery();

        String url = "https://" + requestParams.get("shop") + "/admin/oauth/access_token?" + accessUrl;

        HashMap<String, String> token = webClient.post()
                        .uri(url)
                        .retrieve()
                        .bodyToMono(HashMap.class).block();


        ShopifyAdminAccessTokenEntity user = new ShopifyAdminAccessTokenEntity();
        user.setShop(requestParams.get("shop"));
        user.setAccessToken(token.get("access_token"));
        user.setScopes(token.get("scope"));
        user.setEncryptedPassword(bCryptPasswordEncoder.encode(token.get("access_token")));

        ShopifyAdminAccessTokenEntity checkUser = shopifyAdminAccessTokenRepo.findByAccessToken(token.get("access_token"));

        if(checkUser == null)
        shopifyAdminAccessTokenRepo.save(user);

        return new RedirectView("https://admin.shopify.com/store/training-store-lucent/");
    }

    @PostMapping("/login")
    public ResponseEntity<ShopLoginRest> merchantLogin(@RequestBody ShopLoginRequestModel request){
        ShopLoginRest returnBody = accessTokenService.merchantLogin(request);

        return ResponseEntity.ok()
                .body(returnBody);
    }

}
