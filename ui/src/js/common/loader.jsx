import React  from 'react';
import PropTypes from 'prop-types';
import ClipLoader from "react-spinners/ClipLoader";


const Loader = (props) => {
    const {classNames='' , styles={}, show} = props;
    if(!show) return null;

    return ( 
        <div className={`commmon-content-loader-main ${classNames}`} style={styles}>
            <ClipLoader
            size={150}
            color={"#3c95f5"}
            css={`top:35%; left:45%;position:absolute`}
            />
          </div>
      
     );
}

Loader.propTypes={
    classNames : PropTypes.string,
    style:PropTypes.object,
    show: PropTypes.bool.isRequired
}
 
export default Loader;