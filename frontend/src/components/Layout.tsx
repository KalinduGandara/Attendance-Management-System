import React from 'react'
import NavBar from './NavBar'
import { Outlet } from 'react-router-dom'
import Footer from './Footer'

const Layout: React.FC = () => {
  return (
    <>
      <NavBar />
      <div className='container mt-3'>
        <Outlet />
      </div>
      <Footer />
    </>
  )
}

export default Layout       