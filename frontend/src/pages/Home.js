import React, { useState } from "react";
import AgregarInstalaciones from "../componentes/AgegarInstalacion";
import RSocketTerminal from "../componentes/RSocketTerminal";
import "../styles/Home.css";

const Home = () => {
    const [instalaciones, setInstalaciones] = useState([
        "Centro Visitantes",
        "Laboratorio",
        "Enfermería"
    ]);

    const agregarInstalacion = (nombre) => {
        setInstalaciones((prev) => [...prev, nombre]);
    };

    const handleClickInstalacion = (instalacion) => {
        setInstalacionSeleccionada(instalacion);
    };
    return (
        <div className="home-container">
            <div className="instalaciones-list">
                {instalaciones.map((instalacion, index) => (
                    <button
                        key={index}
                        onClick={() => navigate(`/instalacion/${instalacion}`)}
                        className="instalacion-boton">
                        {instalacion}
                    </button>
                ))}
                <AgregarInstalaciones onNewInstalacion={agregarInstalacion} />
            </div>
        </div>
    );
};

export default Home;
