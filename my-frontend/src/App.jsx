import React, { useState } from "react";
import MyTopBar from "./Components/MyTopBar";
import "@shopify/polaris/build/esm/styles.css";
import enTranslations from "@shopify/polaris/locales/en.json";
import { AppProvider, Frame, Loading } from "@shopify/polaris";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { BrowserRouter, Routes, Route, Link } from "react-router-dom";
import MyNavigation from "./Components/MyNavigation";
import MyHomePage from "./Components/MyHomePage";
import Login from "./Components/Login";
import Products from "./Components/Products";
import Orders from "./Components/Orders";
import Customers from "./Components/Customers";
import CreateCustomers from "./Components/CreateCustomers";
import ErrorPage from "./Components/ErrorPage";
import Customer from "./Components/Customer";
import { DataProvider } from "./Contexts/DataContext";
import CreateProducts from "./Components/CreateProducts";
import CreateOrders from "./Components/CreateOrders";
import ModalExample from "./Components/ModalExample";
import CreateOrder from "./Components/CreateOrder";

const App = () => {
  const logo = {
    width: 124,
    topBarSource:
      "https://cdn.shopify.com/shopifycloud/web/assets/v1/13c99562e1ba017f.svg",
    contextualSaveBarSource:
      "https://cdn.shopify.com/s/files/1/0446/6937/files/jaded-pixel-logo-gray.svg?6215648040070010999",
    url: "#",
    accessibilityLabel: "Jaded Pixel",
  };


  const CustomLinkComponent = ({
    children,
    url,
    ...rest
  }) => {
    return (
      <Link
        to={url}
        {...rest}
      >
        {children}
      </Link>
    );
  };

  return (
    <div>
      <BrowserRouter>
        <DataProvider>
          <AppProvider i18n={enTranslations}
          linkComponent={CustomLinkComponent}
          >
            <Frame
              logo={logo}
              topBar={<MyTopBar />}
              navigation={<MyNavigation />}
            >
              <Routes>
                <Route path="/" element={<Login />} />
                <Route path="/modal" element={<ModalExample />} />
                <Route path="/admin" element={<MyHomePage />} />
                <Route path="/admin/orders" element={<Orders />} />
                <Route path="/admin/orders/create" element={<CreateOrder />} />
                <Route path="/admin/products" element={<Products />} />
                <Route path="/admin/products/create" element={<CreateProducts />} />
                <Route path="/admin/customers" element={<Customers />} />
                <Route
                  path="/admin/customers/create"
                  element={<CreateCustomers />}
                />
                <Route path="/error" element={<ErrorPage />} />
                <Route path="/admin/customers/:id" element={<Customer />} />
              </Routes>
            </Frame>
          </AppProvider>
          <ToastContainer />
        </DataProvider>
      </BrowserRouter>
    </div>
  );
};

export default App;
