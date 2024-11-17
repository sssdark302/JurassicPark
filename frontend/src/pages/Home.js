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
    const [instalacionSeleccionada, setInstalacionSeleccionada] = useState(null);

    const agregarInstalacion = (nombre) => {
        setInstalaciones((prev) => [...prev, nombre]); // Agrega la nueva instalación a la lista
    };
    const handleClickInstalacion = (instalacion) => {
        setInstalacionSeleccionada(instalacion);
    };

    const obtenerMensajesPorInstalacion = (instalacion) => {
        switch (instalacion) {
            case "Centro Visitantes":
                return [
                    "Bienvenido al Centro de Visitantes.",
                    "Iniciando proyección del parque...",
                    "Mostrando estadísticas del día."
                ];
            case "Laboratorio":
                return [
                    "Laboratorio activo.",
                    "Generando ADN de dinosaurio...",
                    "Iniciando secuencia de clonación."
                ];
            case "Enfermería":
                return [
                    "Enfermería operativa.",
                    "Cuidando dinosaurios heridos.",
                    "Administrando tratamientos médicos."
                ];
            default:
                return [
                    `Instalación seleccionada: ${instalacion}.`,
                    "No hay mensajes adicionales para mostrar."
                ];
        }
    };

    return (
        <div className="home-container">
            <div className="instalaciones-list">
                {instalaciones.map((instalacion, index) => (
                    <button
                        key={index}
                        onClick={() => handleClickInstalacion(instalacion)}
                        className="instalacion-boton"
                    >
                        {instalacion}
                    </button>
                ))}
                <AgregarInstalaciones onNewInstalacion={agregarInstalacion} />
            </div>
            {instalacionSeleccionada && (
                <div style={{ marginTop: "30px" }}>
                    <RSocketTerminal
                        instalacion={instalacionSeleccionada}
                        mensajes={obtenerMensajesPorInstalacion(instalacionSeleccionada)}
                    />
                </div>
            )}
        </div>
    );
};

export default Home;
