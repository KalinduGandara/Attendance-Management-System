import React, { useState } from 'react'
import { Link } from 'react-router';

const UserCreate: React.FC = () => {
  const [user, setUser] = useState({
    id: '',
    name: '',
    email: '',
  });

  const handleChange = (e: { target: { name: string; value: string; }; }) => {
    const { name, value } = e.target;
    setUser({ ...user, [name]: value });
  };

  const handleSubmit = (e: { preventDefault: () => void; }) => {
    e.preventDefault();
    console.log('User created:', user);
    // reset or send data to API
  };

  const handleCancel = () => {
    setUser({ id: '', name: '', email: '' });
  };
  return (
    <>
      <div className="container text-center main-content">
        <h2 className="mb-4">Create New User</h2>
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
            <button type="submit" className="btn custom-btn ">Apply</button>
            <Link to={'/users'}><button type="button" className="btn btn-secondary rounded-pill" onClick={handleCancel}>Cancel</button></Link>
          </div>
        </form>
      </div>


    </>
  )
}

export default UserCreate