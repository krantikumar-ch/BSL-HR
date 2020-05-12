import React from 'react';
import PageTemplate from '../common/pageTemplate';


class Home extends React.Component {
    state = {  }
    render() { 
       return (
           <div >
               <PageTemplate {...this.props} />
               <div className="router-main-content">
                <h1>Home</h1>
               </div>
           </div>
       )
    }
}
 
export default Home;
