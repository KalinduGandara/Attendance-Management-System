
import { createBrowserRouter, createRoutesFromElements, RouterProvider, Route } from 'react-router-dom'
import Layout from './components/Layout'
import Home from './pages/Home'
import Users from './pages/Users'
import UserCreate from './pages/UserCreate'
import UserEdit from './pages/UserEdit'
import Report from './pages/Report'

function App() {
  const router = createBrowserRouter(
    createRoutesFromElements(
      <Route path='/' element={<Layout />}>
        <Route index element={<Home />} />
        <Route path='users' element={<Users />} />
        <Route path='report' element={<Report />} />
        <Route path='users/create' element={<UserCreate />} />
        <Route path='users/:userid' element={<UserEdit />} />
        <Route path='*' element={<h2>404 Not Found</h2>} />
      </Route>
    )
  )

  return <RouterProvider router={router} />
}

export default App
