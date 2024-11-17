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
            onNewInstalacion(nombre); // Notifica al componente padre para actualizar la lista
        } else {
            const errorMensaje = await response.text();
            alert("Error: " + errorMensaje);
        }
    };

    return (
        <div style={{ marginTop: "20px", textAlign: "center" }}>
            <h3>Agregar Instalación</h3>
            <select
                value={nombre}
                onChange={(e) => setNombre(e.target.value)}
                style={{
                    padding: "10px",
                    fontSize: "16px",
                    marginRight: "10px",
                    borderRadius: "5px",
                    border: "1px solid #ccc",
                }}
            >
                <option value="">Selecciona una instalación</option>
                {instalacionesDisponibles.map((instalacion, index) => (
                    <option key={index} value={instalacion}>
                        {instalacion}
                    </option>
                ))}
            </select>
            <button
                onClick={crearInstalacion}
                style={{
                    padding: "10px 20px",
                    fontSize: "16px",
                    backgroundColor: "#007BFF",
                    color: "white",
                    border: "none",
                    borderRadius: "5px",
                    cursor: "pointer",
                }}
            >
                Crear
            </button>
        </div>
    );
};

export default AgregarInstalaciones;
