import React from 'react';
import {Button, Modal} from "react-bootstrap";
import axios from "axios";
import {useSnackbar} from 'react-simple-snackbar'

const DeleteModal = (props) => {
    const {show, handleClose, isEmployee, id, value, setValue} = props;
    const [openSnackbar] = useSnackbar()


    const handleDelete = async () => {
        if (isEmployee) {

        } else {
            await axios({
                method: 'delete',
                url: 'http://localhost:8006/departments/' + parseInt(id),
                headers: {'Content-Type': 'application/json'}
            })
                .then(res => {
                    console.log(res)
                    console.log(value)
                    setValue(value + 1)
                    openSnackbar(res.data.message)
                    handleClose()
                })
                .catch(error => {
                    console.log(error)
                    let errorData = error.response.data;
                    openSnackbar(errorData.message)
                })
        }
    }

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
                <Button variant="danger" onClick={handleDelete}>
                    Delete
                </Button>
            </Modal.Footer>
        </Modal>
    );
};

export default DeleteModal;
