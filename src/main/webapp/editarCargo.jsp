<%--
  Created by IntelliJ IDEA.
  User: luism
  Date: 2/20/2025
  Time: 9:55 a. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Editar Cargo</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="container mt-4">
<%--Inicia Barra de Navegacion--%>
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
<%--Finaliza Barra de Navegacion--%>

<h2>Editar Cargo</h2>

<form action="cargos" method="post">
    <input type="hidden" name="action" value="actualizar">
    <input type="hidden" name="idCargo" value="${cargo.idCargo}">

    <div class="mb-3">
        <label class="form-label">Nombre:</label>
        <input type="text" class="form-control" name="cargo" value="${cargo.cargo}" required>
    </div>

    <div class="mb-3">
        <label class="form-label">Descripción:</label>
        <input type="text" class="form-control" name="descripcionCargo" value="${cargo.descripcionCargo}" required>
    </div>

    <div class="mb-3">
        <label class="form-label">Jefatura:</label>
        <select class="form-control" name="jefatura">
            <option value="true" ${cargo.jefatura ? 'selected' : ''}>Sí</option>
            <option value="false" ${!cargo.jefatura ? 'selected' : ''}>No</option>
        </select>
    </div>

    <button type="submit" class="btn btn-primary">Guardar Cambios</button>
    <a href="cargos" class="btn btn-secondary">Cancelar</a>
</form>
<!-- Bootstrap JS  -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

<%--Footer--%>
<footer class="bg-dark text-white text-center py-3 mt-4">
    <p>© 2025 Gestión RRHH - Universidad Don Bosco</p>
</footer>
</body>
</html>

