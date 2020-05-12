import {Switch, Route} from 'react-router-dom';
import {Redirect} from 'react-router';
import React from 'react';
import Home from './../user/Home';
import Departments from '../departments/Departments';
import Employees from '../employees/Employees';
import Login from './../user/Login';
import requireAuthentication from './AuthorizationWrapper';

const RouterNavigation = (props) => {
    return ( 
            <Switch>
                
                <Route  path="/home" exact render={requireAuthentication(Home, {...props})} />
                <Route path="/employees" exact render={requireAuthentication(Employees, {...props})} />
                <Route path="/departments" exact  render={requireAuthentication(Departments, {...props})} />}/>
                <Route path="/login" exact component={Login} />
                <Redirect from='/' to="/login" />
                
            </Switch>
     );
}
 
export default RouterNavigation;