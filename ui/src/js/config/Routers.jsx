import {Switch, Route} from 'react-router-dom';
import {Redirect} from 'react-router';
import React from 'react';
import Home from '../user/home';
import Departments from '../departments/departments';
import Employees from '../employees/employees';
import Login from '../user/login';
import requireAuthentication from './authorizationWrapper';
import Users from '../user/users';

const RouterNavigation = (props) => {
    return ( 
            <Switch>
                <Route  path="/home" exact render={requireAuthentication(Home, {...props})} />
                <Route path="/employees" exact render={requireAuthentication(Employees, {...props})} />
                <Route path="/departments" exact  render={requireAuthentication(Departments, {...props})} />}/>
                <Route path="/users" exact render={requireAuthentication(Users,{...props})}/>
                <Route path="/login" exact component={Login} />
                <Redirect from='/' to="/login" />
                
            </Switch>
     );
}
 
export default RouterNavigation;