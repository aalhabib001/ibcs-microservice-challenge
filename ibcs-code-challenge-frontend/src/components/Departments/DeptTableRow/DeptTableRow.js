import React, {useState} from 'react';
import {Button} from "react-bootstrap";
import './DeptTableRow.css'
import DeleteModal from "../../common/DeleteModal";
import DepartmentForm from "../DepartmentForm/DepartmentForm";

const DeptTableRow = (props) => {
    const {id, name, active} = props.tableData;
    const {value, setValue} = props;

    const [show1, setShow1] = useState(false);

    const handleClose1 = () => setShow1(false);
    const handleShow1 = () => setShow1(true);
    const [show2, setShow2] = useState(false);

    const handleClose2 = () => setShow2(false);
    const handleShow2 = () => setShow2(true);


    return (
        <>
            <DepartmentForm show={show1} handleClose={handleClose1} value={value} setValue={setValue}
                            name={name} isActive={active} key={id} isNew={false} id={id}/>
            <tr>
                <td>{id}</td>
                <td>{name}</td>
                <td>
                    <div className="d-flex justify-content-center">
                        {
                            active ?
                                <span className="span-true">Yes</span> :
                                <span className="span-false">No</span>
                        }
                    </div>
                </td>
                <td>
                    <div className="d-flex justify-content-around">
                        <div>
                            <Button onClick={handleShow1} className="btn btn-warning">Edit</Button>
                        </div>
                        <div>
                            <Button onClick={handleShow2} className="btn btn-danger">Delete</Button>
                        </div>
                    </div>
                </td>
            </tr>

            <DeleteModal show={show2} handleClose={handleClose2} isEmployee="false"
                         id={id} value={value} setValue={setValue}/>
        </>

    );
};

export default DeptTableRow;
