import {
  Page,
  LegacyCard,
  HorizontalGrid,
  VerticalStack,
  AlphaCard,
  Link,
  CalloutCard,
} from "@shopify/polaris";
import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import axios from "axios";

const Customer = () => {
  const { id } = useParams();

  const [customer, setCustomer] = useState({});
  const [address, setAddress] = useState(null);

  const navigate = useNavigate();

  const config = {
    headers: {
      Authorization: `Bearer ${localStorage.getItem("jwtToken")}`,
    },
  };

  useEffect(() => {
    axios
      .get(`http://localhost:8080/v1/view/customer/${id}`, config)
      .then((res) => {
        setCustomer(res.data.customer);
        setAddress(res.data.customer.addresses[0]);
        console.log(res.data);
      })
      .catch((err) => {
        navigate("/error");
      });
  }, []);

  return (
    <Page
      backAction={{ content: "Customers", url: "/admin/customers" }}
      title={customer.first_name + " " + customer.last_name}
      subtitle="Customer for about x hours"
      actionGroups={[
        {
          title: "More option",
          actions: [
            {
              content: "Share on Facebook",
              accessibilityLabel: "Individual action label",
              onAction: () => alert("Share on Facebook action"),
            },
          ],
        },
      ]}
      pagination={{
        hasPrevious: true,
        hasNext: true,
      }}
    >
      <HorizontalGrid gap="1" columns={["twoThirds", "oneThird"]}>
        <VerticalStack gap="5">
          <CalloutCard
            title="Last order placed"
            illustration="https://cdn.shopify.com/s/assets/admin/checkout/settings-customizecart-705f57c725ac05be5a34ec20c05b94298cb8afd10aac7bd9c7ad02030f48cfa0.svg"
            primaryAction={{
              content: "CreateOrder",
              url: "/admin/orders/create",
            }}
          >
            <p>This customer hasn't placed any order yet</p>
          </CalloutCard>
        </VerticalStack>

        <VerticalStack gap="5">
          <LegacyCard title="Notes" sectioned>
            <p>This customer doesn't have notes</p>
          </LegacyCard>
          <AlphaCard>
            <LegacyCard title="Customer" sectioned>
              <p>Has a classic account</p>
            </LegacyCard>
            <LegacyCard title="Contact information" sectioned>
              <Link>{customer.email}</Link>
              <p>{customer.phone}</p>
              <p>will recieve notifications in English</p>
            </LegacyCard>
            <LegacyCard title="Default address" sectioned>
              {
                address!== null ? (
                  <>
                    <p>{address.name}</p>
                    <p>{address.address1}</p>
                    <p>{address.city+" "+address.province+" "+address.zip}</p>
                    <p>{address.country}</p>
                    <p>{address.phone}</p>
                  </>
                  
                ) : (
                  "No address found"
                )
              }
            </LegacyCard>
          </AlphaCard>
        </VerticalStack>
      </HorizontalGrid>
    </Page>
  );
};

export default Customer;
