<%--
  Created by IntelliJ IDEA.
  User: luism
  Date: 2/18/2025
  Time: 8:51 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <title>Gestión de Contrataciones</title>
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
<%--Finaliza Barra de Navegacion--%>
<div class="container mt-4">
  <h2 class="text-center mb-4">Lista de Empleados</h2>
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
    <c:forEach var="contrataciones" items="${contrataciones}">
      <tr>
        <td>${empleado.idEmpleado}</td>
        <td>${empleado.nombrePersona}</td>
        <td>${empleado.usuario}</td>
        <td>${empleado.numeroTelefono}</td>
        <td>${empleado.correoInstitucional}</td>
        <td>${empleado.fechaNacimiento}</td>
        <td>
          <a href="contrataciones?action=editar&idContrataciones=${contrataciones.idContrataciones}" class="btn btn-warning btn-sm">Editar</a>
          <a href="contrataciones?action=eliminar&idContrataciones=${contrataciones.idContrataciones}" class="btn btn-danger btn-sm">Eliminar</a>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>

<h2 class="mt-4">Agregar Nueva Contratación</h2>

<form action="contrataciones" method="post" class="mt-3">
  <input type="hidden" name="action" value="insertar">

  <div class="mb-3">
    <label class="form-label">ID Departamento:</label>
    <input type="number" class="form-control" name="idDepartamento" required>
  </div>

  <div class="mb-3">
    <label class="form-label">ID Empleado:</label>
    <input type="number" class="form-control" name="idEmpleado" required>
  </div>

  <div class="mb-3">
    <label class="form-label">ID Cargo:</label>
    <input type="number" class="form-control" name="idCargo" required>
  </div>

  <div class="mb-3">
    <label class="form-label">ID Tipo de Contratación:</label>
    <input type="number" class="form-control" name="idTipoContratacion" required>
  </div>

  <div class="mb-3">
    <label class="form-label">Fecha de Contratación:</label>
    <input type="date" class="form-control" name="fechaContratacion" required>
  </div>

  <div class="mb-3">
    <label class="form-label">Salario:</label>
    <input type="number" step="0.01" class="form-control" name="salario" required>
  </div>

  <div class="mb-3">
    <label class="form-label">Estado:</label>
    <select class="form-control" name="estado">
      <option value="true">Activo</option>
      <option value="false">Inactivo</option>
    </select>
  </div>

  <button type="submit" class="btn btn-success">Agregar Contratación</button>
</form>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<!-- Bootstrap JS Bundle -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

<%--Footer--%>
<footer class="bg-dark text-white text-center py-3 mt-4">
  <p>© 2025 Gestión RRHH - Universidad Don Bosco</p>
</footer>
</body>
</html>


