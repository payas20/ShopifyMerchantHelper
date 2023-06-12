import axios from "axios";
import React, { useCallback, useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import {
  Page,
  FormLayout,
  TextField,
  Button,
  Divider,
  HorizontalGrid,
  Text,
  AlphaCard,
  Icon,
  LegacyCard,
  VerticalStack,
  IndexTable,
  useIndexResourceState,
  Listbox,
  Combobox,
} from "@shopify/polaris";
import { SearchMinor, MinusMinor, PlusMinor, CancelMinor  } from "@shopify/polaris-icons";
import { toast } from "react-toastify";

const CreateOrders = () => {
  const [products, setProducts] = useState([]);

  const [customers, setCustomers] = useState([]);

  const [selectedProducts, setSelectedProducts] = useState([]);

  const [selectedCustomer, setSelectedCustomer] = useState();

  const [selectedLineItems, setSelectedLineItems] = useState([]);

  const [customerOptions, setCustomerOptions] = useState(customers);

  var responseList = [];

  const config = {
    headers: {
      Authorization: `Bearer ${localStorage.getItem("jwtToken")}`,
    },
  };
  const navigate = useNavigate();
  useEffect(() => {
    axios
      .get("http://localhost:8080/v1/view/products", config)
      .then((res) => {
        setProducts(res.data);
        setOptions(products)
      })
      .catch((err) => {
        navigate("/error");
      });
  }, []);

  const [selectedOption, setSelectedOption] = useState();
  const [inputValue, setInputValue] = useState("");
  const [options, setOptions] = useState(products);

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
      // console.log(options)
      const matchedOption = options.find((option) => {
        return option.title.match(selected);
      });
      //   console.log(matchedOption)
      setSelectedOption(selected);
      //   setSelectedProducts([...selectedProducts,  matchedOption])
      selectedProducts.push(matchedOption);
      console.log(selectedProducts);
      matchedOption.variants.forEach((item) => {
        // setSelectedLineItems((obj) => [...obj, { ...item, quantity: 1 }]);
        selectedLineItems.push({ ...item, quantity: 1 });
      });
      console.log(selectedLineItems);
      setInputValue("");
    },
    [options, selectedProducts, selectedLineItems]
  );

  const optionsMarkup =
    options.length > 0
      ? options.map((option) => {
          const { title } = option;

          return (
            <Listbox.Option
              key={`${title}`}
              value={title}
              selected={selectedOption === title}
              accessibilityLabel={title}
            >
              {title}
            </Listbox.Option>
          );
        })
      : null;

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

      const deleteProduct = useCallback(
        (id) => {
          setSelectedLineItems(selectedLineItems.filter((prod) => prod.id !== id));
          console.log(selectedLineItems)
        },
    
        [selectedLineItems]
      );

      const resourceName = {
        singular: "order",
    
        plural: "orders",
      };

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
      backAction={{ content: "Orders", url: "/admin/orders" }}
      title="Create order"
    >
      <HorizontalGrid gap="1" columns={["twoThirds", "oneThird"]}>
        <VerticalStack gap="5">
          <LegacyCard title="Products" sectioned>
            <Combobox
              activator={
                <Combobox.TextField
                  prefix={<Icon source={SearchMinor} />}
                  onChange={updateText}
                  label="Search tags"
                  labelHidden
                  value={inputValue}
                  placeholder="Search tags"
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

                    // { title: "Status" },

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
        </VerticalStack>

        <VerticalStack gap="5">
          <LegacyCard title="Notes" sectioned>
            <Text>No notes</Text>
          </LegacyCard>
        </VerticalStack>
      </HorizontalGrid>
    </Page>
  );
};

export default CreateOrders;
