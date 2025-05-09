import React from 'react'
import { Link } from 'react-router-dom'
import logo from '../assets/logo.jpg';

const NavBar: React.FC = () => {
  const imageLogo = logo;
  return (
    <>
      <nav className="navbar navbar-expand-lg custom-navbar">
        <div className="container-fluid d-flex justify-content-between align-items-center">
          {/* Logo + Brand */}
          <Link className="navbar-brand d-flex align-items-center gap-2" to="/">
            <img
              src={imageLogo}
              alt="Logo"
              className="rounded-circle"
              style={{ width: '45px', height: '45px', objectFit: 'cover' }}
            />
          </Link>

          {/* Nav Links */}
          <div className="collapse navbar-collapse justify-content-center">
            <ul className="navbar-nav">
              <li className="nav-item"><Link className="nav-link custom-link" to="/">Home</Link></li>
              <li className="nav-item"><Link className="nav-link custom-link" to="/users">User</Link></li>
              <li className="nav-item"><Link className="nav-link custom-link" to="/report">Report</Link></li>
            </ul>
          </div>

          {/* Avatar Dropdown */}
          <div className="dropdown">
            <button className="border-0 bg-transparent p-0" type="button" data-bs-toggle="dropdown" aria-expanded="false">
              <img
                src="https://mdbcdn.b-cdn.net/img/new/avatars/2.webp"
                className="rounded-circle shadow-sm"
                style={{ width: '45px', height: '45px', objectFit: 'cover' }}
                alt="Avatar"
              />
            </button>
            <ul className="dropdown-menu dropdown-menu-end shadow-sm mt-2">
              <li><h6 className="dropdown-header">Welcome, John</h6></li>
              <li><a className="dropdown-item" href="#">Profile</a></li>
              <li><a className="dropdown-item" href="#">Settings</a></li>
              <li><hr className="dropdown-divider" /></li>
              <li><a className="dropdown-item text-danger" href="#">Logout</a></li>
            </ul>
          </div>
        </div>
      </nav>
    </>
  )
}

export default NavBar