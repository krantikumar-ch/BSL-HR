import React, { Component } from 'react';
import PageTemplate from './../common/pageTemplate';

class Users extends Component {
    state = {  }
    render() { 
        return (<div >
        <PageTemplate {...this.props} />
        <div className="router-main-content">
        <h1>Users</h1>
        </div>
    </div>)
    }
}
 
export default Users;