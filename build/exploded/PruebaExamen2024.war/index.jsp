<%-- 
    Document   : index
    Created on : 3 ene 2025, 9:36:22
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
        <h1>Bienvenido a IbilCharge by UPV/EHU</h1>
        
        
        <% 
                if(request.getParameter("error")!=null){
            %>
        
           <h3 style="color:red;"><%=request.getParameter("error")%></h3>
            
            <% }%>
        
        <form action="Controlador" method="get">
            <h3>Iniciar Sesión:  </h3><br><!-- comment -->
            Email:<input type="email" name="email" id="email" required><br><br>
            Contraseña:<input type="password" name="password" id="password" required><br><br>
            <input type="submit" value="Iniciar Sesion"><br><br>
            <input type="reset" value="LIMPIAR">
       
        </form>
            
             <form action="Registrar" method="get">
            <h3>Registrar Usuario:  </h3><br><!-- comment -->
            Nombre:<input type="text" name="nombre" id="nombre" required><br><br>
            Email:<input type="email" name="email" id="email" required><br><br>
            Contraseña:<input type="password" name="password" id="password" required><br><br>
          
            <input type="submit" value="Registrarme"><br><br>
            <input type="reset" value="LIMPIAR">
       
        </form>
    </body>
</html>
