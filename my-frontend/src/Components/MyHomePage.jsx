import React from "react";
import { Page, Badge, LegacyCard } from "@shopify/polaris";

const MyHomePage = () => {
  return (
    <div>
      <Page
        title="3/4 inch Leather pet collar"
        titleMetadata={<Badge status="success">Paid</Badge>}
        subtitle="Perfect for any pet"
        compactTitle
        primaryAction={{ content: "Save", disabled: true }}
        secondaryActions={[
          {
            content: "Duplicate",
            accessibilityLabel: "Secondary action label",
            onAction: () => alert("Duplicate action"),
          },
          {
            content: "View on your store",
            onAction: () => alert("View on your store action"),
          },
        ]}
        actionGroups={[
          {
            title: "Promote",
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
        <LegacyCard title="Credit card" sectioned>
          <p>Credit card information</p>
        </LegacyCard>
      </Page>
    </div>
  );
};

export default MyHomePage;
