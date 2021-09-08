import React from 'react';
import {Button, Form, Modal} from "react-bootstrap";
import {departmentFakeData} from "../../Departments/DepartmentFakeData";

const EmployeeForm = (props) => {
    let {show, handleClose, isNew} = props;
    let {name, phone, gender, dob, dept, id} = props.data;

    const handleOnBlur = (event) => {
        console.log(event.target.checked)
    }

    const handleSubmit = (event) => {
        if(isNew){
            console.log(event.target.gender.value)
        }
        else {
            console.log(id)
            console.log(event.target.dob)
        }
        event.preventDefault();
    }
    return (
        <div>
            <Modal show={show} onHide={handleClose} centered>
                <Modal.Header closeButton>
                    {
                        isNew ?
                            <Modal.Title>Edit Employee</Modal.Title> :
                            <Modal.Title>Create New Employee</Modal.Title>
                    }

                </Modal.Header>
                <Modal.Body>
                    <Form onSubmit={handleSubmit}>
                        <Form.Group className="mb-3" controlId="name">
                            <Form.Label>Name</Form.Label>
                            <Form.Control defaultValue={name} type="text" placeholder="Name"/>
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="dob">
                            <Form.Label>Date of Birth</Form.Label>
                            <Form.Control defaultValue={dob} type="date" placeholder="Date of Birth"/>
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="gender">
                            <Form.Label>Gender</Form.Label>
                            <Form.Select defaultValue={gender? gender : "Choose..."}>
                                <option>Choose...</option>
                                <option>Male</option>
                                <option>Female</option>
                                <option>Other</option>
                            </Form.Select>
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="phone">
                            <Form.Label>Department Name</Form.Label>
                            <Form.Control defaultValue={name} type="text" placeholder="Phone"/>
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="dept">
                            <Form.Label>Department</Form.Label>
                            <Form.Select defaultValue={dept? dept : "Choose..."}>
                                <option>Choose...</option>
                                {
                                    departmentFakeData.map(dept => <option>{dept.name}</option>)
                                }
                            </Form.Select>
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
                </Modal.Footer>
            </Modal>
        </div>
    );
};

export default EmployeeForm;
