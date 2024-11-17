import React, { useState } from "react";
import "../styles/agregarInstalaciones.css";

const AgregarInstalaciones = ({ onNewInstalacion }) => {
    const [mostrarLista, setMostrarLista] = useState(false);

    const instalacionesDinosauriosPlantas = [
        "Jaula_Acuatica_Carnivoro",
        "Jaula_Acuatica_Omnivoro",
        "Jaula_Terrestre_Carnivoro",
        "Jaula_Terrestre_Herbivoro",
        "Jaula_Terrestre_Omnivoro",
        "Jaula_Aerea_Carnivoro",
        "Jaula_Aerea_Omnivoro"
    ];

    const manejarSeleccion = async (nombre) => {
        try {
            const response = await fetch(`http://localhost:8080/api/instalacion/${nombre}`, {
                method: "POST",
            });

            if (response.ok) {
                const mensaje = await response.text();
                alert(`Éxito: ${mensaje}`);
                onNewInstalacion(nombre); // Actualizar lista en el frontend
            } else {
                const errorMensaje = await response.text();
                alert(`Error: ${errorMensaje}`);
            }
        } catch (error) {
            alert(`Error al conectar con el servidor: ${error.message}`);
        }
        setMostrarLista(false);
    };

    return (
        <div className="agregar-instalaciones">
            <button
                onClick={() => setMostrarLista((prev) => !prev)}
                className="boton-agregar"
            >
                Agregar Instalación
            </button>
            {mostrarLista && (
                <ul className="lista-desplegable">
                    {instalacionesDinosauriosPlantas.map((instalacion, index) => (
                        <li
                            key={index}
                            onClick={() => manejarSeleccion(instalacion)}
                            className="opcion"
                        >
                            {instalacion}
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
};

export default AgregarInstalaciones;
