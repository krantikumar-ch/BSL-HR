import React from 'react';
import { withRouter } from 'react-router-dom';
import {toast} from 'react-toastify';
import http from './httpService';



export default  function requireAuthentication(WrapperComponent, rest){

    class AuthenticationWrapper extends React.Component{
        
        state={
            loading:true
        }

        async componentDidMount(){
            const url =`/checkpageAcess${this.props.location.pathname}`;
            try{
                await http.post(url);
                this.setState({loading:false})
            }
            catch(error){
                if(error.response){
                    const {data} =error.response;
                    toast.error(`${data.description} Redirecting to login page`,{
                        autoClose: 3000,
                        position:'top-center',
                        hideProgressBar:false,
                    });
                    setTimeout(()=>{
                        this.props.history.replace("/");
                    },4000)
                }
            }
        }
        render(){
            const {loading} = this.state;
            if(loading){
                return "";
            }
            return(
            <WrapperComponent {...this.props} {...rest} />
            );
        }
    }
    return withRouter(AuthenticationWrapper);
}
