import React, { Component } from 'react'
import {Link} from 'react-router-dom';
import './../../styles/template.css';
class SideBar extends Component {
    state = {  }
    items= [
        {
          path: '/home', /* path is used as id to check which NavItem is active basically */
          name: 'Home',
          css: 'fa fa-fw fas fa-home',
          key: 1 /* Key is required, else console throws error. Does this please you Mr. Browser?! */
        },
        {
          path: '/employees',
          name: 'Employees',
          css: 'fa fa-fw fas fa-users',
          key: 2
        },
        {
          path: '/departments',
          name: 'Departments',
          css: 'fa fa-fw fas  fa-bar-chart',
          key: 3
        },
        {
          path: '/users',
          name: 'User',
          css: 'fa fa-fw fas  fa-user',
          key: 4
        },
      ]

    handleLabelNavigation = (navItem)=>{
      this.props.history.push(navItem.path)
    }
    render() { 
        return (
            <div className="side-nav">  
                
                {this.items.map(item =>{
                    return (
                    <div className='styled-nav-item' key={item.key}>
                      <Link  to={item.path} title={item.name} > 
                          <i className={item.css}></i>
                        </Link>
                        <span className="styled-nav-label" onClick={() => this.handleLabelNavigation(item)}>{item.name}</span>
                    </div>)
                })}
            </div>
        );
    }
}
 
export default SideBar;