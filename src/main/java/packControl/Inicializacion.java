/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package packControl;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author aritz
 */
@WebListener
public class Inicializacion implements ServletContextListener{
    private final static long HORA = 60000;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Inicio");
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                //hrow new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                System.out.println("Ha pasado un minuto: "+new Date());
            }
        },  new Date(System.currentTimeMillis()/HORA*HORA),HORA);
    }
}
