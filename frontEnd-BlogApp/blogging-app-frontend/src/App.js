import "./App.css";
import Home from "./pages/Home";
import Login from "./pages/Login";
import Signup from "./pages/Signup";
import About from "./pages/About";
import Base from "./components/Base";
import { useRoutes } from "react-router";
import { Route, Routes } from "react-router-dom";
import { ToastContainer } from "react-toastify";
import PrivateRoute from "./components/PrivateRoute";
import UserDashboard from "./pages/user-pages/UserDashboard";
import ProfileInfo from "./pages/user-pages/ProfileInfo";
import Services from "./pages/Services";
import PostPage from "./pages/PostPage";


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
            <Route path="/logout" exact element={<Login />} />
            <Route path="/services" exact element={<Services />} />
            <Route path="/post/:postId" element={<PostPage/>}/>
            <Route path="/user" exact element={<PrivateRoute />}>
              <Route path="dashboard" exact element={<UserDashboard />} />
              <Route path="profile" exact element={<ProfileInfo/>}/>
            </Route>
            <Route />
          </Routes>
        </div>
      </Base>
      <ToastContainer position="bottom-center" />
    </div>
  );
}

export default App;
