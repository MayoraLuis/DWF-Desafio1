<%--
  Created by IntelliJ IDEA.
  User: luism
  Date: 2/19/2025
  Time: 8:28 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
  String idDepartamentoStr = request.getParameter("idDepartamento");
  int idDepartamento = 0;

  if (idDepartamentoStr != null && !idDepartamentoStr.isEmpty()) {
    try {
      idDepartamento = Integer.parseInt(idDepartamentoStr);
    } catch (NumberFormatException e) {
      System.out.println("Error: idDepartamento no es un número válido en editarDepartamento.jsp");
      response.sendRedirect("departamentos.jsp");
      return;
    }
  } else {
    System.out.println("Error: idDepartamento es nulo o vacío en editarDepartamento.jsp");
    response.sendRedirect("departamentos.jsp");
    return;
  }
%>

<!DOCTYPE html>
<html>
<head>
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <title>Editar Departamento</title>
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

<h2>Editar Departamento</h2>

<form action="departamentos" method="post">
  <input type="hidden" name="action" value="actualizar">
  <input type="hidden" name="idDepartamento" value="${departamento.idDepartamento}">

  <label>Nombre:</label>
  <input type="text" name="nombreDepartamento" value="${departamento.nombreDepartamento}" required>

  <label>Descripción:</label>
  <input type="text" name="descripcionDepartamento" value="${departamento.descripcionDepartamento}">

  <button type="submit">Actualizar</button>
</form>
<!-- Bootstrap JS  -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

<%--Footer--%>
<footer class="bg-dark text-white text-center py-3 mt-4">
  <p>© 2025 Gestión RRHH - Universidad Don Bosco</p>
</footer>
</body>
</html>