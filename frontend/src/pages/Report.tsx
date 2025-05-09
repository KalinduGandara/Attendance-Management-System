import React from 'react'
import ReportTable from '../components/ReportTable'


const Report: React.FC = () => {
  return (
    <>
      <div className="row mt-4 align-items-center">
        {/* Sync Data Button */}
        <div className="col-md-2 text-center mb-3 mb-md-0">
          <button className="btn custom-btn w-100">Sync Data</button>
        </div>

        {/* Date Range + Generate */}
        <div className="col-md-10">
          <div className="row g-2 align-items-center justify-content-end">
            {/* Start Date */}
            <div className="col-auto">
              <label className="col-form-label fw-semibold">Start Date</label>
            </div>
            <div className="col-auto">
              <input type="date" className="form-control" />
            </div>

            {/* End Date */}
            <div className="col-auto">
              <label className="col-form-label fw-semibold">End Date</label>
            </div>
            <div className="col-auto">
              <input type="date" className="form-control" />
            </div>

            {/* Generate Button */}
            <div className="col-auto">
              <button className="btn custom-btn">Generate</button>
            </div>
          </div>
        </div>
      </div>
      <div className="row mt-4 align-items-center">
        <div className="col-auto">
          <button className='btn btn-warning'>Excell</button>
        </div>
        <div className='col-auto'>
          <button className='btn btn-warning'>PDF</button>
        </div>
      </div>
      <div className=' mt-3'>
        <ReportTable />
      </div>
    </>
  )
}

export default Report