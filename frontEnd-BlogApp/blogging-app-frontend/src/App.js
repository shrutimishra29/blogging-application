
import './App.css';
import Home from './pages/Home';
import Login from './pages/Login';
import Signup from './pages/Signup';
import About from './pages/About';
import Base from './components/Base';
import { useRoutes } from 'react-router';
import { Route, Routes } from 'react-router-dom';

function App() {
  return (
    <div className="base">
      <Base>
      <div className="App">
       
        <Routes>
          <Route path="/" exact element={<Home />} />
          <Route path="/login" exact element={<Login />} />
          <Route path="/signup" exact element={<Signup />} />
          <Route path="/about" exact element={<About />} />
        </Routes>
      </div>
      </Base>
    </div>
  );
}

export default App;
