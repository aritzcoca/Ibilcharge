/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package packControl;

import jakarta.servlet.ServletConfig;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.BD;

/**
 *
 * @author aritz
 */
public class HorasReservadas extends HttpServlet {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    @Override
    public void init(ServletConfig cfg) {
        conn = BD.getConexion();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fecha = request.getParameter("fecha");
        List<String> horasOcupadas = new ArrayList<>();
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        if (fecha == null || fecha.isEmpty()) {
            out.print(""); // Devuelve vacío si no hay fecha válida
            return;
        }

        try {
            String sql = "SELECT HOUR(Fecha) AS hora FROM reservas WHERE DATE(Fecha) = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, fecha);
            rs = ps.executeQuery();

            while (rs.next()) {
                int h = rs.getInt("hora");
                horasOcupadas.add(String.format("%02d:00", h));
            }

            out.write(String.join(",", horasOcupadas));
            out.flush();
        } catch (SQLException e) {
            e.printStackTrace();
            out.write("ERROR");
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException ignored) {
            }
            out.close(); 
        }
    }
}