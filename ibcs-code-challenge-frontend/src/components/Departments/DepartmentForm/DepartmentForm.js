import React from 'react';
import {Button, Form, Modal} from "react-bootstrap";

const DepartmentForm = (props) => {

    let {show, handleClose, name, isActive, isNew, id} = props;

    const handleOnBlur = (event) => {
        console.log(event.target.checked)
    }

    const handleSubmit = (event) => {
        if(isNew){
            console.log(event.target.deptName.value)
            console.log(event.target.isActive.checked)
        }
        else {
            console.log(id)
            console.log(event.target.deptName.value)
            console.log(event.target.isActive.checked)
        }
        event.preventDefault();
    }
    return (
        <div>
            <Modal show={show} onHide={handleClose} centered>
                <Modal.Header closeButton>
                    {
                        isNew ?
                            <Modal.Title>Create New Department</Modal.Title> :
                            <Modal.Title>Edit Department</Modal.Title>
                    }
                </Modal.Header>
                <Modal.Body>
                    <Form onSubmit={handleSubmit}>
                        <Form.Group className="mb-3" controlId="deptName">
                            <Form.Label>Department Name</Form.Label>
                            <Form.Control defaultValue={name} type="text" placeholder="Dept Name"/>
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="isActive">
                            <Form.Check onBlur={handleOnBlur} defaultChecked={isActive} type="checkbox" label="Active"/>
                        </Form.Group>
                        <Button variant="primary" type="submit">
                            Save
                        </Button>
                    </Form>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleClose}>
                        Close
                    </Button>
                    {/*<Button variant="primary" onClick={handleClose}>*/}
                    {/*    Save*/}
                    {/*</Button>*/}
                </Modal.Footer>
            </Modal>
        </div>
    );
};

export default DepartmentForm;
