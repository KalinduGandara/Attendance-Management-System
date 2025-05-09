import React, { useEffect, useState } from 'react'
import UserTable, { userList } from '../components/UserTable'
import { Link } from 'react-router-dom'

const Users: React.FC = () => {
  const [searchTerm, setSearchTerm] = useState('');
  const [filteredUsers, setFilteredUsers] = useState(userList);
  const searchUser = () => {
    if (searchTerm === '') {
      setFilteredUsers(userList);
    } else {
      const filtered = userList.filter(user =>
        user.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
        user.id.toString().includes(searchTerm)
      )
      setFilteredUsers(filtered)
    }
  }

  useEffect(() => {
    if (searchTerm === '') {
      setFilteredUsers(userList);
    }
  }, [searchTerm])

  return (
    <>
      <div className="container text-center">
        <div className="row">
          <div className="col-4">
            <Link to={'/users/create'}><button className="btn custom-btn mb-4">Create Users</button></Link>
          </div>
          <div className="col-8">
            <div className="d-flex align-items-center justify-content-end">
              <input className="form-control me-2 custom-input w-25" type="search" placeholder="Search..." aria-label="Search" value={searchTerm} onChange={(e) => setSearchTerm(e.target.value)} />
              <button className="btn custom-btn" onClick={() => searchUser()}>Search</button>
            </div>
          </div>
        </div>
      </div>
      <div className="mt-3">
        <UserTable users={filteredUsers} />
      </div>
    </>
  )
}

export default Users