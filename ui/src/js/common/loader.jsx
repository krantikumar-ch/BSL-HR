import React, { Component } from 'react';

class Loader extends Component {
    state = {  }
    render() { 
        return ( 
            <div className="loader-main">
                <div className="loader-contet">
                    <i class="fa fa-spinner" aria-hidden="true"></i>
                </div>
            </div>
         );
    }
}
 
export default Loader;