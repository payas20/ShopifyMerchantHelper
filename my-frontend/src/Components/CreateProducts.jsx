import React, { useState, useCallback } from "react";
import {
  Page,
  HorizontalGrid,
  VerticalStack,
  AlphaCard,
  Text,
  LegacyCard,
  Select,
  TextField,
  Button,
} from "@shopify/polaris";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";
import axios from 'axios'

const CreateProducts = () => {
  const [product, setProduct] = useState({
    title: "",
    body_html: "",
    vendor: "",
    product_type: "",
    status: "draft",
    variants: []
    // options: [],
  });

  const handleChange = (value, field) => {
    setProduct({
      ...product,
      [field]: value,
    });
  };


  const [status, setStatus] = useState("draft");
  // const [statusChanged, setStatusChanged] = useState(false)

  const statusOptions = [
    { value: "active", label: "Active" },
    { value: "draft", label: "Draft" }
    
  ];

  const handleSelectChange = (value) => {
    setStatus(value);
    console.log(status);
    product.status = value;
    // setStatusChanged(true)
  };

  const [variantList, setVariantList] = useState([]);
  const [variant, setVariant] = useState({
    option1: '',
    price: '',
    sku: ''
  })

  const handleChangeVariant = (value, field) => {
    setVariant({
        ...variant,
        [field]: value,
      })

}

const addVariant = () => {
        const {option1, price, sku} = variant
        if(option1 && price && sku){
        // setVariantList((prevData)=>[...prevData, {option1, price, sku} ])
        variantList.push({option1, price, sku})
        console.log(variantList);
        console.log(variant)
        setVariant({option1:'', price: '', sku: ''})
        product.variants = variantList
        console.log(product.variants)
        }
}

    
const config = {
    headers: {
      'Authorization': `Bearer ${localStorage.getItem("jwtToken")}`
    }
  };

  const navigate = useNavigate()

    const createProduct = () => {
       if(product.variants.length==0){
        toast.error("Atleast one variant required")
        return
       }

       if(product.status)

       axios.post("http://localhost:8080/v1/create/product", product, config)
        .then(res => {
          
                toast.success("Product created successfully")
                navigate("/admin/products")
            
        }).catch(err => {
            navigate('/error')
        })
    }
 console.log(product)

  return (
    <Page
      backAction={{ content: "Products", url: "/admin/products" }}
      title="Add product"

    >
      <HorizontalGrid gap="1" columns={["twoThirds", "oneThird"]}>
        <VerticalStack gap="5">
          <AlphaCard>
            <TextField
              label="Title"
              name="title"
              value={product.title}
              onChange={useCallback((value) => handleChange(value, "title"))}
            />
            <TextField
              label="Vendor"
              name="vendor"
              value={product.vendor}
              onChange={useCallback((value) => handleChange(value, "vendor"))}
            />
            <TextField
              label="Product Type"
              name="product_type"
              value={product.product_type}
              onChange={useCallback((value) =>
                handleChange(value, "product_type")
              )}
            />
          </AlphaCard>

          <LegacyCard title="Variants" sectioned>
            <TextField type="text" value={variant.option1} name="option1" placeholder="option1" onChange={useCallback((value)=>handleChangeVariant(value, "option1"))} />
            <br />
            <TextField type="text" value={variant.price} name="price" placeholder="price" onChange={useCallback((value)=>handleChangeVariant(value, "price"))} />
            <br />
            <TextField type="text" value={variant.sku} name="sku" placeholder="sku" onChange={useCallback((value)=>handleChangeVariant(value, "sku"))} />
            <br />
            <Button primary onClick={addVariant}>Add variant</Button>
          </LegacyCard>
        </VerticalStack>

        <VerticalStack gap="5">
          <LegacyCard title="Status" sectioned>
            <Select
              options={statusOptions}
              onChange={useCallback((value) => handleSelectChange(value))}
              value={status}
            />
          </LegacyCard>
          <LegacyCard title="Create Product" sectioned>
            <Button primary onClick={createProduct}>save</Button>
          </LegacyCard>
        </VerticalStack>
      </HorizontalGrid>
    </Page>
  );
};

export default CreateProducts;
