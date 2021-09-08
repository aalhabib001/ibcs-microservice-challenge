import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import './App.css';
import Header from "./components/Header/Header";
import HomePage from "./components/HomePage/HomePage";
import 'bootstrap/dist/css/bootstrap.min.css';
import Departments from "./components/Departments/Departments";
import Employees from "./components/Employees/Employees";
import SnackbarProvider from "react-simple-snackbar"

function App() {
    return (
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
    );
}

export default App;
