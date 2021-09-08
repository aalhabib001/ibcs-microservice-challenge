import React, {useState} from 'react';
import {Button, Container, Table} from "react-bootstrap";
import DeptTableRow from "./DeptTableRow/DeptTableRow";
import {departmentFakeData} from "./DepartmentFakeData";
import './Department.css'
import DepartmentForm from "./DepartmentForm/DepartmentForm";

const Departments = () => {

    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    return (
        <Container>
            <DepartmentForm show={show} handleClose={handleClose} isNew="true" key="0"/>
            <div className="d-flex justify-content-between mt-5 mx-5">
                <h3>Department List</h3>
                <Button onClick={handleShow} className="btn btn-primary">Add Department</Button>
            </div>

            <div className="d-flex justify-content-center table-margin">
                <Table striped bordered hover  style={{width: '100%'}}>
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
                        departmentFakeData.map(departmentData => <DeptTableRow key={departmentData.id} tableData={departmentData}/>)
                    }
                    </tbody>
                </Table>
            </div>
        </Container>
    );
};

export default Departments;
