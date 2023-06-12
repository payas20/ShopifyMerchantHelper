import React, { useEffect, useState } from "react";
import { Page, AlphaCard, Icon, TextField, IndexTable,
    useIndexResourceState, 
    Button} from "@shopify/polaris";
import {SearchMinor} from '@shopify/polaris-icons';
import axios from 'axios'
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";


const Customers = () => {

    const navigate = useNavigate()

  const [customers, setCustomers] = useState([])

  const config = {
    headers: {
      'Authorization': `Bearer ${localStorage.getItem("jwtToken")}`
    }
  };

  useEffect(() => {
    
    axios.get("http://localhost:8080/v1/view/customers", config)
    .then((res) => {
        setCustomers(res.data)
    }).catch((err) => {
        navigate("/error")
    })
  })

  

  const deleteCustomer = (id) => {
    axios.delete(`http://localhost:8080/v1/delete/customer/${id}`, config)
    .then((res) => {
      toast.success("Customer Deleted Successfully")
    }).catch((err)=> {
      toast.error("Something went wrong")
    })
  }


  const resourceName = {
    singular: 'customer',
    plural: 'customers',
  };

  const {selectedResources} =
    useIndexResourceState(customers);

    const rowMarkup = customers.map(
        (
          {id, first_name, last_name, email, phone},
          index,
        ) => (
          <IndexTable.Row
            id={id}
            key={id}
            selected={selectedResources.includes(id)}
            position={index}
          >
            <IndexTable.Cell>{first_name}</IndexTable.Cell>
            <IndexTable.Cell>{last_name}</IndexTable.Cell>
            <IndexTable.Cell>{email}</IndexTable.Cell>
            <IndexTable.Cell>{phone}</IndexTable.Cell>
            <IndexTable.Cell>
                <Button primary url={`/admin/customers/${id}`} > View </Button>
                <Button destructive onClick={() => deleteCustomer(id)}> Delete </Button>
            </IndexTable.Cell>
            
          </IndexTable.Row>
        ),
      );
    

  return (
    
    <Page
    fullWidth
    title="Customer segment"
    primaryAction={{content: 'Add Customer', url: "/admin/customers/create"}}
    secondaryActions={[{content: 'Export'}]}
    
  >
    <AlphaCard>

    <TextField
      prefix={<Icon source={SearchMinor} color="base" />}
      placeholder="Search customer"
      autoComplete="off"
    ></TextField>

    <br />

<IndexTable
        resourceName={resourceName}
        itemCount={customers.length}
        selectable={false}
    
        headings={[
          {title: 'First Name'},
          {title: 'Last Name'},
          {title: 'Email'},
          {title: 'Phone'},
          {title: 'Actions'}
        ]}
      >
        {rowMarkup}
      </IndexTable>

    </AlphaCard>
  </Page>
  );
};

export default Customers;
