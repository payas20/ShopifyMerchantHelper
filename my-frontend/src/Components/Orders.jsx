import React from 'react'
import {
  Page,
  IndexTable,
  LegacyCard,
  useIndexResourceState,
  Button
} from '@shopify/polaris';
import {useState, useEffect} from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios'
import { toast } from 'react-toastify';

const Orders = () => {
  return (
    <Page
    fullWidth
    title="Orders"
    primaryAction={{content: 'Create order', url: "/admin/orders/create"}}
    secondaryActions={[{content: 'Export'}]}
    
  >

  </Page>
  )
}

export default Orders
