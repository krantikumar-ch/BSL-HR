import {Switch, Route} from 'react-router-dom';
import {Redirect} from 'react-router';
import React from 'react';
import Home from './../user/Home';
import Departments from '../departments/Departments';
import Employees from '../employees/Employees';
import Login from './../user/Login';

const RouterNavigation = (props) => {
    return ( 
            <Switch>
                
                <Route path="/home" render = {props => <Home sort="newest" {...props} />} />
                <Route path="/employees" render = {props => <Employees sort="newest" {...props} />}/>
                <Route path="/departments" render = {props => <Departments sort="newest" {...props} />}/>
                <Route path="/login" render = {props =><Login sort="newest" {...props}  /> } />
                <Redirect from='/' to="/login" />
            </Switch>
     );
}
 
export default RouterNavigation;