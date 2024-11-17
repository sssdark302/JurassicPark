import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const Register = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [role, setRole] = useState('ROLE_USER'); // Por defecto el rol es ROLE_USER
    const navigate = useNavigate();

    const handleRegister = async (event) => {
        event.preventDefault();
        // Aquí puedes manejar la lógica del registro con tu API
        console.log({ username, password, role });
        // Ejemplo de redirección después del registro exitoso
        navigate('/login');
    };

    return (
        <div>
            <h1>Registrarse</h1>
            <form onSubmit={handleRegister}>
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
                <label>
                    Rol:
                    <input
                        type="text"
                        value={role}
                        onChange={(e) => setRole(e.target.value)}
                        required
                    />
                </label>
                <br />
                <button type="submit">Registrarse</button>
            </form>
            <button onClick={() => navigate('/login')} style={{ marginTop: '20px' }}>
                Iniciar Sesión
            </button>
        </div>
    );
};

export default Register;
