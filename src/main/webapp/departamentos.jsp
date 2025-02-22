<%--
  Created by IntelliJ IDEA.
  User: luism
  Date: 2/18/2025
  Time: 8:47 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Lista de Departamentos</title>
</head>
<body>

<%-- Menu de navegacion --%>
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

<%-- Contenedor Principal --%>
<div class="container mt-4">

    <%-- Tabla de Departamentos --%>
    <div class="card shadow p-4 mb-4">
        <h2 class="text-center mb-3">Lista de Departamentos</h2>
        <div class="table-responsive">
            <table class="table table-striped table-hover">
                <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Nombre Departamento</th>
                    <th>Descripción</th>
                    <th>Acciones</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="departamento" items="${departamentos}">
                    <tr>
                        <td>${departamento.idDepartamento}</td>
                        <td>${departamento.nombreDepartamento}</td>
                        <td>${departamento.descripcionDepartamento}</td>
                        <td>
                            <a href="departamentos?action=editar&idDepartamento=${departamento.idDepartamento}" class="btn btn-warning btn-sm">✏ Editar</a>
                            <a href="departamentos?action=eliminar&idDepartamento=${departamento.idDepartamento}" class="btn btn-danger btn-sm" onclick="return confirm('¿Eliminar este departamento?');">🗑 Eliminar</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <%-- Formulario de Agregar Departamento --%>
    <div class="card shadow p-4">
        <h2 class="text-center mb-3">Agregar Departamento</h2>
        <form action="departamentos" method="post">
            <input type="hidden" name="action" value="insertar">
            <div class="row mb-3">
                <label class="col-md-3 col-form-label">Nombre:</label>
                <div class="col-md-9">
                    <input type="text" class="form-control" name="nombreDepartamento" required>
                </div>
            </div>
            <div class="row mb-3">
                <label class="col-md-3 col-form-label">Descripción:</label>
                <div class="col-md-9">
                    <input type="text" class="form-control" name="descripcionDepartamento" required>
                </div>
            </div>
            <div class="text-center">
                <button type="submit" class="btn btn-primary w-100">➕ Agregar Departamento</button>
            </div>
        </form>
    </div>

</div>

<%-- Footer --%>
<footer class="bg-dark text-white text-center py-3 mt-4">
    <p>© 2025 Gestión RRHH - Universidad Don Bosco</p>
</footer>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>


