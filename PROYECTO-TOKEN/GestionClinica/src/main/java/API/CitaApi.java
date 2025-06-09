/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package API;

import Models.Cita;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;
/**
 *
 * @author Mario
 */
public class CitaApi {
    
   private final String BASE_URL = "http://localhost:5065/api/citas"; 
       Gson gson = new GsonBuilder()
    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    .create();
    

    // Abrir conexión para métodos GET, DELETE (sin cuerpo)
    private HttpURLConnection abrirConexionConReintento(String endpoint, String metodo) throws IOException {
        HttpURLConnection con = abrirConexion(endpoint, metodo);
        int status = con.getResponseCode();
        if (status == 401) {
            TokenAPI.renovarToken();
            con.disconnect();
            con = abrirConexion(endpoint, metodo);
        }
        return con;
    }

    // Abrir conexión para métodos POST, PUT con cuerpo JSON
    private HttpURLConnection abrirConexionConReintento(String endpoint, String metodo, String jsonCuerpo) throws IOException {
        HttpURLConnection con = abrirConexion(endpoint, metodo);

        // Enviar cuerpo antes de leer respuesta
        if (jsonCuerpo != null && (metodo.equals("POST") || metodo.equals("PUT"))) {
            con.setDoOutput(true);
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonCuerpo.getBytes(StandardCharsets.UTF_8);
                os.write(input);
            }
        }

        int status = con.getResponseCode();

        if (status == 401) {
            TokenAPI.renovarToken();
            con.disconnect();

            con = abrirConexion(endpoint, metodo);

            if (jsonCuerpo != null && (metodo.equals("POST") || metodo.equals("PUT"))) {
                con.setDoOutput(true);
                try (OutputStream os = con.getOutputStream()) {
                    byte[] input = jsonCuerpo.getBytes(StandardCharsets.UTF_8);
                    os.write(input);
                }
            }
        }

        return con;
    }

    private HttpURLConnection abrirConexion(String endpoint, String metodo) throws IOException {
        URL url = new URL(endpoint);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod(metodo);
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + TokenAPI.getToken());
        return con;
    }

    // GET ALL
    public List<Cita> getAllCitas() throws IOException {
        HttpURLConnection con = abrirConexionConReintento(BASE_URL, "GET");
        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            Type listType = new TypeToken<List<Cita>>() {
            }.getType();
            return gson.fromJson(in, listType);
        }
    }

    // GET BY ID
    public Cita getCitaById (int id) throws IOException {
        HttpURLConnection con = abrirConexionConReintento(BASE_URL + "/" + id, "GET");
        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            return gson.fromJson(in, Cita.class);
        }
    }

    // BUSCAR
    public List<Cita> buscarCitas(String dato) throws IOException {
        String urlString = BASE_URL + "/buscar?dato=" + URLEncoder.encode(dato, StandardCharsets.UTF_8);
        HttpURLConnection con = abrirConexionConReintento(urlString, "GET");
        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            Type listType = new TypeToken<List<Cita>>() {
            }.getType();
            return gson.fromJson(in, listType);
        }
    }

    // POST
    public boolean addCita(Cita cita) throws IOException {
        String json = gson.toJson(cita);
        HttpURLConnection conn = abrirConexionConReintento(BASE_URL, "POST", json);

        int responseCode = conn.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_CREATED && responseCode != HttpURLConnection.HTTP_OK) {
            try (BufferedReader errorReader = new BufferedReader(new InputStreamReader(conn.getErrorStream(), StandardCharsets.UTF_8))) {
                String errorResponse = errorReader.lines().collect(Collectors.joining("\n"));
                throw new IOException("Error al agregar paciente: " + errorResponse);
            }
        }

        conn.disconnect();
        return true;
    }

    // PUT
    public boolean  updateCita(Cita cita) throws IOException {
       
    String token = TokenAPI.getToken();

    URL url = new URL("http://localhost:5065/api/Citas/" + cita.getIdCita());
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestMethod("PUT");
    con.setRequestProperty("Content-Type", "application/json");
    con.setRequestProperty("Authorization", "Bearer " + token);
    con.setDoOutput(true);

    Gson gson = new Gson();
    String jsonInputString = gson.toJson(cita);
    System.out.println("JSON enviado: " + jsonInputString); // Para depurar

    try (OutputStream os = con.getOutputStream()) {
        byte[] input = jsonInputString.getBytes("utf-8");
        os.write(input, 0, input.length);
    }

    int responseCode = con.getResponseCode();
    System.out.println("Código respuesta: " + responseCode); // útil para depuración

    return responseCode == 200 || responseCode == 204;
}


    // DELETE
    public boolean deleteCita(int id) throws IOException {
        HttpURLConnection con = abrirConexionConReintento(BASE_URL + "/" + id, "DELETE");
        int code = con.getResponseCode();
        con.disconnect();
        return code == HttpURLConnection.HTTP_NO_CONTENT || code == HttpURLConnection.HTTP_OK;
    }
} 
    

