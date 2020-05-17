import React, { Component } from 'react';
import { Row, Col, Form, FormControl} from 'react-bootstrap';

import PageTemplate from './../common/pageTemplate';
import http from './../config/httpService';
import AlertBanner,{Types} from './../common/alert-banner';
import PaginationTable from './../common/pagination-table';

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
            empData:[],
            showAlertBanner:false,
            bannerMsg:'',
            type:'',
            timeOut:0,
            currentPage:1,
            pageSize:20,
            totalPages:1,
            searchValue:''
        }
    }
    componentDidMount(){
        this.loadEmployeeData(1);
    }

    loadEmployeeData = async(pageNumber, searchValue="")=>{  
        try{
            let url=`/employee/getEmployeesByPage/${pageNumber}`;
            if(searchValue.trim() !== ''){
                url = `${url}?search=${searchValue}`
            }
            const {data} = await http.get(url);
            const {employees,pageSize,totalPages,count} = data
            this.setState({empData:employees,pageSize, totalPages})
        }
        catch(error){
            if(error.response){
                const {data} =error.response;
                const message = data.description ? data.description : data.message;
                this.showAlertBanner(message, Types.ERROR);
            }
        }
    }

    showAlertBanner = (messaage, type, timeOut=0)=>{
        this.setState({showAlertBanner:true,bannerMsg:messaage,type,timeOut});
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

    onAlertBannerClose = ()=>{
        this.setState({showAlertBanner:false})
    }

    handlePageChange = (pageNumber, countPages) =>{
        this.loadEmployeeData(pageNumber);
        this.setState({currentPage: pageNumber})
    }

    handleSearch = (event) =>{
        event.preventDefault();
        const {value} = event.target;
        if(value.trim() !== '' && value.trim().length >=3){
            this.loadEmployeeData(1,value);
            this.setState({currentPage:1})
        }
        if(value === ''){
            this.loadEmployeeData(1);
            this.setState({currentPage:1})
        }
        this.setState({searchValue:value});
    }

    render() { 
        const {empData, showAlertBanner, bannerMsg, 
            type, timeOut,currentPage,totalPages, searchValue} = this.state;
       return (
        <div >
            <PageTemplate {...this.props} />
            
            <AlertBanner message={bannerMsg} 
                showBanner={showAlertBanner} 
                type={type} onClose={this.onAlertBannerClose} 
                timeOut={timeOut}/>

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
                            <input type="text" className="form-control"  
                                onChange={this.handleSearch} 
                                value={searchValue} placeholder="Search By First Name"/>
                        
                        </Col>

                    </Row>
                </div>
                <PaginationTable 
                    columns={this.columns} 
                    data={empData} primaryKey={"employeeId"} currentPage={currentPage}
                    onPageChange={this.handlePageChange} totalPages={totalPages} 
                    asyncData={true}
                />
            </div>
        </div>
       );
    }
}
 
export default Employees;
