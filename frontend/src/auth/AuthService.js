import axios from "axios";

const API_URL = "http://localhost:8080";

export const login = async (username, password) => {
    try {
        const response = await axios.post(
            `${API_URL}/api/login`,
            { username, password },
            { withCredentials: true } // Asegúrate de enviar cookies al backend
        );
        return response.data;
    } catch (error) {
        throw new Error("Error al iniciar sesión");
    }
};

export const logout = async () => {
    try {
        await axios.post(`${API_URL}/api/logout`, {}, { withCredentials: true });
    } catch (error) {
        console.error("Error al cerrar sesión", error);
    }
};

export const isAuthenticated = async () => {
    try {
        const response = await axios.get(`${API_URL}/api/authenticated`, {
            withCredentials: true,
        });
        return response.data.authenticated;
    } catch {
        return false;
    }
};
