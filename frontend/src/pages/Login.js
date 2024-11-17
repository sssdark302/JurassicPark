import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const Login = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const handleLogin = async (event) => {
        event.preventDefault();
        // Aquí puedes manejar la lógica del login con tu API
        console.log({ username, password });
        // Ejemplo de redirección después de login exitoso
        navigate('/home');
    };

    return (
        <div>
            <h1>Iniciar Sesión</h1>
            <form onSubmit={handleLogin}>
                <label>
                    Nombre de usuario:
                    <input
                        type="text"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        required
                    />
                </label>
                <br />
                <label>
                    Contraseña:
                    <input
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                </label>
                <br />
                <button type="submit">Iniciar Sesión</button>
            </form>
            <button onClick={() => navigate('/register')} style={{ marginTop: '20px' }}>
                Registrarse
            </button>
        </div>
    );
};
export default Login;
