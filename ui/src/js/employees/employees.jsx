import React, { Component } from 'react';
import { Row, Col, Form, FormControl} from 'react-bootstrap';

import Table from './../common/table';
import PageTemplate from './../common/pageTemplate';
import http from './../config/httpService';

import './../../styles/employees.css';

class Employees extends Component {
    
    constructor(props){
        super(props);
        this.employeeData = [];
        this.columns=[
            {
                name:"checkbox",
                headerContent: ()=>{
                    return(<div className="checkbox">
                        <input type="checkbox"  onChange ={(event) => this.handleSelectAll(event)}/>
                    </div>)
                },
                columnContent: (employee) => { return (
                    <div className="checkbox">
                        <input type="checkbox" checked={employee.selected ? true : false} onChange ={(event) => this.handleSelect(event, employee)}/>
                    </div>)}
            },
            {
                name:"edit",
                label:"",
                columnContent: (employee) => { return (<div className="employee-cell-icon">
                        <i className="fa fa-pencil" aria-hidden="true" 
                            onClick= {() =>{this.handleEdit(employee)}}></i>
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
                name:"email",
                label:"Email",
            },
            {
                name:"salary",
                label:"Salary",
            },
            {
                name:"phoneNumber",
                label:"Phone Number",
            },
            {
                name:"delete",
                label:"",
                columnContent: (employee) => { return (<div className="employee-cell-icon">
                    <i className="fa fa-trash" aria-hidden="true" title="Delete" onClick= {() =>{this.handleDelete(employee)}}></i>
                    </div>)}

            },
    
    ]
        this.state = {
            empData:[]
        }
    }
    handleSelectAll = (event)=>{
        const empData = [...this.employeeData];
        empData.forEach(employee =>{
            employee.selected = event.target.checked;
        })
        this.employeeData = empData;
        this.setState({empData});
    }
    
    handleSelect = (event, employeeItem)=>{
        const empData = [...this.employeeData];
        empData.forEach(employee =>{
            if(employee.employeeId === employeeItem.employeeId){
                employee.selected = event.target.checked;
                return;
            }
        })
        this.employeeData = empData;
        this.setState({empData});
    }

    handleEdit = (employeeItem )=>{
        console.log(" handleEdit employeeItem",employeeItem);
    }

    handleDelete = (employeeItem) =>{
        console.log("handleDelete employeeItem",employeeItem);
    }

    async componentDidMount(){
        try{
            const response = await http.get("/employee/getAllEmployees");
            this.employeeData = [... response.data];
            this.setState({empData:[... response.data]});
        }
        catch(error){
            console.log("error");
        }
    }
    render() { 
        const {empData} = this.state;
       return (
        <div >
            <PageTemplate {...this.props} />
            <div className="router-main-content emp-main">
                <div className="emp-main-header">
                <Row>
                    <Col lg={2}>
                        <div className ="emp-icons">
                            <i className="fa fa-fw  fa-plus-square emp-icon" aria-hidden="true" title="Create"></i>
                            <i className="fa fa-fw fa-file-excel-o emp-icon" aria-hidden="true" title="Export As Excel"></i>
                            <i className="fa fa-fw  fa-trash emp-icon" aria-hidden="true" title="Delete"></i>
                        </div>
                    </Col>
                    <Col lg={4}>
                    <Form className="form-center">
                        <FormControl type="text" placeholder="Search" className="" />
                    </Form>
                    </Col>

                </Row>
                </div>
                <div className="emp-list">
                    <Table data={empData} columns={this.columns} primaryKey="employeeId" />
                </div>
            </div>
        </div>
       );
    }
}
 
export default Employees;
