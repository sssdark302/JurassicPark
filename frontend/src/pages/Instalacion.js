import React, {useState} from 'react';
import RSocketTerminal from '../componentes/RSocketTerminal';
import { useParams } from 'react-router-dom';

const Instalacion = () => {
    const {instalacion} = useParams();

    const [instalacionSeleccionada, setInstalacionSeleccionada] = useState(null);

    function obtenerMensajesPorInstalacion(instalacionSeleccionada) {
        switch (instalacionSeleccionada) {
            case "Enfermeria":
                return [
                    "Bienvenido a la enfermería"
                ];
            case "Laboratorio":
                return [
                    "Bienvenido al laboratorio"
                ];
            case "Centro_Visitantes":
                return [
                    "Bienvenido al centro de visitantes"
                ];
        }

        return (
            <div>
                <h1>Instalación: {instalacion}</h1>
                <img
                    src={`http://localhost:8080/images/${instalacion}.png`}
                    alt={instalacion}
                    style={{imageRendering: 'pixelated', width: '300px', height: '300px'}}
                />
                {instalacionSeleccionada && (
                    <div style={{marginTop: "30px"}}>
                        <RSocketTerminal
                            instalacion={instalacionSeleccionada}
                            mensajes={obtenerMensajesPorInstalacion(instalacionSeleccionada)}
                        />
                    </div>
                )} />
            </div>
        );
    };
}

export default Instalacion;
