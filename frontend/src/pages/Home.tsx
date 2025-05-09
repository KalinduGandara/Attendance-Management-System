import React from 'react'
import { Link } from 'react-router'

const Home: React.FC = () => {
  return (
    <>
      <div className="container-fluid py-4 px-4 bg-light min-vh-100">
        <h2 className="mb-4 fw-bold text-dark">Welcome, Admin</h2>
        {/* Quick Stats */}
        <div className="row g-4 mb-4">
          <div className="col-md-3">
            <div className="card bg-white shadow-sm border-0 text-center p-3">
              <h6 className="text-secondary">Total Employees</h6>
              <h3 className="text-dark fw-bold">120</h3>
            </div>
          </div>
          <div className="col-md-3">
            <div className="card bg-white shadow-sm border-0 text-center p-3">
              <h6 className="text-secondary">Present Today</h6>
              <h3 className="text-success fw-bold">98</h3>
            </div>
          </div>
          <div className="col-md-3">
            <div className="card bg-white shadow-sm border-0 text-center p-3">
              <h6 className="text-secondary">Late Arrivals</h6>
              <h3 className="text-warning fw-bold">7</h3>
            </div>
          </div>
          <div className="col-md-3">
            <div className="card bg-white shadow-sm border-0 text-center p-3">
              <h6 className="text-secondary">Absent Today</h6>
              <h3 className="text-danger fw-bold">15</h3>
            </div>
          </div>
        </div>

        {/* Quick Actions - CSK Themed */}
        <div className="row g-4">
          <div className="col-md-6">
            <div className="card shadow-sm border-0 text-light p-4" style={{ backgroundColor: '#1e293b' }}>
              <h5 className="fw-semibold">User Management</h5>
              <p className="small text-light">Add, update, or remove employees and manage their access.</p>
              <Link to="/users" className="btn px-4 fw-semibold rounded-pill" style={{ backgroundColor: '#f97316', color: '#fff' }}>
                Go to Users
              </Link>
            </div>
          </div>
          <div className="col-md-6">
            <div className="card shadow-sm border-0 text-light p-4" style={{ backgroundColor: '#1e293b' }}>
              <h5 className="fw-semibold">Generate Attendance Report</h5>
              <p className="small text-light">View, filter, and download detailed attendance logs.</p>
              <Link to="/report" className="btn px-4 fw-semibold rounded-pill" style={{ backgroundColor: '#f97316', color: '#fff' }}>
                View Reports
              </Link>
            </div>
          </div>
        </div>
      </div>
    </>
  )
}

export default Home