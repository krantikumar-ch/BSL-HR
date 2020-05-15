import axios from 'axios';

//axios.defaults.headers.common["authToken"] = localStorage.getItem("authToken"); 

 axios.interceptors.request.use(config =>{
    console.log("config ",config)
     if(config.url.indexOf("/authenticate") ===-1){
        config.headers["authToken"] = sessionStorage.getItem("authToken");
     }
     else{
        localStorage.removeItem("authToken");
     }
    return config;
 })

export default {
    get : axios.get,
    post: axios.post,
    put: axios.put,
    delete: axios.delete,
    fetch:axios
}