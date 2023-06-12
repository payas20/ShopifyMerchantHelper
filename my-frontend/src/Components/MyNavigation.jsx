import React from 'react'
import { Navigation} from '@shopify/polaris';
import {
  HomeMajor,
  OrdersMajor,
  ProductsMajor,
  CustomersMajor,
} from '@shopify/polaris-icons';

const MyNavigation = () => {
  return (
    <div>
      <Navigation location="/">
        <Navigation.Section
          items={[
            {
              url: '/admin',
              excludePaths: ['#'],
              label: 'Home',
              icon: HomeMajor,
            },
            {
              url: '/admin/orders',
              excludePaths: ['#'],
              label: 'Orders',
              icon: OrdersMajor,
            },
            {
              url: '/admin/products',
              excludePaths: ['#'],
              label: 'Products',
              icon: ProductsMajor,
            },
            {
              url: '/admin/customers',
              excludePaths: ['#'],
              label: 'Customers',
              icon: CustomersMajor,
            },
          ]}
        />
      </Navigation>
    </div>
  )
}

export default MyNavigation
