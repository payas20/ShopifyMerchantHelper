import React from "react";
import {
  Page,
  IndexTable,
  LegacyCard,
  useIndexResourceState,
  Button,
} from "@shopify/polaris";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { toast } from "react-toastify";

const Products = () => {
  const navigate = useNavigate();

  const [products, setProducts] = useState([]);

  const config = {
    headers: {
      Authorization: `Bearer ${localStorage.getItem("jwtToken")}`,
    },
  };

  useEffect(() => {
    axios
      .get("http://localhost:8080/v1/view/products", config)
      .then((res) => {
        setProducts(res.data);
      })
      .catch((err) => {
        navigate("/error");
      });
  });

  const deleteProduct = (id) => {
    axios
      .delete(`http://localhost:8080/v1/delete/product/${id}`, config)
      .then((res) => {
        toast.success("Product Deleted Successfully");
      })
      .catch((err) => {
        toast.error("Something went wrong");
      });
  };

  const resourceName = {
    singular: "product",
    plural: "products",
  };

  const { selectedResources } = useIndexResourceState(products);

  const rowMarkup = products.map(
    ({ id, title, vendor, product_type, status }, index) => (
      <IndexTable.Row
        id={id}
        key={id}
        selected={selectedResources.includes(id)}
        position={index}
      >
        <IndexTable.Cell>{title}</IndexTable.Cell>
        <IndexTable.Cell>{status}</IndexTable.Cell>
        <IndexTable.Cell>{product_type}</IndexTable.Cell>
        <IndexTable.Cell>{vendor}</IndexTable.Cell>
        <IndexTable.Cell>
          <Button primary url={`/admin/products/${id}`}>
            {" "}
            View{" "}
          </Button>
          <Button destructive onClick={() => deleteProduct(id)}>
            {" "}
            Delete{" "}
          </Button>
        </IndexTable.Cell>
      </IndexTable.Row>
    )
  );

  return (
    <Page
      fullWidth
      title="Products"
      primaryAction={{ content: "Add product", url: "/admin/products/create" }}
      secondaryActions={[{ content: "Export" }]}
    >
      <LegacyCard>
        <IndexTable
          resourceName={resourceName}
          itemCount={products.length}
          selectable={false}
          headings={[
            { title: "Product" },
            { title: "Status" },
            { title: "Type" },
            { title: "Vendor" },
            { title: "Actions" },
          ]}
        >
          {rowMarkup}
        </IndexTable>
      </LegacyCard>
    </Page>
  );
};

export default Products;
