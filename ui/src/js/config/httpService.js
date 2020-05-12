import axios from 'axios';

//axios.defaults.headers.common["authToken"] = localStorage.getItem("authToken"); 

 axios.interceptors.request.use(config =>{
     if(config.url.indexOf("/authenticate") ===-1){
        config.headers["authToken"] = sessionStorage.getItem("authToken");
     }
     else{
        localStorage.removeItem("authToken");
     }
    return config;
 })

export const getServiceUrl =  (uri)=> `http://localhost:9090/${uri}`

export default {
    get : axios.get,
    post: axios.post,
    put: axios.put,
    delete: axios.delete,
    getServiceUrl
}