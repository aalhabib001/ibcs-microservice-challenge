import React, {useState} from 'react';
import {Button} from "react-bootstrap";
import DeleteModal from "../../common/DeleteModal";
import EmployeeForm from "../EmployeeForm/EmployeeForm";

const EmpTableRow = (props) => {
    const {code, name, dob, gender, phone, dept} = props.tableData;

    const [show1, setShow1] = useState(false);

    const handleClose1 = () => setShow1(false);
    const handleShow1 = () => setShow1(true);
    const [show2, setShow2] = useState(false);

    const handleClose2 = () => setShow2(false);
    const handleShow2 = () => setShow2(true);

    return (
        <>
            <EmployeeForm show={show1} handleClose={handleClose1} isNew="false" key={code} data={props.tableData}/>
            <tr>
                <td>{code}</td>
                <td>{name}</td>
                <td>{dob}</td>
                <td>{phone}</td>
                <td>{gender}</td>
                <td>{dept}</td>
                <td>
                    <div className="d-flex justify-content-around">
                        <div>
                            <Button onClick={handleShow1} className="btn btn-warning mx-1">Edit</Button>
                        </div>
                        <div>
                            <Button onClick={handleShow2} className="btn btn-danger mx-1">Delete</Button>
                        </div>
                    </div>
                </td>
            </tr>

            <DeleteModal show={show2} handleClose={handleClose2}/>
        </>
    );
};

export default EmpTableRow;
