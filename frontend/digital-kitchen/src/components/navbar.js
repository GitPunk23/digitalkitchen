import React from 'react';
import { Link, useLocation } from 'react-router-dom';

const Navbar = () => {
  const location = useLocation();

  // Check if the current location matches the home page route
  const isHomePage = location.pathname === '/';

  // Render the navbar only if it's not the home page
  if (isHomePage) {
    return null;
  }

  return (
    <nav className="navbar navbar-expand-lg navbar-light bg-light">
      <div className="container">
        <Link className="navbar-brand" to="/">
          Home
        </Link>
        <Link className="navbar-brand" to="/create">
          Create
        </Link>
        <Link className="navbar-brand" to="/search">
          Search
        </Link>
      </div>
    </nav>
  );
};

export default Navbar;
