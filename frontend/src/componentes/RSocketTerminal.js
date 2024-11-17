import React, { useState, useEffect } from "react";
import { connectToRSocket } from "../rsocketService";
import "../styles/RSocketTerminal.css";

const RSocketTerminal = ({ instalacion }) => {
    const [mensajes, setMensajes] = useState([]);
    const [error, setError] = useState(null);

    useEffect(() => {
        if (!instalacion) {
            setError("No se ha especificado ninguna instalación.");
            return;
        }

        let subscription;

        connectToRSocket().subscribe({
            onComplete: socket => {
                subscription = socket.requestStream({
                    data: instalacion,
                    metadata: String.fromCharCode('instalaciones.terminal'.length) + 'instalaciones.terminal'
                }).subscribe({
                    onNext: payload => {
                        setMensajes(prev => [...prev, payload.data]);
                    },
                    onError: err => {
                        setError("Error al recibir datos: " + err);
                    },
                    onComplete: () => console.log("Stream completado."),
                });
            },
            onError: err => setError("Error al conectar: " + err),
        });

        return () => {
            if (subscription) {
                subscription.cancel();
            }
        };
    }, [instalacion]);

    return (
        <div className="terminal-container">
            <h2>Terminal para {instalacion}</h2>
            <div className="terminal">
                {error ? (
                    <p>{error}</p>
                ) : (
                    mensajes.map((msg, index) => <p key={index}>{msg}</p>)
                )}
            </div>
        </div>
    );
};

export default RSocketTerminal;
