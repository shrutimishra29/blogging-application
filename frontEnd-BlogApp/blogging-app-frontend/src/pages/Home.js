// Home.jsx

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCoffee, faSignInAlt, faUserPlus } from '@fortawesome/free-solid-svg-icons';
import NewFeed from '../components/newFeed';
import { useEffect } from 'react';
import '../components/styles/home.css';
import { Link } from 'react-router-dom';

const Home = () => {

  return (
    <div className="home-container">
      <div className="background-image"></div>
      <div className="content">
        <h3>Welcome to Velvet voices</h3>
        <p>Share your thoughts, stories, and ideas with the world.</p>

        <div className="additional-info">
          
        </div>
        
        <div className="button-container">
          <Link to="/login" className="action-button">
          <p>Already have an account?</p>
            <FontAwesomeIcon icon={faSignInAlt} />
            <span>Login</span>
          </Link>

          <Link to="/signup" className="action-button">
            
          <p>Register today!!</p>
            <FontAwesomeIcon icon={faUserPlus} />
            <span>Sign Up</span>
          </Link>
        </div>
      </div>
    </div>
  );
};

const ActionButton = ({ icon, label }) => {
  return (
    <button className="action-button">
      <FontAwesomeIcon icon={icon} />
      <span>{label}</span>
    </button>
  );
};

export default Home;
