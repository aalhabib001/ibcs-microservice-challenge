import React from 'react';
import {Button, Modal} from "react-bootstrap";

const DeleteModal = (props) => {
    const {show, handleClose} = props;

    return (
        <Modal show={show} onHide={handleClose} centered>
            <Modal.Header closeButton>
                <Modal.Title>Are You Sure?</Modal.Title>
            </Modal.Header>
            <Modal.Body>Are you sure wants to delete this?</Modal.Body>
            <Modal.Footer>
                <Button variant="secondary" onClick={handleClose}>
                    Close
                </Button>
                <Button variant="danger" onClick={handleClose}>
                    Delete
                </Button>
            </Modal.Footer>
        </Modal>
    );
};

export default DeleteModal;
