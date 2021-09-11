import React, {useState} from 'react';
import {Button, Form, Modal} from "react-bootstrap";
import axios from "axios";
import {useSnackbar} from 'react-simple-snackbar'

const DepartmentForm = (props) => {

    let {show, handleClose, name, isActive, isNew, id, value, setValue} = props;
    const [openSnackbar] = useSnackbar()

    const handleOnBlur = (event) => {
        console.log(event.target.checked)
    }

    const [deptData] = useState({
        name: null,
        active: null
    })

    const handleSubmit = async (event) => {
        event.preventDefault();

        deptData.name = event.target.dept.value
        deptData.active = event.target.active.checked

        if (isNew === 'true') {
            console.log(deptData);

            await axios({
                method: 'post',
                // url: 'http://localhost:8006/departments',
                url: 'http://localhost:8000/api/department-service/departments',
                headers: {'Content-Type': 'application/json'},
                data: deptData
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
            console.log(deptData);
            console.log(id)

            axios({
                method: 'put',
                // url: 'http://localhost:8006/departments/' + parseInt(id),
                url: 'http://localhost:8000/api/department-service/departments/' + parseInt(id),
                headers: {'Content-Type': 'application/json'},
                data: deptData
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
                        <Form.Group className="mb-3" controlId="dept">
                            <Form.Label>Department Name</Form.Label>
                            <Form.Control defaultValue={name} type="text" placeholder="Dept Name" required/>
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="active">
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
