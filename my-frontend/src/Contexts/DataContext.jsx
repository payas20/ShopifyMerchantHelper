import React, { createContext, useState } from 'react'

const DataContext = createContext({})

export const DataProvider = ({children}) => {

  const [shop, setShop] = useState('');

  return (
    <DataContext.Provider value={{
      shop, setShop
    }}>
      {children}
    </DataContext.Provider>
  )
}

export default DataContext