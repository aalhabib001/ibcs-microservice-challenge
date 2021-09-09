import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import './App.css';
import Header from "./components/Header/Header";
import HomePage from "./components/HomePage/HomePage";
import 'bootstrap/dist/css/bootstrap.min.css';
import Departments from "./components/Departments/Departments";
import Employees from "./components/Employees/Employees";
import SnackbarProvider from "react-simple-snackbar"
import {createContext, useEffect, useState} from "react";
import axios from "axios";

export const DeptContext = createContext();

function App() {
    const [departments, setDepartments] = useState([]);
    useEffect(() => {

        axios({
            method: 'get',
            // url: 'http://localhost:8006/departments',
            url: 'http://localhost:8000/api/department-service/departments',
            headers: {'Content-Type': 'application/json'}
        })
            .then(res => {
                console.log(res.data)
                setDepartments(res.data.data)

            })
            .catch(error => {
                let errorData = error.response.data;
                console.log(errorData)

            })

    }, [])

    return (
        <DeptContext.Provider value={[departments, setDepartments]}>
            <Router>
                <SnackbarProvider>
                    <div>
                        <Header/>

                        <Switch>
                            <Route exact path="/">
                                <HomePage/>
                            </Route>
                            <Route path="/departments">
                                <Departments/>
                            </Route>

                            <Route path="/employees">
                                <Employees/>
                            </Route>
                        </Switch>
                    </div>
                </SnackbarProvider>
            </Router>
        </DeptContext.Provider>
    );
}

export default App;
