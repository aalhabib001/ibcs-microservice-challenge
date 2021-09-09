import React, {useEffect, useState} from 'react';
import {Button, Container, Table} from "react-bootstrap";
import {employeeFakeData} from "./EmployeeFakeData";
import EmpTableRow from "./EmpTableRow/EmpTableRow";
import EmployeeForm from "./EmployeeForm/EmployeeForm";
import {useSnackbar} from 'react-simple-snackbar'
import axios from "axios";

const Employees = () => {

    const [openSnackbar] = useSnackbar()
    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    const handleShow = () => {
        setShow(true)
    };

    const [employees, setEmployees] = useState([]);
    const [value, setValue] = useState(1);

    useEffect(() => {

        openSnackbar("Data Loading from Internet")

        axios({
            method: 'get',
            // url: 'http://localhost:8005/employees/',
            url: 'http://localhost:8000/api/employee-service/employees',
            headers: {'Content-Type': 'application/json'}
        })
            .then(res => {
                console.log(res.data)
                setEmployees(res.data.data)

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
            <EmployeeForm show={show} handleClose={handleClose} isNew="true" key="0" data={{}}
                          value={value} setValue={setValue}/>
            <div className="d-flex justify-content-between mt-5 mx-5">
                <h3>Employee List</h3>
                <Button onClick={handleShow} className="btn btn-primary">Add Employee</Button>
            </div>

            <div className="d-flex justify-content-center table-margin">
                <Table striped bordered hover>
                    {/*<thead>*/}
                    <tr>
                        <th>Code</th>
                        <th>Name</th>
                        <th>Date Of Birth</th>
                        <th>Phone</th>
                        <th>Gender</th>
                        <th>Dept Name</th>
                        <th>Action</th>
                    </tr>
                    {/*</thead>*/}
                    <tbody>
                    {
                        employees.map(employeeData => <EmpTableRow key={employeeData.id} tableData={employeeData}
                                                                   value={value} setValue={setValue}/>)
                    }
                    </tbody>
                </Table>
            </div>
        </Container>
    );
};

export default Employees;
