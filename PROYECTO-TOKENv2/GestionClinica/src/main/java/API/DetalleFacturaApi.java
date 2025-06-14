/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package API;

import Models.DetalleFactura;
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
public class DetalleFacturaApi {
  private final String BASE_URL = "http://localhost:5065/api/detallefactura";
    private final Gson gson = new Gson();

    public List<DetalleFactura> getAllDetalleFacturas() throws IOException {
        URL url = new URL(BASE_URL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        List<DetalleFactura> detalles = gson.fromJson(in, new TypeToken<List<DetalleFactura>>() {}.getType());
        in.close();
        return detalles;
    }

    public DetalleFactura getDetalleFacturaById(int id) throws IOException {
        URL url = new URL(BASE_URL + "/" + id);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        DetalleFactura detalle = gson.fromJson(in, DetalleFactura.class);
        in.close();
        return detalle;
    }

    public boolean addDetalleFactura(DetalleFactura detalle) throws IOException {
        URL url = new URL(BASE_URL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);

        String json = gson.toJson(detalle);
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = json.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int responseCode = con.getResponseCode();
        return responseCode == HttpURLConnection.HTTP_CREATED || responseCode == HttpURLConnection.HTTP_OK;
    }

    public boolean updateDetalleFactura(DetalleFactura detalle) throws IOException {
        URL url = new URL(BASE_URL + "/" + detalle.getIdFactura());
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("PUT");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);

        String json = gson.toJson(detalle);
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = json.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        return con.getResponseCode() == HttpURLConnection.HTTP_OK;
    }

    public boolean deleteDetalleFactura(int id) throws IOException {
        URL url = new URL(BASE_URL + "/" + id);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("DELETE");

        return con.getResponseCode() == HttpURLConnection.HTTP_NO_CONTENT || con.getResponseCode() == HttpURLConnection.HTTP_OK;
    }  
}
