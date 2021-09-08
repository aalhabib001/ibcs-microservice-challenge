import React, {useState} from 'react';
import {Button, Container, Table} from "react-bootstrap";
import {employeeFakeData} from "./EmployeeFakeData";
import EmpTableRow from "./EmpTableRow/EmpTableRow";
import EmployeeForm from "./EmployeeForm/EmployeeForm";
import {useSnackbar} from 'react-simple-snackbar'

const Employees = () => {

    const [openSnackbar] = useSnackbar()
    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    const handleShow = () => {
        openSnackbar('This is the content of the Snackbar.')
        setShow(true)
    };


    return (
        <Container>
            <EmployeeForm show={show} handleClose={handleClose} isNew="true" key="0" data={{}}/>
            <div className="d-flex justify-content-between mt-5 mx-5">
                <h3>Employee List</h3>
                <Button onClick={handleShow} className="btn btn-primary">Add Employee</Button>
            </div>

            <div className="d-flex justify-content-center table-margin">
                <Table striped bordered hover>
                    <thead>
                    <tr>
                        <th>Code</th>
                        <th>Name</th>
                        <th>Date Of Birth</th>
                        <th>Phone</th>
                        <th>Gender</th>
                        <th>Dept Name</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        employeeFakeData.map(employeeData => <EmpTableRow key={employeeData.id}
                                                                          tableData={employeeData}/>)
                    }
                    </tbody>
                </Table>
            </div>
        </Container>
    );
};

export default Employees;
