import React from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import '../styles/instalacion.css';

const Instalacion = ({ instalacion }) => {
    const navigate = useNavigate();

    if (!instalacion || !instalacion.nombre) {
        console.error('Error: La instalación no está definida correctamente.', instalacion);
        return (
            <div>
                <p>Error: La instalación no está definida. Volviendo a la página principal...</p>
                <button onClick={() => navigate('/home')}>Volver a Home</button>
            </div>
        );
    }

    const nombreInstalacion = instalacion.nombre.replace(/\s+/g, '_');

    return (
        <div
            className="instalacion-container"
            style={{
                backgroundImage: `url(${instalacion.fondo})`,
                backgroundSize: 'cover',
                backgroundPosition: 'center',
                minHeight: '100vh',
            }}
        >
            <button
                className="instalacion-boton"
                onClick={() => navigate(`/home/${instalacion.nombre}`)}
            >
                {instalacion.nombre}
            </button>
        </div>
    );
};

export default Instalacion;
