import axios from "axios";
import React, { useCallback, useState } from "react";
import { useNavigate } from "react-router-dom";
import {Page, FormLayout, TextField, Button, Divider, HorizontalGrid, Text, Card, AlphaCard} from '@shopify/polaris';
import { toast } from "react-toastify";

const CreateCustomers = () => {

    const [customer, setCustomer] = useState({
        first_name: '',
        last_name: '',
        email: '',
        phone: '',
        verified_email: true,
        password: '',
        password_confirmation: ''
      });

    const navigate = useNavigate();

    const handleChange = (value, field) => {
        setCustomer({
            ...customer,
            [field]: value,
          })
    }

    const config = {
        headers: {
          'Authorization': `Bearer ${localStorage.getItem("jwtToken")}`
        }
      };

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log(customer.first_name.length)
        if(customer.first_name.length == 0 || customer.email.length == 0 || customer.password.length == 0 || customer.password_confirmation.length == 0) {
            toast.error("Some Fields are Required")
            return;
        }

        axios.post("http://localhost:8080/v1/create/customer", customer, config)
        .then(res => {
          
                toast.success("Customer created successfully")
                navigate("/admin/customers")
            
        }).catch(err => {
            toast.error("Something went wrong");
        })
    }


  return (
    <Page
    backAction={{content: 'Customers', url: '/admin/customers'}}
    title="New Customer"  
  >
    <Divider/>
    <br />
    <HorizontalGrid gap="1" columns={['oneThird', 'twoThirds']}>

    <Text variant="headingMd" as="h6">
        Customer overview
    </Text>
    
    <AlphaCard>
    <FormLayout>
      <HorizontalGrid gap="2" columns={2}>
      <TextField label="First name" name="first_name" value={customer.first_name} onChange={useCallback((value) => handleChange(value, "first_name"))}  />
      <TextField label="Last name" name="last_name" value={customer.last_name} onChange={useCallback((value) => handleChange(value, "last_name"))}  />
      </HorizontalGrid>
      <TextField type="email" label="Email" name="email" value={customer.email} onChange={useCallback((value) => handleChange(value, "email"))}  />
      <TextField label="Phone" name="phone" value={customer.phone} onChange={useCallback((value) => handleChange(value, "phone"))}  />
      <TextField type="password" label="Password" name="password" value={customer.password} onChange={useCallback((value) => handleChange(value, "password"))}  />
      <TextField type="password" label="Password Confirmation" name="password_confirmation" value={customer.password_confirmation} onChange={useCallback((value) => handleChange(value, "password_confirmation"))}  />
      <Button primary onClick={handleSubmit}>Save</Button>
    </FormLayout>
    </AlphaCard>

   

    </HorizontalGrid>
    
    
  </Page>
  );
  
}

export default CreateCustomers
