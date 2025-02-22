<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bienvenido - GestionRRHH</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        .hero {
            background: url('https://source.unsplash.com/1600x900/?office,teamwork') center/cover no-repeat;
            height: 50vh;
            display: flex;
            align-items: center;
            justify-content: center;
            color: white;
            text-shadow: 2px 2px 5px rgba(0, 0, 0, 0.5);
        }
    </style>
</head>
<body class="bg-light">

<!--  Menu de navegacion -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="#">GestionRRHH</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item"><a class="nav-link" href="empleados.jsp">Empleados</a></li>
                <li class="nav-item"><a class="nav-link" href="departamentos.jsp">Departamentos</a></li>
                <li class="nav-item"><a class="nav-link" href="contrataciones.jsp">Contrataciones</a></li>
                <li class="nav-item"><a class="nav-link" href="cargos.jsp">Cargos</a></li>
                <li class="nav-item"><a class="nav-link" href="reporteEmpleados">Reporte</a></li>
            </ul>
        </div>
    </div>
</nav>

<!-- Portada -->
<div class="hero text-dark">
    <div class="text-center">
        <h1 class="display-4 fw-bold">Universidad Don Bosco</h1>
        <h3 class="lead">Desarrollo de Aplicaciones con Web Frameworks DWF901 G01T</h3>
        <h3 class="lead">Experiencia de Aprendizaje 1</h3>
        <p class="lead">Desafio 1</p>
        <p class="lead">Sistema Para Recursos Humanos</p>
        <h3 class="lead">Docente: Ing. Emerson Ernesto Torres Rodriguez </h3>
    </div>
</div>

<!-- Información del Proyecto -->
<div class="container mt-5">
    <div class="card p-4 shadow">
        <h2 class="text-center">Sobre el Proyecto</h2>
        <p class="text-center">Es sistema web que permite la administración eficiente de los recursos humanos, incluyendo la gestión de empleados, contrataciones, cargos y departamentos.</p>

        <!--Tabla de Integrantes -->
        <h3 class="mt-4 text-center">Integrantes del Equipo</h3>
        <div class="table-responsive">
            <table class="table table-bordered table-hover text-center">
                <thead class="table-dark">
                <tr>
                    <th>Nombre</th>
                    <th>Carnet</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>Luis Ernesto Mayora Claros</td>
                    <td>MC090391</td>
                </tr>
                <tr>
                    <td>Katherine Estefany Beltran Lopez</td>
                    <td>BL233081</td>
                </tr>
                <tr>
                    <td>Katya Maria Hernandez Perez</td>
                    <td>HP221350</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

<!-- Enlaces Rápidos -->
<div class="container mt-5 text-center">
    <h3>Accede al Sistema</h3>
    <p>Haz clic en el módulo que deseas gestionar:</p>
    <div class="d-flex justify-content-center gap-3">
        <a href="empleados.jsp" class="btn btn-primary">Empleados</a>
        <a href="departamentos.jsp" class="btn btn-secondary">Departamentos</a>
        <a href="contrataciones.jsp" class="btn btn-success">Contrataciones</a>
        <a href="cargos.jsp" class="btn btn-warning">Cargos</a>
    </div>
</div>

<!--  Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>