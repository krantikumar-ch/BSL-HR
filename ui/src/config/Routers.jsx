import {Switch, Route} from 'react-router-dom';
import React from 'react';
import Home from './../user/Home';
import Domains from '../domain/Domains';
import Reports from '../reports/Reports';
import Login from './../user/Login';

const RouterNavigation = (props) => {
    return ( 
        <div className="auth-wrapper">
           <div className="auth-inner">
            <Switch>
                <Route path="/home" render = {props => <Home sort="newest" {...props} />} />
                <Route path="/reports" render = {props => <Reports sort="newest" {...props} />}/>
                <Route path="/domains" render = {props => <Domains sort="newest" {...props} />}/>
                <Route path="/login" render = {props => <div className="login-router"> <Login sort="newest" {...props}  /> </div>} />
                <Route path="/" exact render = {props => <Login sort="newest" {...props} />}/>
            </Switch>
            </div> 
        </div>
        
     );
}
 
export default RouterNavigation;