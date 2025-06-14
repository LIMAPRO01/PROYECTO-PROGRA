/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package API;
import Models.Medicamento;
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
public class MedicamentoApi {
    private final String BASE_URL = "http://localhost:5065/api/medicamentos";
    private final Gson gson = new Gson();

    public List<Medicamento> getAllMedicamentos() throws IOException {
        URL url = new URL(BASE_URL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Authorization", "Bearer " + TokenAPI.getToken());

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        List<Medicamento> medicamentos = gson.fromJson(in, new TypeToken<List<Medicamento>>() {}.getType());
        in.close();
        return medicamentos;
    }

    public Medicamento getMedicamentoById(int id) throws IOException {
        URL url = new URL(BASE_URL + "/" + id);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Authorization", "Bearer " + TokenAPI.getToken());

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        Medicamento medicamento = gson.fromJson(in, Medicamento.class);
        in.close();
        return medicamento;
    }
        // BUSCAR
    public List<Medicamento> buscarMedicamentos(String dato) throws IOException {
        
        String urlString = BASE_URL + "/buscar?dato=" + URLEncoder.encode(dato, StandardCharsets.UTF_8);
    URL url = new URL(urlString);
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestMethod("GET");
    con.setRequestProperty("Authorization", "Bearer " + TokenAPI.getToken());
    

    try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
        return gson.fromJson(in, new TypeToken<List<Medicamento>>() {}.getType());
    }}

    public boolean addMedicamento(Medicamento medicamento) throws IOException {
        URL url = new URL(BASE_URL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + TokenAPI.getToken());
        con.setDoOutput(true);

        String json = gson.toJson(medicamento);
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = json.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int responseCode = con.getResponseCode();
        return responseCode == HttpURLConnection.HTTP_CREATED || responseCode == HttpURLConnection.HTTP_OK;
    }

    public boolean updateMedicamento(Medicamento medicamento) throws IOException {
        URL url = new URL(BASE_URL + "/" + medicamento.getIdMedicamento());
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("PUT");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + TokenAPI.getToken());
        con.setDoOutput(true);

        String json = gson.toJson(medicamento);
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = json.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        return con.getResponseCode() == HttpURLConnection.HTTP_OK;
    }

    public boolean deleteMedicamento(int id) throws IOException {
        URL url = new URL(BASE_URL + "/" + id);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("DELETE");
        con.setRequestProperty("Authorization", "Bearer " + TokenAPI.getToken());

        return con.getResponseCode() == HttpURLConnection.HTTP_NO_CONTENT || con.getResponseCode() == HttpURLConnection.HTTP_OK;
    }
}
