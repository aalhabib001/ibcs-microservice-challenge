import React from 'react';
import {Container} from "react-bootstrap";
import './HomePage.css'
import {Link} from "react-router-dom";

const HomePage = () => {
    return (
        <Container className="w-75">
            <div className="d-flex justify-content-center mt-5">
                <h2>Employee Management System</h2>
            </div>
            <div className="d-flex justify-content-around mt-5">
                <Link to="/departments">
                    <div className="card card-1 m-2 p-5">
                        <h4 className="card__title">Manage Department</h4>
                    </div>
                </Link>
                <Link to="/employees">
                    <div className="card card-2 m-2 p-5">
                        <h4 className="card__title">Manage Employee</h4>
                    </div>
                </Link>
            </div>
        </Container>
    );
};

export default HomePage;
