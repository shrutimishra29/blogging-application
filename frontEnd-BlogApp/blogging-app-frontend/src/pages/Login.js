import { useState } from "react";
import Base from "../components/Base";
import { 
  Form,
  FormGroup,
  Label,
  Input,
  FormText,
  Button,
  ListGroup,
  Container,
  Card,
  CardHeader,
  CardBody,
  Row,
  Col
} from "reactstrap";
import { toast } from "react-toastify";
import { loginUser } from "../services/user-service";
import { doLogin } from "../components/auth";
import '../components/styles/login.css'

const Login = () => {
  
  const [loginDetail, setLoginDetail] = useState({
    email: "",
    password: "",
  });

  const handleChange = (e, feild) => {
      let actualValue = e.target.value;
      setLoginDetail({
        ...loginDetail,
        [feild]: actualValue,
      })
      
  }

const handleFormSubmit = (event) => {
  event.preventDefault();
  console.log(loginDetail);

  //Validating the fields

  if(loginDetail.email.trim() === ""){
    toast.error("Email is mandatory");
    return;
  }
  if(loginDetail.password.trim() === ""){
    toast.error("Password is mandatory");
    return;
  }

  //submit the data to server to generate token
  loginUser(loginDetail).then((data) => {
    console.log(data);

    //save data to local storage
    doLogin(data , () =>{
      console.log("Login details are saved.")
    })

    toast.success("Login Successful");
     setTimeout(function(){
      window.location.href = "/user/dashboard";
     },6000)
    }).catch((error) =>{
      console.log(error);
      toast.error("Invalid Credentials");
    })
  
};



const handleReset = () => {
  setLoginDetail({
    email: "",
    password: "",
  })
}
  return (
    <Container style={{ marginTop: "30px" }}>
      <Row>
        <Col sm={{ size: "6", offset: "3" }}>
          <Card>
            <CardHeader>
              <div className="text-center">
                <h3>Login</h3>
              </div>
            </CardHeader>
            <CardBody>
              {/* creating form */}

              <Form onSubmit={handleFormSubmit}>
                <FormGroup>
                  <Label for="email">Email</Label>
                  <Input
                    type="email"
                    name="email"
                    id="email"
                    placeholder="Enter your email"
                    value={loginDetail.email}
                    onChange={(e) => handleChange(e,'email')}
                  />
                </FormGroup>
                <FormGroup>
                  <Label for="password">Password</Label>
                  <Input
                    type="password"
                    name="password"
                    id="password"
                    placeholder="Enter your password"
                    value={loginDetail.password}
                    onChange={(e) => handleChange(e,'password')}
                  />
                </FormGroup>
                <div className="text-center">
                  <Button type="submit" className="login-submit-btn">
                    Login
                  </Button>
                  <Button color="light" className="ms-2" onReset={handleReset}>
                    Reset
                  </Button>
                </div>
              </Form>
            </CardBody>
          </Card>
        </Col>
      </Row>
    </Container>
    
  );
}
export default Login;
