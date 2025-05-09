import React, { useState, useEffect } from 'react'
import { Link, useParams } from 'react-router-dom'
import { userList } from '../components/UserTable'

const UserEdit: React.FC = () => {
  const { userid } = useParams<{ userid: string }>()
  const [user, setUser] = useState({ id: '', name: '', email: '' })

  const userDetails = userList.find(userDetails => userDetails.id === userid)

  useEffect(() => {
    // Simulate API call
    const fetchUser = async () => {
      // Example: Replace with real API call like axios.get(`/api/users/${id}`)
      const fakeUser = {
        id: userid || '',
        name: userDetails?.name || '',
        email: userDetails?.email || '',
      }
      setUser(fakeUser)
    }

    if (userid) fetchUser()
  }, [userid])

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target
    setUser({ ...user, [name]: value })
  }

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault()
    console.log('User updated:', user)
    // TODO: Send updated user to API
  }

  const handleCancel = () => {
    setUser({ id: '', name: '', email: '' })
  }

  return (
    <div className="container text-center main-content">
      <h2 className="mb-4">Edit User</h2>
      <form onSubmit={handleSubmit} className="text-start mx-auto" style={{ maxWidth: '500px' }}>
        <div className="mb-3">
          <label className="form-label">Employee ID</label>
          <input type="text" className="form-control" name="id" value={user.id} onChange={handleChange} required />
        </div>
        <div className="mb-3">
          <label className="form-label">Name</label>
          <input type="text" className="form-control" name="name" value={user.name} onChange={handleChange} required />
        </div>
        <div className="mb-3">
          <label className="form-label">Email</label>
          <input type="email" className="form-control" name="email" value={user.email} onChange={handleChange} required />
        </div>
        <div className="d-flex justify-content-end gap-2">
          <button type="submit" className="btn custom-btn">Apply</button>
          <Link to={'/users'}><button type="button" className="btn btn-secondary rounded-pill" onClick={handleCancel}>Cancel</button></Link>
        </div>
      </form>
    </div>
  )
}

export default UserEdit
