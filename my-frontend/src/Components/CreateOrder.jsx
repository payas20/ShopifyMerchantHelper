import React, { useCallback, useEffect, useState } from "react";

import {
  Page,
  Layout,
  LegacyCard,
  Text,
  Icon,
  Combobox,
  Listbox,
  IndexTable,
  Loading,
  Badge,
  Button,
} from "@shopify/polaris";

import {
  SearchMinor,
  CancelMinor,
  MinusMinor,
  PlusMinor,
} from "@shopify/polaris-icons";

import axios from "axios";

import { useNavigate } from "react-router-dom";

// import OrderLineItem from "./OrderLineItem";

export default function CreateOrder() {
  const [products, setProducts] = useState([]);

  const [customers, setCustomers] = useState([]);

  const [selectedProducts, setSelectedProducts] = useState([]);

  const [selectedCustomer, setSelectedCustomer] = useState();

  const [selectedLineItems, setSelectedLineItems] = useState([]);

  const [options, setOptions] = useState(products);

  const [customerOptions, setCustomerOptions] = useState(customers);

  var responseList = [];

  const navigate = useNavigate();

  var token;

  // const [selectedItems, setSelectedItems] = useState([]);

  useEffect(() => {
    setOptions(products);

    setCustomerOptions(customers);

    token = localStorage.getItem("jwtToken");

    fetchAllProducts(token);

    fetchAllCustomers(token);
  }, []);

  const [selectedOption, setSelectedOption] = useState();

  const [inputValue, setInputValue] = useState("");

  const [selectedCustomerOption, setSelectedCustomerOption] = useState();

  const [customerInputValue, setCustomerInputValue] = useState("");

  const [isLoading, setIsLoading] = useState(true);

  const loadingMarkup = isLoading ? <Loading /> : null;

  const updateText = useCallback(
    (value) => {
      setInputValue(value);

      if (value === "") {
        setOptions(products);

        return;
      }

      const filterRegex = new RegExp(value, "i");

      const resultOptions = products.filter((option) =>
        option.title.match(filterRegex)
      );

      setOptions(resultOptions);
    },

    [products, options]
  );

  const updateSelection = useCallback(
    (selected) => {
      //!Prevent from duplication

      console.log(selected);

      const matchedOption = options.find((option) => {
        if (option.title === selected) return option;
      });

      setSelectedOption(selected, [selectedProducts]);

      setSelectedProducts([...selectedProducts, matchedOption]);

      console.log(matchedOption);

      matchedOption.variants.forEach((item) => {
        setSelectedLineItems((obj) => [...obj, { ...item, quantity: 1 }]);
      });

      setInputValue((matchedOption && matchedOption.title) || "");
    },

    [options, selectedProducts, selectedLineItems]
  );

  const updateCustomerText = useCallback(
    (value) => {
      setCustomerInputValue(value);

      if (value === "") {
        setCustomerOptions(customers);

        return;
      }

      const filterRegex = new RegExp(value, "i");

      const resultOptions = customers.filter((option) =>
        option.first_name.match(filterRegex)
      );

      setCustomerOptions(resultOptions);
    },

    [customers, customerOptions]
  );

  const updateCustomerSelection = useCallback(
    (selected) => {
      //!Prevent from duplication

      console.log(selected);

      const matchedOption = customerOptions.find((option) =>
        option.first_name.match(selected)
      );

      setSelectedCustomerOption(selected, [selectedCustomer]);

      setSelectedCustomer(matchedOption);

      console.log(matchedOption);

      setInputValue((matchedOption && matchedOption.first_name) || "");
    },

    [customerOptions, selectedCustomer]
  );

  const handleOnSubmit = async () => {
    token = localStorage.getItem("jwtToken");

    const config = {
      headers: {
        "Content-Type": "application/json",

        Authorization: "Bearer " + token,
      },
    };

    const response = await axios.post(
      "/order/create",

      {
        customer: selectedCustomer,

        variants: selectedLineItems,
      },

      config
    );

    console.log(response);

    console.log(selectedLineItems);

    console.log(selectedCustomer);

    if (response.status === 200) {
      navigate("/admin/orders");
    } else {
      navigate("/error");
    }
  };

  const fetchAllProducts = async (token) => {
    const config = {
      headers: {
        "Content-Type": "application/json",

        Authorization: "Bearer " + token,
      },
    };

    const response = await axios
      .get("http://localhost:8080/v1/view/products", config)
      .catch((e) => {
        navigate("/error");
      });

    console.log(response.status);

    if (response.status === 200) {
      if (response.data === "") {
        window.location.href = "http://localhost:3000/login";
      } else {
        responseList = response.data;

        if (responseList.length === 0) {
          navigate("/data/not-found");
        }

        console.log(responseList);

        setProducts(responseList);
      }
    }

    setIsLoading(false);
  };

  const fetchAllCustomers = async (token) => {
    const config = {
      headers: {
        "Content-Type": "application/json",

        Authorization: "Bearer " + token,
      },
    };

    const response = await axios
      .get("http://localhost:8080/v1/view/customers", config)
      .catch((e) => {
        navigate("/error/oops");
      });

    console.log(response.status);

    if (response.status === 200) {
      console.log(response);

      if (response.data === "") {
        navigate("/error/oops");
      } else {
        responseList = response.data;

        if (responseList.length === 0) {
          navigate("/data/not-found");
        }

        console.log(responseList);

        setCustomers(responseList);
      }
    }

    setIsLoading(false);
  };

  const optionsMarkup =
    options.length > 0
      ? options.map((option) => {
          const { title } = option;

          return (
            <Listbox.Option
              key={title}
              value={title}
              selected={selectedOption === title}
              accessibilityLabel={title}
            >
              {title}
            </Listbox.Option>
          );
        })
      : null;

  const customerOptionsMarkup =
    customerOptions.length > 0
      ? customerOptions.map((option) => {
          const { first_name } = option;

          return (
            <Listbox.Option
              key={first_name}
              value={first_name}
              selected={selectedCustomerOption === first_name}
              accessibilityLabel={first_name}
            >
              {first_name}
            </Listbox.Option>
          );
        })
      : null;

  const resourceName = {
    singular: "order",

    plural: "orders",
  };

  const deleteProduct = useCallback(
    (id) => {
      setSelectedLineItems(selectedLineItems.filter((prod) => prod.id !== id));
    },

    [selectedLineItems]
  );

  const increaseQuantity = useCallback(
    (id) => {
      const newState = selectedLineItems.map((obj) => {
        if (obj.id === id) {
          return { ...obj, quantity: obj.quantity + 1 };
        }

        return obj;
      });

      setSelectedLineItems(newState);
    },

    [selectedLineItems]
  );

  const decreaseQuantity = useCallback(
    (id) => {
      const newState = selectedLineItems.map((obj) => {
        if (obj.id === id) {
          if (obj.quantity > 1) return { ...obj, quantity: obj.quantity - 1 };
        }

        return obj;
      });

      setSelectedLineItems(newState);
    },

    [selectedLineItems]
  );

  const rowMarkup = selectedLineItems.map(
    ({ id, title, price, quantity }, index) => (
      // <div style={{ display: "inline-block" }}>

      <IndexTable.Row id={id} key={index} position={index}>
        <IndexTable.Cell>
          <Text variant="bodyMd" fontWeight="bold" as="span">
            {index + 1}
          </Text>
        </IndexTable.Cell>

        <IndexTable.Cell>{title}</IndexTable.Cell>

        <IndexTable.Cell>
          <div
            style={{
              display: "flex",
            }}
          >
            <Button
              icon={MinusMinor}
              onClick={() => decreaseQuantity(id)}
            ></Button>

            <div
              style={{
                margin: "auto",

                textAlign: "center",
              }}
            >
              {quantity}
            </div>

            <Button
              icon={PlusMinor}
              onClick={() => increaseQuantity(id)}
            ></Button>
          </div>
        </IndexTable.Cell>

        <IndexTable.Cell>
          <Text alignment="center">{price * quantity}</Text>
        </IndexTable.Cell>

        <IndexTable.Cell>
          <Button icon={CancelMinor} onClick={() => deleteProduct(id)}></Button>
        </IndexTable.Cell>

        <IndexTable.Cell></IndexTable.Cell>
      </IndexTable.Row>
    )
  );

  return (
    <Page
      fullWidth
      backAction={[
        {
          content: "Orders",

          url: "/admin/orders",

          onAction: console.log("asasa"),
        },
      ]}
      title={
        <Text alignment="start" fontWeight="bold">
          Create Order
        </Text>
      }
      titleMetadata={<Badge status="success">New</Badge>}
      compactTitle
      primaryAction={{
        content: "Create Order",

        disabled: false,

        onAction: handleOnSubmit,
      }}
    >
      <Layout>
        {loadingMarkup}

        <Layout.Section>
          <LegacyCard title="Add Product" sectioned>
            <Combobox
              activator={
                <Combobox.TextField
                  prefix={<Icon source={SearchMinor} />}
                  onChange={updateText}
                  label="Search tags"
                  labelHidden
                  value={inputValue}
                  placeholder="Search Products"
                  autoComplete="off"
                />
              }
            >
              {options.length > 0 ? (
                <Listbox onSelect={updateSelection}>{optionsMarkup}</Listbox>
              ) : null}
            </Combobox>

            <br />

            {
              selectedLineItems.length > 0 ? (
                <IndexTable
                  resourceName={resourceName}
                  itemCount={selectedLineItems.length}
                  selectable={false}
                  headings={[
                    { title: "Index" },

                    { title: "Name" },

                    { title: "Status" },

                    { title: "Quantity" },

                    { title: "Total", alignment: "end" },

                    { title: "", alignment: "end" },
                  ]}
                >
                  {rowMarkup}
                </IndexTable>
              ) : (
                "No Product Selected"
              ) //
            }
          </LegacyCard>
        </Layout.Section>

        <Layout.Section secondary>
          <LegacyCard title="Add Customer" sectioned>
            {selectedCustomer == null ? (
              <Combobox
                activator={
                  <Combobox.TextField
                    prefix={<Icon source={SearchMinor} />}
                    onChange={updateCustomerText}
                    label="Search tags"
                    labelHidden
                    value={customerInputValue}
                    placeholder="Search Customer"
                  />
                }
              >
                {customerOptions.length > 0 ? (
                  <Listbox onSelect={updateCustomerSelection}>
                    {customerOptionsMarkup}
                  </Listbox>
                ) : null}
              </Combobox>
            ) : (
              <center>
                <div style={{ color: "#bf0711" }}>
                  <Button
                    monochrome
                    outline
                    onClick={() => {
                      setSelectedCustomer();
                    }}
                  >
                    Remove Customer
                  </Button>
                </div>
              </center>
            )}

            <br />

            {selectedCustomer != null ? (
              <Layout>
                <Layout.Section>
                  <LegacyCard title="Shipment Details" sectioned>
                    <div>
                      <Text>{selectedCustomer.addresses[0].address1}</Text>

                      <Text>
                        {selectedCustomer.city}
                        {" - "} {selectedCustomer.addresses[0].zip}
                      </Text>

                      <Text>
                        {selectedCustomer.province}
                        {" - "} {selectedCustomer.addresses[0].province_code}
                      </Text>

                      <Text>
                        {selectedCustomer.addresses[0].country} {" - "}{" "}
                        {selectedCustomer.addresses[0].country_code}
                      </Text>
                    </div>
                  </LegacyCard>
                </Layout.Section>

                <Layout.Section secondary>
                  <LegacyCard title="Cutomer Details" sectioned>
                    <div>
                      <Text>{selectedCustomer.name}</Text>

                      <Text>{selectedCustomer.email}</Text>

                      <Text>{selectedCustomer.phone}</Text>
                    </div>
                  </LegacyCard>
                </Layout.Section>
              </Layout>
            ) : (
              "No Customer Selected"
            )}
          </LegacyCard>
        </Layout.Section>
      </Layout>
    </Page>
  );
}
