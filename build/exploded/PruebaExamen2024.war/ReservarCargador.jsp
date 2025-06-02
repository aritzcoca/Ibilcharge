<%-- 
    Document   : ReservarCargador
    Created on : 29 mar 2025, 11:00:51
    Author     : aritz
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="utils.BD"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
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
        <h1>Reservar Cargador</h1>
        
         <% 
                if(request.getParameter("error")!=null){
            %>
        
            <h3 style="color:red;"><%=request.getParameter("error")%></h3>            
            <% }%>
        
                    <%!  
            private Connection conn;
   private PreparedStatement ps;
   private ResultSet rs;
   
   
            public void jspInit(){
                conn = BD.getConexion();
            } 
        %>
        <form action="Reservar" method="get">
            <h3>Reservar:  </h3><br><!-- comment -->
            Fecha: <input type="date" id="fecha" name="fecha" required><br><br>
            Hora: 
                <select id="hora" name="hora" required>
                 <option value="">Selecciona una hora</option>
                </select>
            <label for="idCoche">Selecciona un coche:</label>
             <select name="idCoche" id="idCoche" size="1">
            <%
            int id = (int) session.getAttribute("id");
            try{
            
            String sql= "SELECT c.* FROM usuariocoche as uc " +
    "JOIN coche as c ON uc.IdCoche = c.idCoche " +
    "WHERE uc.Id = ?";
            ps=conn.prepareStatement(sql);
           ps.setInt(1, id);
           rs=ps.executeQuery();
           
           while(rs.next()){
            
                String matricula=rs.getString("c.Matricula");
                int idcoche = rs.getInt("c.idCoche");
            
            %>
           
            <option value="<%=idcoche%>"><%=matricula%></option>
            
            <%
                }
            %>
              </select>
    <br>
    <%
                 }
        catch(SQLException e){
            
            e.printStackTrace();
            
        }
            %>
            <input type="submit" value="Reservar"><br><br>
            <input type="reset" value="LIMPIAR">
        </form>
        <script>
    function cargarHorasDisponibles(fechaSeleccionada) {
        const horasDropdown = document.getElementById("hora");
        horasDropdown.innerHTML = "<option value=''>Cargando horas...</option>";

        fetch("HorasReservadas?fecha=" + fechaSeleccionada)
            .then(response => response.text())
            .then(textoPlano => {
                const horasReservadas = textoPlano ? textoPlano.split(",") : [];

                horasDropdown.innerHTML = "<option value=''>Selecciona una hora</option>";
                const ahora = new Date();
            const hoyISO = ahora.toISOString().split("T")[0];
                
                
                for (let h = 8; h <= 20; h++) {
                    let hora = (h < 10 ? "0" + h : h) + ":00";
                    
                    // Si es hoy y la hora ya pasó, saltarla
                if (fechaSeleccionada === hoyISO && h <= ahora.getHours()) {
                    continue;
                }
                    if (!horasReservadas.includes(hora)) {
                        let option = document.createElement("option");
                        option.value = hora;
                        option.text = hora;
                        horasDropdown.appendChild(option);
                    }
                }

                if (horasDropdown.options.length === 1) {
                    horasDropdown.innerHTML = "<option value=''>Sin horas disponibles</option>";
                }
            })
            .catch(error => {
                console.error("Error al obtener horas:", error);
                horasDropdown.innerHTML = "<option value=''>Error al cargar</option>";
            });
    }

    window.onload = function () {
        const fechaInput = document.getElementById("fecha");
        const hoy = new Date();
        const max = new Date();
        max.setDate(hoy.getDate() + 7);

        fechaInput.min = hoy.toISOString().split("T")[0];
        fechaInput.max = max.toISOString().split("T")[0];

        fechaInput.addEventListener("change", function () {
            cargarHorasDisponibles(this.value);
        });
    }
</script>
</body>
</html>


