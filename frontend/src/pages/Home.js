import React from "react";
import { logout } from "../auth/AuthService";

const Home = () => {
    const handleLogout = async () => {
        await logout();
        window.location.href = "/login";
    };

    return (
        <div>
            <h1>Bienvenido al Home</h1>
            <button onClick={handleLogout}>Cerrar sesión</button>
        </div>
    );
};

export default Home;
