import React, { useState, useEffect } from "react";
import "../styles/RSocketTerminal.css";

const RSocketTerminal = ({ instalacion, mensajes }) => {
    const [logs, setLogs] = useState([]);

    useEffect(() => {
        if (mensajes) {
            setLogs(mensajes);
        }
    }, [instalacion, mensajes]);

    const agregarMensaje = (mensaje) => {
        setLogs((prevLogs) => [...prevLogs, mensaje]);
    };

    return (
        <div className="terminal-container">
            <h2>Terminal para {instalacion}</h2>
            <div className="terminal">
                {logs.map((msg, index) => (
                    <p key={index}>{msg}</p>
                ))}
            </div>
        </div>
    );
};

export default RSocketTerminal;
