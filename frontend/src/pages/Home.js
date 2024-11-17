import React, { useState } from "react";
import AgregarInstalaciones from "../componentes/AgegarInstalacion";
import "../styles/Home.css";

const Home = () => {
    const [instalaciones, setInstalaciones] = useState([
        { nombre: "Centro Visitantes", id: 1, fondo: "/assets/img/centrodevisitantes.webp" },
        { nombre: "Laboratorio", id: 2, fondo: "/assets/img/laboratorio.webp" },
        { nombre: "Enfermería", id: 3, fondo: "/assets/img/enfermeria.webp" },
    ]);

    const agregarNuevaInstalacion = (nombre, fondo) => {
        const nuevaInstalacion = {
            nombre: nombre.trim(),
            id: instalaciones.length + 1,
            fondo: fondo || "/assets/img/default.webp", // Fondo predeterminado
        };

        setInstalaciones((prev) => [...prev, nuevaInstalacion]);
    };

    return (
        <div className="home-container">
            <div className="instalaciones-row">
                {instalaciones.map((instalacion, index) => (
                    <button
                        key={index}
                        className="instalacion-boton"
                        onClick={() =>
                            window.location.href = `/home/${instalacion.nombre}`
                        }
                    >
                        {instalacion.nombre}
                    </button>
                ))}
                <AgregarInstalaciones onNewInstalacion={agregarNuevaInstalacion} />
            </div>
        </div>
    );
};

export default Home;
