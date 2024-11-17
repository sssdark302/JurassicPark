import React, { useEffect, useState } from "react";
import { Navigate } from "react-router-dom";
import { isAuthenticated } from "./AuthService";

const PrivateRoute = ({ children }) => {
    const [auth, setAuth] = useState(null);

    useEffect(() => {
        isAuthenticated().then(setAuth);
    }, []);

    if (auth === null) return <div>Cargando...</div>;
    return auth ? children : <Navigate to="/login" />;
};

export default PrivateRoute;
