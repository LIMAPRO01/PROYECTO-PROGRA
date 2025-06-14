/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package API;

import Models.RecetaMedica;
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
public class RecetaMedicaApi {
    private final String BASE_URL = "http://localhost:5065/api/recetasmedicas";
    private final Gson gson = new Gson();

    public List<RecetaMedica> getAllRecetaMedicas() throws IOException {
        URL url = new URL(BASE_URL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Authorization", "Bearer " + TokenAPI.getToken());

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        List<RecetaMedica> recetas = gson.fromJson(in, new TypeToken<List<RecetaMedica>>() {}.getType());
        in.close();
        return recetas;
    }

    public RecetaMedica getRecetaMedicaById(int id) throws IOException {
        URL url = new URL(BASE_URL + "/" + id);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Authorization", "Bearer " + TokenAPI.getToken());

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        RecetaMedica receta = gson.fromJson(in, RecetaMedica.class);
        in.close();
        return receta;
    }

    public boolean addRecetaMedica(RecetaMedica receta) throws IOException {
        URL url = new URL(BASE_URL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + TokenAPI.getToken());
        con.setDoOutput(true);

        String json = gson.toJson(receta);
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = json.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int responseCode = con.getResponseCode();
        return responseCode == HttpURLConnection.HTTP_CREATED || responseCode == HttpURLConnection.HTTP_OK;
    }

    public boolean updateRecetaMedica(RecetaMedica receta) throws IOException {
        URL url = new URL(BASE_URL + "/" + receta.getIdreceta());
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("PUT");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + TokenAPI.getToken());
        con.setDoOutput(true);

        String json = gson.toJson(receta);
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = json.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        return con.getResponseCode() == HttpURLConnection.HTTP_OK;
    }

    public boolean deleteRecetaMedica(int id) throws IOException {
        URL url = new URL(BASE_URL + "/" + id);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("DELETE");
        con.setRequestProperty("Authorization", "Bearer " + TokenAPI.getToken());

        return con.getResponseCode() == HttpURLConnection.HTTP_NO_CONTENT || con.getResponseCode() == HttpURLConnection.HTTP_OK;
    }
}
