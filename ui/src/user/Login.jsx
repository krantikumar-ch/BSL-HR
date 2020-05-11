import React, { Component } from 'react';
import {Form, Button} from 'react-bootstrap'; 
import {toast} from 'react-toastify';
import axios from 'axios';
import endPoints from './../config/endpoints';


class Login extends Component {
    state = {  
        userName:'',
        password:'',
        emptyName:false,
        emptyPassword:false,
    }
    ;
    handleChange = (event) =>{
        const state = this.state;
        state[event.target.name] = event.target.value;
        this.setState({state});
    };

     onSubmit = async(event) =>{
         event.preventDefault();
        const {userName, password} = this.state;
        const emptyName = userName.trim() === '';
        const emptyPassword = password === '';
        this.setState({...this.state, emptyName,  emptyPassword});
       if(emptyName || emptyPassword){    
           return;
       }
       const { USER_LOGIN_API } = endPoints;
       try{
            const promise = await axios.post(USER_LOGIN_API,{userName,password});
            localStorage.setItem("authToken",promise.data);
            this.props.history.replace("/home");
       }
       catch(error){
           if(error.response){
                const {data} = error.response;
                toast.error(data.description,{className:'login-error-toast'});
           }
       }
    }

    render() { 
        const{userName, password, emptyName, emptyPassword, errorMsg} = this.state;
       return(
           <div className="login-main">
                <h1 className="login-header">Sign In</h1>
                <Form >
                    <Form.Group controlId="formBasicEmail">
                    <Form.Label>User Name</Form.Label>
                    <Form.Control type="text" placeholder="Enter user name" 
                        name="userName" value={userName} 
                        onChange={this.handleChange}/>
                    { emptyName && <Form.Text className="text-muted">
                        User name should not be empty
                        </Form.Text>}
                    </Form.Group>

                    <Form.Group controlId="formBasicPassword">
                    <Form.Label>Password</Form.Label>
                    <Form.Control type="password" 
                        name="password" value={password} 
                        onChange={this.handleChange}
                        placeholder="Password" />
                        { emptyPassword && <Form.Text className="text-muted" >
                            Please enter password
                        </Form.Text> }
                    </Form.Group>
                    

                    <Button variant="primary" type="submit" 
                        style={{width:"100%"}} onClick ={this.onSubmit}>   Submit </Button>
                </Form>
        </div>
       )
    }
}
 
export default Login;