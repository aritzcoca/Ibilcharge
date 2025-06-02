<%-- 
    Document   : inserccionCorrecta3
    Created on : 30 mar 2025, 11:33:58
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
    <script src="https://cdn.jsdelivr.net/npm/@emailjs/browser@3/dist/email.min.js"></script>
    <script>
        (function(){
            emailjs.init("bukRb9gTCDsG6o_o_");
        })();

        function enviarEmailReserva(name, date, hour, email) {
            emailjs.send("IbilCharge", "template_p6ejs0b", {
                name: name,
                date: date,
                hour: hour,
                email: email
            }).then(function(response) {
                console.log("✅ ¡Correo enviado con éxito!", response.status, response.text);
            }, function(error) {
                console.error("❌ Error al enviar correo:", error);
            });
        }

        window.onload = function() {
            let name = '<%= session.getAttribute("nombre") %>';  
            let date = '<%= session.getAttribute("date") %>';
            let hour = '<%= session.getAttribute("hour") %>';
            let email = '<%= session.getAttribute("email") %>';

            enviarEmailReserva(name, date, hour, email);
        };
    </script>
</head>
    <body>
        <h1>Reserva eliminada correctamente</h1>
        
         <a href="gestion.jsp">VOLVER</a>
    </body>
</html>
