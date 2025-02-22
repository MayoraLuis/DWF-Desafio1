<%--
  Created by IntelliJ IDEA.
  User: luism
  Date: 2/21/2025
  Time: 8:46 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Reporte de Empleados</title>
</head>
<body>
<!-- Barra de navegación -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="index.jsp">GestionRRHH</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item"><a class="nav-link" href="empleados.jsp">Empleados</a></li>
                <li class="nav-item"><a class="nav-link" href="departamentos.jsp">Departamentos</a></li>
                <li class="nav-item"><a class="nav-link" href="contrataciones.jsp">Contrataciones</a></li>
                <li class="nav-item"><a class="nav-link" href="cargos.jsp">Cargos</a></li>
                <li class="nav-item"><a class="nav-link active" href="reporteEmpleados">Reporte</a></li>
            </ul>
        </div>
    </div>
</nav>

<!-- Contenido del Reporte -->
<div class="container mt-4">
    <h2 class="text-center mb-4">Reporte de Empleados</h2>

    <table class="table table-striped table-hover shadow">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Número DUI</th>
            <th>Nombre</th>
            <th>Usuario</th>
            <th>Teléfono</th>
            <th>Correo</th>
            <th>Fecha Nacimiento</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="empleado" items="${empleados}">
            <tr>
                <td>${empleado.idEmpleado}</td>
                <td>${empleado.numeroDui}</td>
                <td>${empleado.nombrePersona}</td>
                <td>${empleado.usuario}</td>
                <td>${empleado.numeroTelefono}</td>
                <td>${empleado.correoInstitucional}</td>
                <td>${empleado.fechaNacimiento}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

<!-- Footer -->
<footer class="bg-dark text-white text-center py-3 mt-4">
    <p>© 2025 Gestión RRHH - Universidad Don Bosco</p>
</footer>
</body>
</html>
