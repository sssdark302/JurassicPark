import React, { useState } from "react";
import "../styles/agregarInstalaciones.css";

const AgregarInstalaciones = ({ onNewInstalacion }) => {
    const [nombre, setNombre] = useState("");

    const instalacionesDisponibles = [
        "Jaula_Acuatica_Carnivoro",
        "Jaula_Acuatica_Omnivoro",
        "Jaula_Terrestre_Carnivoro",
        "Jaula_Terrestre_Herbivoro",
        "Jaula_Terrestre_Omnivoro",
        "Jaula_Aerea_Carnivoro",
        "Jaula_Aerea_Omnivoro"
    ];

    const crearInstalacion = async () => {
        if (!nombre) {
            alert("Selecciona una instalación.");
            return;
        }

        const response = await fetch(`http://localhost:8080/api/instalaciones/${nombre}`, {
            method: "POST",
        });

        if (response.ok) {
            const mensaje = await response.text();
            alert(mensaje);
            onNewInstalacion(nombre);
        } else {
            const errorMensaje = await response.text();
            alert("Error: " + errorMensaje);
        }
    };

    return (
        <div>
            <h3>Agregar Instalación</h3>
            <select
                className={nombre}
                onChange={(e) => setNombre(e.target.value)}
            >
                <option className={Lista}>Selecciona una instalación</option>
                {instalacionesDisponibles.map((instalacion, index) => (
                    <option key={index} value={instalacion}>
                        {instalacion}
                    </option>
                ))}
            </select>
            <button className={botonAgregar}>Crear</button>
        </div>
    );
};

export default AgregarInstalaciones;
