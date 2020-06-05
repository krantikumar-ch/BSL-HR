import React, { Component } from 'react';
import {toast} from 'react-toastify';
import PageTemplate from './../common/pageTemplate';
import httpService from '../config/httpService';
import Loader from './../common/loader';
import CustomTable from '../common/table';
import EditUser from './user-edit';

import './../../styles/users.css';

class Users extends Component {

    state = {  
        users:[],
        showLoader:false,
        userId:null,
        showUser:false}

    columnsConfig =[
        {
            name:"edit",
            label:"",
            columnContent: (user) => { return (<div className="employee-cell-icon">
                    <i className="fa fa-pencil" aria-hidden="true" 
                        onClick= {() =>{this.handleEdit(user)}}></i>
                </div>)}
        },
        {
            name:"firstName",
            label:"First Name",
        },
        {
            name:"lastName",
            label:"Last Name",
        },
        {
            name:"userName",
            label:"User Name",
        },
        {
            name:"email",
            label:"Email",
        }

    ]

    
    async componentDidMount(){
        this.setState({showLoader:true});
        try{
            const {data = []} = await httpService.get("/getAllUsers");
            this.setState({users:data})
        }
        catch(error){
            if(error.response){
                const {data} = error.response;
                toast.error(data.description ? data.description: data.message,
                    {position: "top-center"});
            }
        }
        finally{
            this.setState({showLoader:false});
        }
    }
    handleEdit = (user)=>{
        this.setState({userId:user.userId, showUser:true});
    }

    hideUserEdit = ()=>{
        this.setState({showUser:false})
    }

    downloadExcel = async() =>{
        const columns = this.columnsConfig.filter( column => column.label && column.label.trim() !== '');
        this.setState({showLoader:true});
        try{
            const {data, headers} = await httpService.post("/downloadUsers",
                columns,{responseType:"blob"}); //important 
            const url = window.URL.createObjectURL(new Blob([data]));
            const link = document.createElement('a');
            link.href = url;
            link.setAttribute('download', headers['file-name']); 
            document.body.appendChild(link);
            link.click();
        }
        catch(error){
            if(error.repsonse){
                const {data} = error.response;
                toast.error(data.message,{position:'top-center'})
            }
        }
        finally{
            this.setState({showLoader:false});
        }
    }
    render() { 
        const {showLoader, users, userId, showUser} = this.state;
        const disableClass = showLoader ? "common-disable-all" :  '';
        return (<div className={disableClass}>
            <Loader show={showLoader}/>
            <PageTemplate {...this.props} />
            <div className="router-main-content user-main-content">
               {showUser && <EditUser userId={userId} showUser={showUser} onHide={this.hideUserEdit}/> } 
                <div className="row">
                    <div className="col-md-3">
                        <i className="fa fa-fw  fa-plus-square emp-icon" aria-hidden="true" title="Create"></i>
                        <i className="fa fa-fw fa-file-excel-o emp-icon" aria-hidden="true" title="Export All" onClick={this.downloadExcel}></i>
                    </div>

                </div>
                <CustomTable data={users} columns={this.columnsConfig} 
                primaryKey="userId" />
            </div>
        </div>)
    }
}
 
export default Users;