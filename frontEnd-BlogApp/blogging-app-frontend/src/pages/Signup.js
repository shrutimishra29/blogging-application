// Signup.js
import React, { useEffect, useState } from "react";
import {
  Container,
  Row,
  Col,
  Card,
  CardHeader,
  CardBody,
  Form,
  FormGroup,
  Label,
  Input,
  Button,
  FormFeedback,
} from "reactstrap";
import { toast } from "react-toastify";
import "../components/styles/signup.css";

import {
  isEmailAvailable,
  isUsernameAvailable,
  signup,
} from "../services/user-service";

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
    isError: false,
  });

  const handleChange = (e, property) => {
    setData({
      ...data,
      [property]: e.target.value,
    });
  };

  const submitForm = (event) => {
    event.preventDefault();

    if (error.isError) {
      toast.error("Form data is invalid, correct the errors and try again");
      return;
    }

    if (
      !data.firstName ||
      !data.lastName ||
      !data.userEmail ||
      !data.password ||
      !data.about ||
      !data.username
    ) {
      toast.error("All fields are mandatory");
      return;
    }

    isUsernameAvailable(data.username).then((response) => {
      if (response === true) {
        toast.success("Username is available");
      } if(response === false) {
        toast.error("Username is already taken");
        setError({
          errors: {
            response: {
              data: {
                validationFails: {
                  username: "Username is already taken",
                },
              },
            },
          },
          isError: true,
        });
      }
    });

    isEmailAvailable(data.userEmail).then((response) => {
      if (response === true) {
        toast.success("Email is available");
      }
      if (response === false) {
        toast.error("Email is already registered");
        setError({
          errors: {
            response: {
              data: {
                validationFails: {
                  userEmail: "Email is already registered",
                },
              },
            },
          },
          isError: true,
        });
      }
    });

    signup(data)
      .then((response) => {
        toast.success("Signup Successful");
        resetData();
      })
      .catch((error) => {
        setError({
          errors: error,
          isError: true,
        });
      });
  };

  const resetData = () => {
    setData({
      firstName: "",
      lastName: "",
      userEmail: "",
      password: "",
      about: "",
      username: "",
    });
    setError({
      errors: {},
      isError: false,
    });
  };

  return (
    <Container className="signup-container">
      <Row>
        <Col sm={{ size: "6", offset: "3" }}>
          <Card>
            <CardHeader>
              <div className="text-center">
                <h3>Fill information to Sign up</h3>
              </div>
            </CardHeader>
            <CardBody>
              <Form onSubmit={submitForm}>
                <FormGroup>
                  <Label for="firstName">First Name</Label>
                  <Input
                    type="text"
                    name="firstName"
                    id="firstName"
                    placeholder="Enter your first name"
                    onChange={(e) => handleChange(e, "firstName")}
                    value={data.firstName}
                    invalid={
                      error.errors?.response?.data?.validationFails?.firstName
                        ? true
                        : false
                    }
                  />
                  <FormFeedback>
                    {error.errors?.response?.data?.validationFails?.firstName}
                  </FormFeedback>
                </FormGroup>
                <FormGroup>
                  <Label for="lastName">Last Name</Label>
                  <Input
                    type="text"
                    name="lastName"
                    id="lastName"
                    placeholder="Enter your last name"
                    onChange={(e) => handleChange(e, "lastName")}
                    value={data.lastName}
                    invalid={
                      error.errors?.response?.data?.validationFails?.lastName
                        ? true
                        : false
                    }
                  />
                  <FormFeedback>
                    {error.errors?.response?.data?.validationFails?.lastName}
                  </FormFeedback>
                </FormGroup>
                <FormGroup>
                  <Label for="username">Username</Label>
                  <Input
                    type="text"
                    name="username"
                    id="username"
                    placeholder="Enter your username"
                    onChange={(e) => handleChange(e, "username")}
                    value={data.username}
                    invalid={
                      error.errors?.response?.data?.validationFails?.username
                        ? true
                        : false
                    }
                  />
                  <FormFeedback>
                    {error.errors?.response?.data?.validationFails?.username}
                  </FormFeedback>
                </FormGroup>
                <FormGroup>
                  <Label for="userEmail">Email</Label>
                  <Input
                    type="email"
                    name="userEmail"
                    id="userEmail"
                    placeholder="Enter your email"
                    onChange={(e) => handleChange(e, "userEmail")}
                    value={data.userEmail}
                    invalid={
                      error.errors?.response?.data?.validationFails?.userEmail
                        ? true
                        : false
                    }
                  />
                  <FormFeedback>
                    {error.errors?.response?.data?.validationFails?.userEmail}
                  </FormFeedback>
                </FormGroup>
                <FormGroup>
                  <Label for="password">Password</Label>
                  <Input
                    type="password"
                    name="password"
                    id="password"
                    placeholder="Enter your password"
                    onChange={(e) => handleChange(e, "password")}
                    value={data.password}
                    invalid={
                      error.errors?.response?.data?.validationFails?.password
                        ? true
                        : false
                    }
                  />
                  <FormFeedback>
                    {error.errors?.response?.data?.validationFails?.password}
                  </FormFeedback>
                </FormGroup>
                <FormGroup>
                  <Label for="about">About</Label>
                  <Input
                    type="text"
                    name="about"
                    id="about"
                    placeholder="Enter your about"
                    onChange={(e) => handleChange(e, "about")}
                    value={data.about}
                    invalid={
                      error.errors?.response?.data?.validationFails?.about
                        ? true
                        : false
                    }
                  />
                  <FormFeedback>
                    {error.errors?.response?.data?.validationFails?.about}
                  </FormFeedback>
                </FormGroup>
                <div className="text-center">
                  <Button className="signup-submit-btn">
                    Register
                  </Button>
                  <Button
                    onClick={resetData}
                    color="light"
                    className="ms-2 reset-btn"
                  >
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
