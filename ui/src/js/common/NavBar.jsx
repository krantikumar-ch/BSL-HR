import React, { Component } from 'react';
import NavIcon from 'react-bootstrap';
import {Link} from 'react-router-dom';
import './../../styles/template.css';
class NavBar extends Component {
    state = {  }
    items= [
        {
          path: '/home', /* path is used as id to check which NavItem is active basically */
          name: 'Home',
          css: 'fa fa-fw fa-home',
          key: 1 /* Key is required, else console throws error. Does this please you Mr. Browser?! */
        },
        {
          path: '/employees',
          name: 'Employees',
          css: 'fa fa-fw fa-clock',
          key: 2
        },
        {
          path: '/departments',
          name: 'Departments',
          css: 'fas fa-hashtag',
          key: 3
        },
      ]
    render() { 
        return (
            <div className="side-nav">  
                
                {this.items.map(item =>{
                    return (<div className='styled-nav-item'><Link key={item.key} to={item.path} className={item.css} > 
                     {<div className='styled-nav-item'/>}
                   
                </Link>
                </div>)
                })}
            </div>
        );
    }
}
 
export default NavBar;