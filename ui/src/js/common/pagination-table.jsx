import React, { Component } from 'react';
import PropTypes from 'prop-types';
import {Row, Col} from 'react-bootstrap';
import _ from 'lodash';
import CustomTable from './table';


class PaginationTable extends Component {
    state = { 
        holdInput:false,
        pageValue:''
     }

    onInputChange = (event, count)=>{       
        const pageNumber = _.toNumber(event.target.value);
        if(_.isNaN(pageNumber) || (pageNumber<1 || pageNumber>count)){
            this.setState({holdInput:true,pageValue:event.target.value});
            return;
        }
        this.setState({holdInput:false,pageValue:''});
        this.props.onPageChange(pageNumber, count);        
    }

    getPageData = (data, currentPage,pageSize)=>{
        if(data.length === 0 || data/pageSize <= pageSize) return data;
        const chunkData = _.chunk(data, pageSize);
        return chunkData[currentPage-1];
    }

    handlePageButton = (pageNumber) =>{
        this.props.onPageChange(pageNumber);
        this.setState({holdInput:false,pageValue:''});
    }

    render() { 
        const {holdInput, pageValue} = this.state;
        const {data, columns, primaryKey, classNames="", 
            style={}, pageSize, totalPages=1, 
            currentPage=1, asyncData} = this.props;
        const renderData = asyncData ? data : this.getPageData(data, currentPage, pageSize);
        const countPages = asyncData ? totalPages : ( _.ceil(data.length/pageSize)); 
        return (  
            <div>
                <div className={`custom-common-pagination-table ${classNames}`} style={style}>
                    <CustomTable data={renderData} columns={columns} primaryKey={primaryKey} />
                </div>
                <Row style={{"padding" : 5, "margin":0, "border":"1px solid #dee2e6"}}>
                    <Col lg={9}/>
                    <Col lg={3}>
                        <button type="button" className="btn btn-primary bt-sm common-custom-pagination-button" 
                             disabled={currentPage === 1}
                            onClick={()=> {this.handlePageButton(1)}}>{"<<"}</button>

                        <button type="button"  className="btn btn-primary  bt-sm common-custom-pagination-button" 
                            disabled={currentPage === 1}
                            onClick={()=> {this.handlePageButton(currentPage-1)}}>{"<"}</button>

                        <input type="text" className="form-control common-custom-pagination-button" 
                            style={{"width": '20%', display: "inline-block"}} 
                            value={holdInput ? pageValue : currentPage} onChange={(event)=>this.onInputChange(event,countPages)}/>
                        <span className=""> of {countPages}</span>

                        <button type="button"  className="btn btn-primary  bt-sm  common-custom-pagination-button" 
                            disabled={currentPage === countPages}
                            onClick={()=> {this.handlePageButton(currentPage+1)}}  >{">"}</button>

                        <button type="button"  className="btn btn-primary bt-sm common-custom-pagination-button" 
                            disabled={currentPage === countPages}
                            onClick={()=> {this.handlePageButton(countPages)}} >{">>"}</button>
                    </Col>
                </Row>
            </div>
        );
    }
}

PaginationTable.propTypes={
    columns: PropTypes.array.isRequired,
    primaryKey: PropTypes.string.isRequired,
    data: PropTypes.array.isRequired,
    onPageChange: PropTypes.func.isRequired,
    classNames: PropTypes.string,
    style: PropTypes.object,
    pageSize: PropTypes.number,
    totalPages: PropTypes.number,
    currentPage: PropTypes.number,
    asyncData: PropTypes.bool
}
PaginationTable.defaultProps ={
    pageSize:20,
    asyncData:false
}
export default PaginationTable;