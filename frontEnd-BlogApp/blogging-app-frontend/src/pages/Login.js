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
const Login = () => {
  return (
    <Container style={{ marginTop: "30px" }}>
      <Row>
        <Col sm={{ size: "6", offset: "3" }}>
          <Card color="dark" inverse>
            <CardHeader>
              <div className="text-center">
                <h3>Login</h3>
              </div>
            </CardHeader>
            <CardBody>
              {/* creating form */}

              <Form>
                <FormGroup>
                  <Label for="email">Email</Label>
                  <Input
                    type="email"
                    name="email"
                    id="email"
                    placeholder="Enter your email"
                  />
                </FormGroup>
                <FormGroup>
                  <Label for="password">Password</Label>
                  <Input
                    type="password"
                    name="password"
                    id="password"
                    placeholder="Enter your password"
                  />
                </FormGroup>
                <div className="text-center">
                  <Button outline color="light">
                    Login
                  </Button>
                  <Button color="light" className="ms-2">
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

export default Login;
