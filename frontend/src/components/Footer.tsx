import React from 'react'

const Footer: React.FC = () => {
  return (
    <>
      <footer className="bg-dark text-light py-2 mt-3">
        <div className="container text-center">
          <div className="small" style={{ color: '#cccccc' }}>
            &copy; {new Date().getFullYear()} CSK Software Solutions. All rights reserved.
          </div>
        </div>
      </footer>
    </>
  )
}

export default Footer