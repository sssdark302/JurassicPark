import {
    RSocketClient,
    JsonSerializer,
    IdentitySerializer,
} from "rsocket-core";
import RSocketWebSocketClient from "rsocket-websocket-client";

export const connectToRSocket = () => {
    return new RSocketClient({
        serializers: {
            data: JsonSerializer,
            metadata: IdentitySerializer,
        },
        setup: {
            keepAlive: 10000,
            lifetime: 20000,
            dataMimeType: "application/json",
            metadataMimeType: "message/x.rsocket.routing.v0",
        },
        transport: new RSocketWebSocketClient({
            url: "ws://localhost:7000/rsocket",
        }),
    }).connect();
};
