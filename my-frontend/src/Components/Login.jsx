import axios from "axios";
import React, { useCallback, useContext, useState } from "react";
import { useNavigate } from "react-router-dom";
import {Page, FormLayout, TextField, Button} from '@shopify/polaris';
import { toast } from "react-toastify";


const Login = (props) => {
  const navigate = useNavigate();

  const [loginDetail, setLoginDetail] = useState({
    shop: '',
    accessToken: '',
  });

  

//   const handleChange = (event) => {
//     const {name, value} = event.target;
//     setLoginDetail((prevData) => ({
//         ...prevData,
//         [name]: value,
//       }));
//     console.log(event.target)
//   };

  const handleFormSubmit = (e) => {
    e.preventDefault();
    console.log(loginDetail);

    try {
      axios
        .post("http://localhost:8080/login", loginDetail)
        .then((response) => {
          console.log(response.data);
          localStorage.setItem("jwtToken", response.data.jwtToken);
          toast.success("Login Successful")
          navigate("/admin");
        });
    } catch (error) {
      document.write(error);
    }

  };

  return (
    <Page
    title="Admin Login"  
  >
    <FormLayout>
      <TextField label="Store name" name="shop" value={loginDetail.shop} onChange={useCallback((value)=>
      setLoginDetail({
        ...loginDetail,
        ["shop"]: value,
      }), [])}  />
      <Button primary onClick={handleFormSubmit}>Login</Button>
    </FormLayout>
    
  </Page>
  );
};

export default Login;
