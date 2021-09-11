import React from 'react';
import {Container, Nav, Navbar} from "react-bootstrap";
import './Header.css'
import {Link} from "react-router-dom";

const Header = () => {
    return (
        <Navbar collapseOnSelect expand="lg" bg="light" variant="light" className="sticky-top">
            <Container>
                <Link to="/" className="nav-link active text-dark">
                    <Navbar.Brand className="header-logo">Employee Management System</Navbar.Brand>
                </Link>
                <Navbar.Toggle aria-controls="responsive-navbar-nav"/>
                <Navbar.Collapse id="responsive-navbar-nav">

                    <Nav className="align-items-center">
                        <Link to="/"
                              className="nav-link active text-dark mr-3 header-option ">
                            Home
                        </Link>
                        <Link to="/departments"
                              className="nav-link active text-dark mr-3 header-option ">
                            Departments
                        </Link>
                        <Link to="/employees"
                              className="nav-link active text-dark mr-3 header-option ">
                            Employees
                        </Link>
                    </Nav>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    );
};

export default Header;
