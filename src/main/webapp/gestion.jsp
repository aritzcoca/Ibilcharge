<%-- 
    Document   : gestion
    Created on : 3 ene 2025, 9:36:53
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
        <h1>Bienvenid@, <%=session.getAttribute("nombre")%></h1>
        <h3>Que desea hacer?</h3>
        
        
        <form action="Gestionador" method="get">
            
            <h3>Cerrar Sesión: </h3>
            <input type="submit"  name="btnSubmit" value="Cerrar Sesion">
        </form>
        
        <form action="Gestionador" method="get">
            
            <h3>Registrar Coche: </h3>
            <input type="submit"  name="btnSubmit"  value="Registrar Coche">
        </form>
        
        <form action="Gestionador" method="get">
            
            <h3>Añadir coche a mi perfil: </h3>
            <input type="submit"  name="btnSubmit"  value="Add Coche">
        </form>
        
        <form action="Gestionador" method="get">
            
            <h3>Hacer Reserva: </h3>
            <input type="submit"  name="btnSubmit"  value="Hacer Reserva">
        </form>
        
        <form action="Gestionador" method="get">
            
            <h3>Ver Mis Reservas: </h3>
            <input type="submit"  name="btnSubmit" value="Ver Reservas">
        </form>
        
        <form action="Gestionador" method="get">
            
            <h3>Activar Cargador: </h3>
            <input type="submit"  name="btnSubmit" value="Activar Cargador">
        </form>
         
        
        
        
    </body>
</html>
