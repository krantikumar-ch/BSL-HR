import React, { Component } from 'react';
import PageTemplate from './../common/pageTemplate';
import httpService from '../config/httpService';
import Table from './../common/table';

import './../../styles/departments.css';

class Departments extends Component{

    constructor(props){
        super(props);
        this.state = {
            data:[]
        }
        this.departments = [];
        this.columnsConfig=[{label:"Department Name", name:"departmentName"}];
    }
    
    async componentDidMount(){
        try{
            const {data} = await httpService.get("/getAllDepartments");
            this.departments = data;
            this.setState({data})
        }
        catch(error){

        }
    }

    render(){
        const {data} = this.state;
        return (
            <div>
                <PageTemplate {...this.props} />
                
                <div className="department-list">
                    <Table data={data} columns={this.columnsConfig} 
                        primaryKey="departmentId"/>
                    </div>
                </div>
           
           );
    }
}

export default Departments
