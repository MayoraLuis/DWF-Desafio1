<%--
  Created by IntelliJ IDEA.
  User: luism
  Date: 2/18/2025
  Time: 8:22 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Lista de Empleados</title>
</head>
<body>

<%--Navbar --%>
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

<%--Contenedor Principal --%>
<div class="container mt-4">

    <%--Tabla de Empleados --%>
    <div class="card shadow p-4 mb-4">
        <h2 class="text-center mb-3">Lista de Empleados</h2>
        <div class="table-responsive">
            <table class="table table-striped table-hover">
                <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Usuario</th>
                    <th>Teléfono</th>
                    <th>Correo</th>
                    <th>Fecha Nacimiento</th>
                    <th>Acciones</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="empleado" items="${empleados}">
                    <tr>
                        <td>${empleado.idEmpleado}</td>
                        <td>${empleado.nombrePersona}</td>
                        <td>${empleado.usuario}</td>
                        <td>${empleado.numeroTelefono}</td>
                        <td>${empleado.correoInstitucional}</td>
                        <td>${empleado.fechaNacimiento}</td>
                        <td>
                            <a href="empleados?action=editar&idEmpleado=${empleado.idEmpleado}" class="btn btn-warning btn-sm">✏ Editar</a>
                            <a href="empleados?action=eliminar&idEmpleado=${empleado.idEmpleado}" class="btn btn-danger btn-sm" onclick="return confirm('¿Eliminar este empleado?');">🗑 Eliminar</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <%--Formulario de Agregar Empleado --%>
    <div class="card shadow p-4">
        <h2 class="text-center mb-3">Agregar Nuevo Empleado</h2>
        <form action="empleados" method="post">
            <input type="hidden" name="action" value="insertar">

            <div class="row mb-3">
                <label class="col-md-3 col-form-label">Número DUI:</label>
                <div class="col-md-9">
                    <input type="text" class="form-control" name="numeroDui" required>
                </div>
            </div>

            <div class="row mb-3">
                <label class="col-md-3 col-form-label">Nombre:</label>
                <div class="col-md-9">
                    <input type="text" class="form-control" name="nombrePersona" required>
                </div>
            </div>

            <div class="row mb-3">
                <label class="col-md-3 col-form-label">Usuario:</label>
                <div class="col-md-9">
                    <input type="text" class="form-control" name="usuario" required>
                </div>
            </div>

            <div class="row mb-3">
                <label class="col-md-3 col-form-label">Teléfono:</label>
                <div class="col-md-9">
                    <input type="text" class="form-control" name="numeroTelefono">
                </div>
            </div>

            <div class="row mb-3">
                <label class="col-md-3 col-form-label">Correo:</label>
                <div class="col-md-9">
                    <input type="email" class="form-control" name="correoInstitucional">
                </div>
            </div>

            <div class="row mb-3">
                <label class="col-md-3 col-form-label">Fecha de Nacimiento:</label>
                <div class="col-md-9">
                    <input type="date" class="form-control" name="fechaNacimiento">
                </div>
            </div>

            <div class="text-center">
                <button type="submit" class="btn btn-primary w-100">➕ Agregar Empleado</button>
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