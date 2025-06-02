/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package packControl;

import java.util.Map;
import retrofit2.Call;
import retrofit2.http.POST;

/**
 *
 * @author aritz
 */
public interface ServicioOCPP {
    
    @POST("/start")
    public Call<Map<String,Object>> start();
    @POST("/stop")
    public Call<Map<String,Object>> stop();
    
}
