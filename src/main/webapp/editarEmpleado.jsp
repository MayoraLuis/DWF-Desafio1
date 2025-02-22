<%--
  Created by IntelliJ IDEA.
  User: luism
  Date: 2/21/2025
  Time: 11:46 a. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Editar Empleado</title>
</head>
<body>
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
<!--Formulario de Edición -->
<div class="container mt-4">
    <div class="card shadow-lg p-4 rounded bg-white">
        <h3 class="text-center">Editar Empleado</h3>
        <form action="empleados" method="post">
            <input type="hidden" name="action" value="actualizar">
            <input type="hidden" name="idEmpleado" value="${empleado.idEmpleado}">

            <div class="row">
                <!-- Número DUI -->
                <div class="col-md-6 mb-3">
                    <label class="form-label fw-bold">Número DUI:</label>
                    <input type="text" class="form-control" name="numeroDui" value="${empleado.numeroDui}" required>
                </div>

                <!-- Nombre -->
                <div class="col-md-6 mb-3">
                    <label class="form-label fw-bold">Nombre Completo:</label>
                    <input type="text" class="form-control" name="nombrePersona" value="${empleado.nombrePersona}" required>
                </div>

                <!-- Usuario -->
                <div class="col-md-6 mb-3">
                    <label class="form-label fw-bold">Usuario:</label>
                    <input type="text" class="form-control" name="usuario" value="${empleado.usuario}" required>
                </div>

                <!-- Teléfono -->
                <div class="col-md-6 mb-3">
                    <label class="form-label fw-bold">Teléfono:</label>
                    <input type="text" class="form-control" name="numeroTelefono" value="${empleado.numeroTelefono}" required>
                </div>

                <!-- Correo Electrónico -->
                <div class="col-md-6 mb-3">
                    <label class="form-label fw-bold">Correo Electrónico:</label>
                    <input type="email" class="form-control" name="correoInstitucional" value="${empleado.correoInstitucional}" required>
                </div>

                <!-- Fecha de Nacimiento -->
                <div class="col-md-6 mb-3">
                    <label class="form-label fw-bold">Fecha de Nacimiento:</label>
                    <input type="date" class="form-control" name="fechaNacimiento" value="${empleado.fechaNacimiento}" required>
                </div>
            </div>

            <!--Botones -->
            <div class="text-center mt-3">
                <button type="submit" class="btn btn-success"><i class="bi bi-save"></i> Guardar</button>
                <a href="empleados.jsp" class="btn btn-secondary"><i class="bi bi-arrow-left"></i> Cancelar</a>
            </div>
        </form>
    </div>
</div>
<!-- Bootstrap JS Bundle -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

<%--Footer--%>
<footer class="bg-dark text-white text-center py-3 mt-4">
    <p>© 2025 Gestión RRHH - Universidad Don Bosco</p>
</footer>
</body>
</html>
