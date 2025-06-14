/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package API;

import Models.DetalleReceta;
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
public class DetalleRecetaApi {
    
    private final String BASE_URL = "http://localhost:5065/api/detallereceta";
    private final Gson gson = new Gson();

    public List<DetalleReceta> getAllDetalleRecetas(int idReceta) throws IOException {
        URL url = new URL(BASE_URL);    
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Authorization", "Bearer " + TokenAPI.getToken());

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        List<DetalleReceta> detalles = gson.fromJson(in, new TypeToken<List<DetalleReceta>>() {}.getType());
        in.close();
        return detalles;
    }

    public DetalleReceta getDetalleRecetaById(int id) throws IOException {
        URL url = new URL(BASE_URL + "/" + id);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Authorization", "Bearer " + TokenAPI.getToken());

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        DetalleReceta detalle = gson.fromJson(in, DetalleReceta.class);
        in.close();
        return detalle;
    }

    public boolean addDetalleReceta(DetalleReceta detalle) throws IOException {
        URL url = new URL(BASE_URL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + TokenAPI.getToken());
        con.setDoOutput(true);

        String json = gson.toJson(detalle);
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = json.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int responseCode = con.getResponseCode();
        return responseCode == HttpURLConnection.HTTP_CREATED || responseCode == HttpURLConnection.HTTP_OK;
    }

    public boolean updateDetalleReceta(DetalleReceta detalle) throws IOException {
        URL url = new URL(BASE_URL + "/" + detalle.getIdDetalle());
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("PUT");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + TokenAPI.getToken());
        con.setDoOutput(true);

        String json = gson.toJson(detalle);
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = json.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        return con.getResponseCode() == HttpURLConnection.HTTP_OK;
    }

    public boolean deleteDetalleReceta(int id) throws IOException {
        URL url = new URL(BASE_URL + "/" + id);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("DELETE");
        con.setRequestProperty("Authorization", "Bearer " + TokenAPI.getToken());

        return con.getResponseCode() == HttpURLConnection.HTTP_NO_CONTENT || con.getResponseCode() == HttpURLConnection.HTTP_OK;
    }

    public List<DetalleReceta> getAllDetalleRecetas() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}

