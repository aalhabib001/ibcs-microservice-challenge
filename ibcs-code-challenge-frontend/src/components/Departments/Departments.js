import React, {useEffect, useState} from 'react';
import {Button, Container, Table} from "react-bootstrap";
import DeptTableRow from "./DeptTableRow/DeptTableRow";
import {departmentFakeData} from "./DepartmentFakeData";
import './Department.css'
import DepartmentForm from "./DepartmentForm/DepartmentForm";
import axios from "axios";
import {useSnackbar} from 'react-simple-snackbar'

const Departments = () => {

    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);
    const [openSnackbar] = useSnackbar()

    const [departments, setDepartments] = useState([]);
    const [value, setValue] = useState(1);

    useEffect(() => {

        openSnackbar("Data Loading from Internet")

        axios({
            method: 'get',
            url: 'http://localhost:8006/departments',
            headers: {'Content-Type': 'application/json'}
        })
            .then(res => {
                console.log(res.data)
                setDepartments(res.data.data)

                openSnackbar("Data Loaded from Internet")

            })
            .catch(error => {
                let errorData = error.response.data;
                console.log(errorData)

                openSnackbar(errorData.message)
            })

    }, [value])

    return (
        <Container>
            <DepartmentForm show={show} handleClose={handleClose} isNew="true" key="0" value={value}
                            setValue={setValue}/>
            <div className="d-flex justify-content-between mt-5 mx-5">
                <h3>Department List</h3>
                <Button onClick={handleShow} className="btn btn-primary">Add Department</Button>
            </div>

            <div className="d-flex justify-content-center table-margin">
                <Table striped bordered hover style={{width: '70%'}}>
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Active</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>

                    {
                        departments.map(department => <DeptTableRow value={value} setValue={setValue}
                                                                    key={department.id} tableData={department}/>)
                    }
                    </tbody>
                </Table>
            </div>
        </Container>
    );
};

export default Departments;
