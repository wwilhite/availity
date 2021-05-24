import React from "react";
import {Button, Form} from "react-bootstrap";

class App extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      firstName: "",
      lastName: "",
      npi: "",
      address: "",
      tel: "",
      email: ""
    }

    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleSubmit() {
    console.log("Submitting form:");
    console.log(this.state)
  }

  handleChange(event) {
    this.setState({
      [event.target.name]: event.target.value
    });
  }

  render() {
    return (
      <Form onSubmit={this.handleSubmit}>
        <h2>Enrollment Form</h2>
        <Form.Group>
          <Form.Label>
            First Name:
          </Form.Label>
          <Form.Control type="text" name="firstName" value={this.state.firstName} onChange={this.handleChange}/>
        </Form.Group>
        <Form.Group>
          <Form.Label>
            Last Name:
          </Form.Label>
          <Form.Control type="text" name="lastName" value={this.state.lastName} onChange={this.handleChange}/>
        </Form.Group>
        <Form.Group>
          <Form.Label>
            NPI Number:
          </Form.Label>
          <Form.Control type="text" name="npi" value={this.state.npi} onChange={this.handleChange}/>
        </Form.Group>
        <Form.Group>
          <Form.Label>
            Business Address:
          </Form.Label>
          <Form.Control type="text" name="address" value={this.state.address} onChange={this.handleChange}/>
        </Form.Group>
        <Form.Group>
          <Form.Label>
            Telephone Number:
          </Form.Label>
          <Form.Control type="tel" name="tel" value={this.state.tel} onChange={this.handleChange}/>
        </Form.Group>
        <Form.Group>
          <Form.Label>
            Email Address:
          </Form.Label>
          <Form.Control type="email" name="email" value={this.state.email} onChange={this.handleChange}/>
        </Form.Group>
        <Button type="submit" name="submit">Submit</Button>
      </Form>
    )
  }
}

export default App;
