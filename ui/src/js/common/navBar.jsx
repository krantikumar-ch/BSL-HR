import React, { Component } from 'react'
import { Row, Col, Form, FormControl } from 'react-bootstrap';

class NavBar extends Component {
    logout = ()=>{
      this.props.history.replace("/")
    }
    render(){
      return (
      <div className="nav-header">
        <Row style={{margin:0}}>
          <Col lg={3}>
            <div className="nav-logo">
              Hi {sessionStorage.getItem("userName")}
            </div>
        </Col>
        <Col lg={6}>
          <Form className="form-center" onSubmit={(event)=> event.preventDefault()}>
            <FormControl type="text" placeholder="Search" className="" />
          </Form>
        </Col>

        <Col lg={3}>
          <div className="user-info">
            <i className="fa fa-fw fa-user-circle-o" aria-hidden="true" title="Edit User" ></i>
            &nbsp;&nbsp;&nbsp;&nbsp;
            <i className="fa fa-fw  fa-sign-out" aria-hidden="true" title="Logout" onClick={this.logout}></i>
          </div>
        </Col>
        </Row>
      </div>);
    }
}
 
export default NavBar;
