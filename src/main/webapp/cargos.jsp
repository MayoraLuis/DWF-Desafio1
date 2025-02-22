<%--
  Created by IntelliJ IDEA.
  User: luism
  Date: 2/18/2025
  Time: 8:50 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<%--    Bootstrap CSS--%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <title>Gestión de Cargos</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="container mt-4">

<%--Inicia Barra de Navegacion--%>
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
                <li class="nav-item"><a class="nav-link" href="reporteEmpleados">Reporte</a></li>
            </ul>
        </div>
    </div>
</nav>
<%--Finaliza Barra de navegacion--%>

<h2 class="mb-4">Lista de Cargos</h2>

<table class="table table-bordered">
    <thead class="table-dark">
    <tr>
        <th>ID</th>
        <th>Nombre</th>
        <th>Descripción</th>
        <th>Jefatura</th>
        <th>Acciones</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="cargo" items="${cargos}">
        <tr>
            <td>${cargo.idCargo}</td>
            <td>${cargo.cargo}</td>
            <td>${cargo.descripcionCargo}</td>
            <td>${cargo.jefatura ? 'Sí' : 'No'}</td>
            <td>
                <a href="cargos?action=editar&idCargo=${cargo.idCargo}" class="btn btn-warning btn-sm">Editar</a>
                <a href="cargos?action=eliminar&idCargo=${cargo.idCargo}" class="btn btn-danger btn-sm" onclick="return confirm('¿Seguro que quieres eliminar este cargo?');">Eliminar</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<h2 class="mt-4">Agregar Nuevo Cargo</h2>

<form action="cargos" method="post" class="mt-3">
    <input type="hidden" name="action" value="insertar">

    <label>Nombre:</label>
    <input type="text" class="form-control" name="cargo" required>

    <label>Descripción:</label>
    <input type="text" class="form-control" name="descripcionCargo" required>

    <label>Jefatura:</label>
    <select class="form-control" name="jefatura">
        <option value="true">Sí</option>
        <option value="false">No</option>
    </select>

    <button type="submit" class="btn btn-success mt-2">Agregar Cargo</button>
</form>
<!-- Bootstrap JS Bundle -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>


<%--Footer--%>
<footer class="bg-dark text-white text-center py-3 mt-4">
    <p>© 2025 Gestión RRHH - Universidad Don Bosco</p>
</footer>
</body>
</html>

