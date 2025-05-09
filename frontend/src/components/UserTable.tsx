import React, { useEffect, useState } from 'react'
import { Link } from 'react-router'

export const userList = [
  { name: 'surindu', email: 'surindu@lk.lk', id: '221' },
  { name: 'Kavish', email: 'kavish@lk.lk', id: '222' },
  { name: 'Amila', email: 'amila@lk.lk', id: '223' },
  { name: 'Rajitha', email: 'rajitha@lk.lk', id: '224' },
];

export interface User {
  name: string;
  email: string;
  id: string;
}
interface Props {
  users: User[]
}


const UserTable: React.FC<Props> = ({ users }) => {
  const [destroyUser, setDestroyUser] = useState('');

  const handleDestroy = () => {
    const destroy = [destroyUser];
    console.log(destroy);
  }

  useEffect(() => {
    handleDestroy();
  }, [destroyUser])

  return (
    <>
      <table className="table table-striped">
        <thead className="custom-thead">
          <tr>
            <th scope="col">#</th>
            <th></th>
            <th scope="col">Employee ID</th>
            <th scope="col">Name</th>
            <th scope="col">Email</th>
          </tr>
        </thead>
        <tbody>

          {users.map((user, index) => {
            return (
              <tr key={index}>
                <th scope="row">{index + 1}</th>
                <td><input type="checkbox" value={user.id} onChange={(e) => setDestroyUser(e.target.value)} /></td>
                <td><Link to={`/users/${user.id}`}>{user.id}</Link></td>
                <td>{user.name}</td>
                <td>{user.email}</td>
              </tr>
            )
          })}
        </tbody>
      </table>
    </>
  )
}

export default UserTable