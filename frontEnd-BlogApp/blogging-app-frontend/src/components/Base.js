import MyNavbar from "./myNavbar";
import MyFooter from "./myFooter";
const Base = ({ title = "Welcome to our website", children }) => {
    return (
        <div className="container-fluid p-0 m-0">
            <MyNavbar /> 
            {children}

            <MyFooter/>
        </div>
    );
};

export default Base;

