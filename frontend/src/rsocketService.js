import {
    RSocketClient,
    JsonSerializer,
    IdentitySerializer
} from 'rsocket-core';
import RSocketWebSocketClient from 'rsocket-websocket-client';

export const connectToRSocket = () => {
    return new RSocketClient({
        serializers: {
            data: JsonSerializer,
            metadata: IdentitySerializer
        },
        setup: {
            keepAlive: 10000, // Enviar señales de vida cada 10 segundos
            lifetime: 20000, // Tiempo de vida de la conexión
            dataMimeType: 'application/json',
            metadataMimeType: 'message/x.rsocket.routing.v0'
        },
        transport: new RSocketWebSocketClient({
            url: 'ws://localhost:7000/rsocket' // Cambia la URL según tu backend
        })
    }).connect();
};
