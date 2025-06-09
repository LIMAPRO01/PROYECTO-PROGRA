/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package API;
import Models.Factura;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
/**
 *
 * @author Mario
 */
public class FacturaApi {
    private final String BASE_URL = "http://localhost:5065/api/facturas";
    private final Gson gson = new Gson();
     private void agregarAutorizacion(HttpURLConnection con) throws IOException {
        con.setRequestProperty("Authorization", "Bearer " + TokenAPI.getToken());
    }

    public List<Factura> getAllFacturas() throws IOException {
        URL url = new URL(BASE_URL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
agregarAutorizacion(con); // ← TOKEN
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        List<Factura> facturas = gson.fromJson(in, new TypeToken<List<Factura>>() {}.getType());
        in.close();
        return facturas;
    }

    public Factura getFacturaById(int id) throws IOException {
        URL url = new URL(BASE_URL + "/" + id);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
agregarAutorizacion(con); // ← TOKEN
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        Factura factura = gson.fromJson(in, Factura.class);
        in.close();
        return factura;
    }

    public boolean addFactura(Factura factura) throws IOException {
        URL url = new URL(BASE_URL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);
agregarAutorizacion(con); // ← TOKEN
        String json = gson.toJson(factura);
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = json.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int responseCode = con.getResponseCode();
        return responseCode == HttpURLConnection.HTTP_CREATED || responseCode == HttpURLConnection.HTTP_OK;
    }
     // BUSCAR
    public List<Factura> buscarFacturas(String dato) throws IOException {
        
        String urlString = BASE_URL + "/buscar?dato=" + URLEncoder.encode(dato, StandardCharsets.UTF_8);
    URL url = new URL(urlString);
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestMethod("GET");
    agregarAutorizacion(con); // ← TOKEN

    try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
        return gson.fromJson(in, new TypeToken<List<Factura>>() {}.getType());
    }

    }

    public boolean updateFactura(Factura factura) throws IOException {
        URL url = new URL(BASE_URL + "/" + factura.getIdFactura());
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("PUT");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);
agregarAutorizacion(con); // ← TOKEN
        String json = gson.toJson(factura);
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = json.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        return con.getResponseCode() == HttpURLConnection.HTTP_OK;
    }

    public boolean deleteFactura(int id) throws IOException {
        URL url = new URL(BASE_URL + "/" + id);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("DELETE");
agregarAutorizacion(con); // ← TOKEN
        return con.getResponseCode() == HttpURLConnection.HTTP_NO_CONTENT || con.getResponseCode() == HttpURLConnection.HTTP_OK;
    }
    
}
