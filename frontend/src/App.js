import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Login from '../src/pages/Login';
import Register from '../src/pages/Register';
import Home from '../src/pages/Home';
import Instalacion from "./pages/Instalacion";

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/login" element={<Login />} />
                <Route path="/register" element={<Register />} />
                <Route path="/home" element={<Home />} />
                <Route path="/home" element={<Home />} />
                <Route path="/home/:nombreInstalacion" element={<Instalacion />} />
            </Routes>
        </Router>
    );
}

export default App;
