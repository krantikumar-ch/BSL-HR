import React from 'react';
import { PropTypes } from 'prop-types';

class AlertBanner extends React.Component {
    
    render() { 
        const { type=Types.INFO, message, 
            style={}, classNames="", showBanner, onClose, timeOut=0} = this.props;
            if(!showBanner){
                return null;
            }
           if(timeOut >0){
               setTimeout( () => onClose(), timeOut*1000);
           }
        return ( 
            <div className={`alert  alert-dismissible ${type} fade show
                common-custom-alert-banner ${classNames}`}
                style={{style}}
                 role="alert">
                <strong>{message}</strong>
                <button type="buteton" className="close" data-dismiss="alert" 
                    aria-label="Close" onClick={onClose}>
                    <span aria-hidden="true">&times;</span>
                </button>
          </div>
         );
    }
}
 
export default AlertBanner;

AlertBanner.propTypes = {
    message: PropTypes.string.isRequired,
    type: PropTypes.string,
    style: PropTypes.object,
    classNames:PropTypes.string,
    onClose: PropTypes.func,
    showBanner: PropTypes.bool,
    timeOut:PropTypes.number
}

export const Types={
    WARNING:"alert-warning",
    SUCCESS:'alert-success',
    ERROR:"alert-danger",
    INFO:"alert-info"
 }