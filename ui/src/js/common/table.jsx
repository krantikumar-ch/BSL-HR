import React, { Component } from 'react';
import PropTypes from 'prop-types';
import {Table} from 'react-bootstrap';

class CustomTable extends Component {
    state = {  }

    renderHeader = () =>{
        const {columns} = this.props;
        return(
            <thead>
                <tr>
                    {
                     columns.map(column => {
                        return <th key={column.name}>{this.renderHeaderCell(column)}</th>
                        })
                    }
                </tr>
            </thead>
        )
    }
    renderCell = (item,column) =>{

        if(column.columnContent){
            return column.columnContent(item);
        }
        return item[column.name];
    }

    renderHeaderCell = (column)=>{
        if(column.headerContent){
            return column.headerContent(column);
        }
        return column.label;
    }

    renderColumns = () =>{
        const {columns, primaryKey, data} = this.props;
        const tableColumns = data.map(item =>{

            return(
                <tr key={item[primaryKey]}>
                {columns.map(column =>{
                    return (
                        <td key={`${item[primaryKey]}-${column.name}`}>
                            {this.renderCell(item,column)}
                        </td>)
                })}
                </tr>
                
            )
        })
        return <tbody>{tableColumns}</tbody>
    }
    render() { 
        const {style={}} = this.props;
        return (
            <Table striped bordered hover size="sm" style={style}>
                {this.renderHeader()}
                {this.renderColumns()}
            </Table>
        );
    }
}

CustomTable.propTypes ={
    columns: PropTypes.array.isRequired,
    primaryKey: PropTypes.string.isRequired,
    data: PropTypes.array.isRequired,
    style: PropTypes.object,
}

export default CustomTable;