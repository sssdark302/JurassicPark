fetch("http://localhost:8080/api/instalaciones")
    .then(response => response.json())
    .then(data => console.log(data))
    .catch(error => console.error("Error:", error));
