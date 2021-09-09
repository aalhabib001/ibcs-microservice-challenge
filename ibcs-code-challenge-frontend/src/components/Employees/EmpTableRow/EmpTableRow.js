import React, {useState} from 'react';
import {Button} from "react-bootstrap";
import DeleteModal from "../../common/DeleteModal";
import EmployeeForm from "../EmployeeForm/EmployeeForm";

const EmpTableRow = (props) => {
    let {code, name, dateOfBirth, gender, mobile, departmentName, id} = props.tableData;
    const {value, setValue} = props;

    const [show1, setShow1] = useState(false);

    const handleClose1 = () => setShow1(false);
    const handleShow1 = () => setShow1(true);
    const [show2, setShow2] = useState(false);

    const handleClose2 = () => setShow2(false);
    const handleShow2 = () => setShow2(true)

    // props.tableData.dateOfBirth = props.tableData.dateOfBirth.replaceAll("/", "-")

    return (
        <>
            <EmployeeForm show={show1} handleClose={handleClose1} isNew="false" value={value}
                          setValue={setValue} key={code} data={props.tableData}/>
            <tr>
                <td>{code}</td>
                <td>{name}</td>
                <td>{dateOfBirth}</td>
                <td>{mobile}</td>
                <td>{gender}</td>
                <td>{departmentName}</td>
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

            <DeleteModal show={show2} id={id} isEmployee="true" value={value} setValue={setValue} handleClose={handleClose2}/>
        </>
    );
};

export default EmpTableRow;
