import React, { Component } from 'react';

class BaseComponent extends Component {
    state = {  test:'nineio'}
    render() { 
        return ( <h1>In Parent</h1> );
    }
}
 
export default BaseComponent;