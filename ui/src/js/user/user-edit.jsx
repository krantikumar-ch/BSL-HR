import React, { Component } from 'react';
import PropTypes from 'prop-types';
import {Modal} from 'react-bootstrap';

class EditUser extends Component {
    state = {  }
    render() { 
        const {showUser, userId, onHide} = this.props;
        return (  
            <Modal  size="lg"
            show={showUser}
            aria-labelledby="contained-modal-title-vcenter"
            onHide={onHide}
            backdrop="static"
            centered>
                <Modal.Header closeButton>
                    <Modal.Title id="contained-modal-title-vcenter">
                    Modal heading
                    </Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <h4>Centered Modal</h4>
                    <p>
                    Cras mattis consectetur purus sit amet fermentum. Cras justo odio,
                    dapibus ac facilisis in, egestas eget quam. Morbi leo risus, porta ac
                    consectetur ac, vestibulum at eros.
                    </p>
                </Modal.Body>
                {/* <Modal.Footer>
                    <Button onClick={props.onHide}>Close</Button>
                </Modal.Footer> */}
            </Modal>
            
        );
    }
}

EditUser.propTypes ={
    userId: PropTypes.number,
    showUser: PropTypes.bool,
    onHide: PropTypes.func
}
export default EditUser