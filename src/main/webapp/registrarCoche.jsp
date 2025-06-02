<%-- 
    Document   : registrarCoche
    Created on : 3 abr 2025, 12:17:05
    Author     : aritz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IbilCharge</title>
        <link rel="stylesheet" href="css/estilos.css">
        <link rel="icon" type="image/x-icon" href="img/favicon.ico">
    </head>
    <body>
         <a href="gestion.jsp" class="return-button">← Return</a>
        <h1>Registrar Coche</h1>
        <h2>Si es un coche ya registrado, añadelo a tu perfil.</h2>
        <a href="añadirCoche.jsp">Añadir a mi perfil</a>
        <h2>Si es un coche sin registrar, registralo primero y añadelo a tu perfil.</h2>
          <% 
                if(request.getParameter("error")!=null){
            %>
        
           <h3 style="color:red;"><%=request.getParameter("error")%></h3>
            
            <% }%>
        
        <form action="RegistrarCoche" method="get">
            <h3>Registrar un coche nuevo:  </h3><br><!-- comment -->
           
            Matrícula:<input type="text" name="matricula" id="matricula" required><br><br>
            Marca:<input type="text" name="marca" id="marca" required><br><br><!-- comment -->
            Modelo:<input type="text" name="modelo" id="modelo" required><br><br>
            Color:<input type="text" name="color" id="color" required><br><br>
            
            <input type="submit" value="Registrar"><br><br>
            <input type="reset" value="LIMPIAR">
    </body>
</html>
