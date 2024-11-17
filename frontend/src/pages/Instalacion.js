import React from 'react';
import RSocketTerminal from '../componentes/RSocketTerminal';
import { useParams } from 'react-router-dom';

const Instalacion = () => {
    const { instalacion } = useParams();

    return (
        <div>
            <h1>Instalación: {instalacion}</h1>
            <img
                src={`http://localhost:8080/images/${instalacion}.png`}
                alt={instalacion}
                style={{ imageRendering: 'pixelated', width: '300px', height: '300px' }}
            />
            <RSocketTerminal instalacion={instalacion} />
        </div>
    );
};

export default Instalacion;
