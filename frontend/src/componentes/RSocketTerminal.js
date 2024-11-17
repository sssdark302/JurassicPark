import React, { useState, useEffect } from "react";
import { connectToRSocket } from "../rsocketService";

const RSocketTerminal = ({ instalacion }) => {
    const [logs, setLogs] = useState([]);
    const [error, setError] = useState(null);

    useEffect(() => {
        let subscription;

        connectToRSocket().subscribe({
            onComplete: (socket) => {
                subscription = socket
                    .requestStream({
                        data: instalacion,
                        metadata: String.fromCharCode(
                            "instalaciones.terminal".length
                        ) + "instalaciones.terminal",
                    })
                    .subscribe({
                        onNext: (payload) => {
                            setLogs((prevLogs) => [...prevLogs, payload.data]);
                        },
                        onError: (err) =>
                            setError("Error al recibir mensajes: " + err),
                        onComplete: () => console.log("Flujo completo."),
                    });
            },
            onError: (err) => setError("Error al conectar: " + err),
        });

        return () => {
            if (subscription) {
                subscription.cancel();
            }
        };
    }, [instalacion]);

    return (
        <div>
            <h2>Terminal para {instalacion}</h2>
            <div style={{ backgroundColor: "#333", color: "#fff", padding: "10px" }}>
                {error ? (
                    <p>{error}</p>
                ) : (
                    logs.map((log, index) => <p key={index}>{log}</p>)
                )}
            </div>
        </div>
    );
};

export default RSocketTerminal;
