package API;

import Models.Paciente;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class PacienteApi {

    
    
    
    private final String BASE_URL = "http://localhost:5065/api/pacientes";
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
    public List<Paciente> getAllPacientes() throws IOException {
        HttpURLConnection con = abrirConexionConReintento(BASE_URL, "GET");
        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            Type listType = new TypeToken<List<Paciente>>() {
            }.getType();
            return gson.fromJson(in, listType);
        }
    }

    // GET BY ID
    public Paciente getPacienteById(int id) throws IOException {
        HttpURLConnection con = abrirConexionConReintento(BASE_URL + "/" + id, "GET");
        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            return gson.fromJson(in, Paciente.class);
        }
    }

    // BUSCAR
    public List<Paciente> buscarPacientes(String dato) throws IOException {
        String urlString = BASE_URL + "/buscar?dato=" + URLEncoder.encode(dato, StandardCharsets.UTF_8);
        HttpURLConnection con = abrirConexionConReintento(urlString, "GET");
        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            Type listType = new TypeToken<List<Paciente>>() {
            }.getType();
            return gson.fromJson(in, listType);
        }
    }

    // POST
    public boolean addPaciente(Paciente paciente) throws IOException {
        
        String json = gson.toJson(paciente);
       
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
    public boolean updatePaciente(Paciente paciente) throws IOException {
        String json = gson.toJson(paciente);
        HttpURLConnection conn = abrirConexionConReintento(BASE_URL + "/" + paciente.getIdPaciente(), "PUT", json);

        int responseCode = conn.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK && responseCode != HttpURLConnection.HTTP_NO_CONTENT) {
            try (BufferedReader errorReader = new BufferedReader(new InputStreamReader(conn.getErrorStream(), StandardCharsets.UTF_8))) {
                String errorResponse = errorReader.lines().collect(Collectors.joining("\n"));
                throw new IOException("Error al actualizar paciente (" + responseCode + "): " + errorResponse);
            }
        }

        conn.disconnect();
        return true;
    }

    // DELETE
    public boolean deletePaciente(int id) throws IOException {
        HttpURLConnection con = abrirConexionConReintento(BASE_URL + "/" + id, "DELETE");
        int code = con.getResponseCode();
        con.disconnect();
        return code == HttpURLConnection.HTTP_NO_CONTENT || code == HttpURLConnection.HTTP_OK;
    }
}

  

    

    

