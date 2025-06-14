/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package API;

import Models.MovimientoStock;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
/**
 *
 * @author Mario
 */
public class MovimientoStockApi {
    private final String BASE_URL = "http://localhost:5065/api/movimientostock";
    private final Gson gson = new Gson();

    public List<MovimientoStock> getAllMovimientoStock() throws IOException {
        URL url = new URL(BASE_URL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
      con.setRequestProperty("Authorization", "Bearer " + TokenAPI.getToken()); 
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        List<MovimientoStock> movimientos = gson.fromJson(in, new TypeToken<List<MovimientoStock>>() {}.getType());
        in.close();
        return movimientos;
    }

    public MovimientoStock getMovimientoStockById(int id) throws IOException {
        URL url = new URL(BASE_URL + "/" + id);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Authorization", "Bearer " + TokenAPI.getToken());
        
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        MovimientoStock movimiento = gson.fromJson(in, MovimientoStock.class);
        in.close();
        return movimiento;
    }

    public boolean addMovimientoStock(MovimientoStock movimiento) throws IOException {
        URL url = new URL(BASE_URL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + TokenAPI.getToken());
        con.setDoOutput(true);

        String json = gson.toJson(movimiento);
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = json.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int responseCode = con.getResponseCode();
        if ( responseCode == HttpURLConnection.HTTP_CREATED || responseCode == HttpURLConnection.HTTP_OK) {
         return true;
         } else {
        // Leer respuesta de error para ver qué pasó
        try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.err.println("Error al agregar movimiento: " + response.toString());
        }
        return false;
    }
    }

    public boolean updateMovimientoStock(MovimientoStock movimiento) throws IOException {
        URL url = new URL(BASE_URL + "/" + movimiento.getIdMovimiento());
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("PUT");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);

        String json = gson.toJson(movimiento);
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = json.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        return con.getResponseCode() == HttpURLConnection.HTTP_OK;
    }

    public boolean deleteMovimientoStock(int id) throws IOException {
        URL url = new URL(BASE_URL + "/" + id);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("DELETE");
        con.setRequestProperty("Authorization", "Bearer " + TokenAPI.getToken());

        return con.getResponseCode() == HttpURLConnection.HTTP_NO_CONTENT || con.getResponseCode() == HttpURLConnection.HTTP_OK;
    }
    
}
