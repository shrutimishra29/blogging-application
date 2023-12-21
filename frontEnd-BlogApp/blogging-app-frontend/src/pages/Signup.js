import { useEffect, useState } from "react";
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
  Col,
} from "reactstrap";

import { signup } from "../services/user-service";
const Signup = () => {

    const [data, setData] = useState({
        firstName: "",
        lastName: "",
        userEmail: "",
        password: "",
        about: "",
        username: "",
    });

    const [error, setError] = useState({
        errors: {},
        isError: false
    })

    const handleChange = (e, property) => {
       
        //Dynamically setting values in data state
        setData({
            ...data,
            [property]: e.target.value
        })
    }

    useEffect(() => {
        console.log(data);
    }, [data])
   const submitForm = (event) => {
        event.preventDefault(); 
       
        //data validate

        //call server api for sending the data
        signup(data).then((response) => {
            console.log(response);
            console.log("Success")
        }).catch((error) => {
            console.log(error);
            console.log("Error")
        });
    }

    const resetData = () => {
        setData({
            firstName: "",
            lastName: "",
            userEmail: "",
            password: "",
            about: "",
            username: "",
        })
    
    }




  return (
    <Container style={{ marginTop: "30px" }}>
      <Row>
        <Col sm={{ size: "6", offset: "3" }}>
          <Card color="dark" inverse>
            <CardHeader>
              <div className="text-center">
                <h3>Fill information to Sign up !! </h3>
              </div>
            </CardHeader>
            <CardBody>
              {/* creating form */}

              <Form onSubmit={submitForm}>
                <FormGroup>
                  <Label for="firstname">First Name</Label>
                  <Input
                    type="text"
                    name="firstname"
                    id="firstname"
                    placeholder="Enter your first name"
                    onChange={(e)=> handleChange(e, 'firstName')}
                    value={data.firstname}
                  />
                </FormGroup>
                <FormGroup>
                  <Label for="lastname">Last Name</Label>
                  <Input
                    type="text"
                    name="lastname"
                    id="lastname"
                    placeholder="Enter your last name"
                    onChange={(e)=> handleChange(e, 'lastName')}
                    value={data.lastname}
                  />
                </FormGroup>
                <FormGroup>
                  <Label for="username">Username</Label>
                  <Input
                    type="text"
                    name="username"
                    id="username"
                    placeholder="Enter your username"
                    onChange={(e)=> handleChange(e, 'username')}
                    value={data.username}
                  />
                </FormGroup>
                <FormGroup>
                  <Label for="email">Email</Label>
                  <Input
                    type="email"
                    name="email"
                    id="email"
                    placeholder="Enter your email"
                    onChange={(e)=> handleChange(e, 'userEmail')}
                    value={data.email}
                  />
                </FormGroup>
                <FormGroup>
                  <Label for="password">Password</Label>
                  <Input
                    type="password"
                    name="password"
                    id="password"
                    placeholder="Enter your password"
                    onChange={(e)=> handleChange(e, 'password')}
                    value={data.password}
                  />
                </FormGroup>
                <FormGroup>
                  <Label for="about">About</Label>
                  <Input
                    type="text"
                    name="about"
                    id="about"
                    placeholder="Enter your about"
                    onChange={(e)=> handleChange(e, 'about')}
                    value={data.about}
                  />
                </FormGroup>
                <div className="text-center">
                  <Button outline color="light">
                    Register
                  </Button>
                  '
                  <Button onClick={resetData} color="secondary" className="ms-2">
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
};

export default Signup;
