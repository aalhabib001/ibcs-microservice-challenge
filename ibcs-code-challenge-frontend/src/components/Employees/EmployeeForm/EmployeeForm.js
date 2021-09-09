import React, {useContext, useState} from 'react';
import {Button, Form, Modal} from "react-bootstrap";
import {departmentFakeData} from "../../Departments/DepartmentFakeData";
import axios from "axios";
import {useSnackbar} from 'react-simple-snackbar'
import {DeptContext} from "../../../App";

const EmployeeForm = (props) => {
    let {show, handleClose, isNew, value, setValue} = props;
    let {name, mobile, code, gender, dateOfBirth, departmentName, id} = props.data;

    const [openSnackbar] = useSnackbar()
    const [departments, setDepartments] = useContext(DeptContext);


    const [employeeData, setEmployeeData] = useState({
        code: null,
        name: null,
        dateOfBirth: null,
        gender: null,
        mobile: null,
        departmentId: null
    });

    const handleSubmit = async (event) => {
        event.preventDefault();

        (isNew === 'true') ? employeeData.code = event.target.code.value : employeeData.code = 0;


        employeeData.name = event.target.name.value
        employeeData.dateOfBirth = event.target.dateOfBirth.value
        employeeData.gender = event.target.gender.value
        employeeData.mobile = event.target.mobile.value
        // employeeData.departmentId = 3
        employeeData.departmentId = event.target.departmentId.value


        console.log(isNew)
        if (isNew === "true") {
            console.log(employeeData);

            await axios({
                method: 'post',
                // url: 'http://localhost:8005/employees/',
                url: 'http://localhost:8000/api/employee-service/employees/',
                headers: {'Content-Type': 'application/json'},
                data: employeeData
            })
                .then(res => {
                    setValue(value + 1)
                    openSnackbar(res.data.message)
                    handleClose()
                })
                .catch(error => {
                    let errorData = error.response.data;
                    openSnackbar(errorData.message)
                })
        } else {
            console.log(employeeData);
            console.log(id)

            axios({
                method: 'put',
                // url: 'http://localhost:8005/employees/' + parseInt(id),
                url: 'http://localhost:8000/api/employee-service/employees/' + parseInt(id),
                headers: {'Content-Type': 'application/json'},
                data: employeeData
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

    let deptId;
    for (let i = 0; i < departments.length; i++) {
        if(departments[i].name === departmentName){
            deptId = departments[i].id;
            break;
        }
    }

    return (
        <div>
            <Modal show={show} onHide={handleClose} centered>
                <Modal.Header closeButton>
                    {
                        (isNew === 'false') ?
                            <Modal.Title>Edit Employee</Modal.Title> :
                            <Modal.Title>Create New Employee</Modal.Title>
                    }

                </Modal.Header>
                <Modal.Body>
                    <Form onSubmit={handleSubmit}>
                        {
                            (isNew === 'true') ?
                                <Form.Group className="mb-3" controlId="code">
                                    <Form.Label>Code</Form.Label>
                                    <Form.Control defaultValue={code} type="number" placeholder="Code"/>
                                </Form.Group> :
                                <></>
                        }

                        <Form.Group className="mb-3" controlId="name">
                            <Form.Label>Name</Form.Label>
                            <Form.Control defaultValue={name} type="text" placeholder="Name"/>
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="dateOfBirth">
                            <Form.Label>Date of Birth</Form.Label>
                            <Form.Control defaultValue={dateOfBirth} type="date"
                                          placeholder="Date of Birth"/>
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="gender">
                            <Form.Label>Gender</Form.Label>
                            <Form.Select defaultValue={gender ? gender : "Choose..."}>
                                <option>Choose...</option>
                                <option>MALE</option>
                                <option>FEMALE</option>
                                <option>OTHERS</option>
                            </Form.Select>
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="mobile">
                            <Form.Label>Mobile No</Form.Label>
                            <Form.Control defaultValue={mobile} type="text" placeholder="Mobile"/>
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="departmentId">
                            <Form.Label>Department</Form.Label>
                            <Form.Select defaultValue={departmentName ? deptId : "Choose..."}>
                                <option>Choose...</option>
                                {
                                    departments.map(dept => <option value={dept.id}>{dept.name}</option>)
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
