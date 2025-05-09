import React from 'react'

const InOutEdit: React.FC = () => {
  return (
    <>
      <div className="card" style={{ width: '29rem', height: '20rem' }}>
        <div className="card-body">
          <div className='mb-3 '>
            <select className="form-select mb-3" aria-label="Default select example">
              <option selected>Select Type</option>
              <option value="1">IN</option>
              <option value="2">OUT</option>
            </select>
            <div className="mb-3">
              <label htmlFor="datetime" className="form-label">Select Date & Time</label>
              <input
                type="datetime-local"
                className="form-control"
                id="datetime"
                name="datetime"
                required
              />
            </div>
          </div>
          <div className="modal-footer">
            <button type="button" className="btn btn-primary">Save changes</button>
          </div>
        </div>
      </div>
    </>
  )
}

export default InOutEdit