import React, { Component } from 'react';
import PageTemplate from './../common/pageTemplate';

class Departments extends Component{
    render(){
        return (
            <div >
                <PageTemplate {...this.props} />
                <div className="router-main-content">
                <h1>Departments</h1>
                </div>
            </div>
           );
    }
}

export default Departments