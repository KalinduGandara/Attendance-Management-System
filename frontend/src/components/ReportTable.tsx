import React from 'react'
import InOutEdit from './InOutEdit'

const ReportTable: React.FC = () => {
  const attendanceList = [
    {
      employeeId: 221,
      name: 'Surindu',
      date: '2025-05-07',
      inTime: '08:45',
      outTime: '13:00',
      workingHours: '04:15',
      result: 'Normal'
    },
    {
      employeeId: 221,
      name: 'Surindu',
      date: '2025-05-07',
      inTime: '17:00',
      outTime: '21:00',
      workingHours: '04:00',
      result: 'Normal'
    },
    {
      employeeId: 222,
      name: 'Kavish',
      date: '2025-05-07',
      inTime: '09:20',
      outTime: '12:50',
      workingHours: '03:30',
      result: 'Late IN, Early Out'
    }, {
      employeeId: 222,
      name: 'Kavish',
      date: '2025-05-07',
      inTime: '17:00',
      outTime: '21:50',
      workingHours: '04:50',
      result: 'Normal'
    },
    {
      employeeId: 223,
      name: 'Amila',
      date: '2025-05-07',
      inTime: '13:05',
      outTime: '21:00',
      workingHours: '07:55',
      result: 'Present'
    }
  ]

  return (
    <>
      <table className="table table-striped">
        <thead className="custom-thead">
          <tr>

            <th scope="col">Employee ID</th>
            <th scope="col">Name</th>
            <th>Date</th>
            <th>In Time</th>
            <th>Out Time</th>
            <th>Working Hours</th>
            <th scope="col">Result</th>

          </tr>
        </thead>
        <tbody>
          {attendanceList.map((record, index) => (
            <tr key={index}>
              <td>{record.employeeId}</td>
              <td>{record.name}</td>
              <td>{record.date}</td>
              <td>
                <button type="button" className="border-0 bg-transparent p-0" data-bs-toggle="modal" data-bs-target={`#timechangeModal${record.employeeId}`}>{record.inTime}</button>
                <div className="modal fade" id={`timechangeModal${record.employeeId}`} aria-labelledby="exampleModalLabel" aria-hidden="true">
                  <div className="modal-dialog">
                    <div className="modal-content">
                      <div className="modal-header">
                        <h1 className="modal-title fs-5" id="exampleModalLabel">{record.name} - {record.date}</h1>
                        <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                      </div>
                      <div className="modal-body item-center">
                        <InOutEdit />
                      </div>

                    </div>
                  </div>
                </div>
              </td>
              <td>
                <button type="button" className="border-0 bg-transparent p-0" data-bs-toggle="modal" data-bs-target={`#timechangeModal${record.employeeId}`}>{record.outTime}</button>
              </td>
              <td>{record.workingHours}</td>
              <td>{record.result}</td>
            </tr>

          ))}
        </tbody>
      </table>
      {/* <button type="button" className="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
  Launch demo modal
</button> */}
    </>
  )
}

export default ReportTable