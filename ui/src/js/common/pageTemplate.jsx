import React, { Component } from 'react';
import SideBar from './sideBar';
import NavBar from './navBar';
import './../../styles/template.css';

class PageTemplate extends Component {
    state = {  }
    render() { 
        return (
            <div className="template-main">
                <NavBar {...this.props}/>
                <SideBar {...this.props}/>
            </div>
          );
    }
}
 
export default PageTemplate;