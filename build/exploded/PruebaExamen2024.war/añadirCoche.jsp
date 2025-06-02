<%-- 
    Document   : añadirCoche
    Created on : 3 abr 2025, 11:40:05
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
        <h1>Añadir Coche</h1>
        <h2>Si es un coche ya registrado, añadelo a tu perfil.</h2>
        <h2>Si es un coche sin registrar, registralo primero y añadelo a tu perfil.</h2>
        <a href="registrarCoche.jsp">Registrar un coche nuevo</a>
          <% 
                if(request.getParameter("error")!=null){
            %>
        
           <h3 style="color:red;"><%=request.getParameter("error")%></h3>
            
            <% }%>
        
        <form action="AddCoche" method="get">
            <h3>Añadir coche a tu perfil:  </h3><br><!-- comment -->
           
            Matrícula:<input type="text" name="matricula" id="matricula" required><br><br>

            <input type="submit" value="Añadir"><br><br>
            <input type="reset" value="LIMPIAR">
    </body>
</html>
