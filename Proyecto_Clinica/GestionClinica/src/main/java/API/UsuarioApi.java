package API;

import Models.LoginRequest;
import Models.Usuario;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class UsuarioApi {

    private final String BASE_URL = "http://localhost:5065/api/usuarios";
    private final Gson gson = new Gson();

    // Método reutilizable para agregar token
    private void agregarAutorizacion(HttpURLConnection con) throws IOException {
        con.setRequestProperty("Authorization", "Bearer " + TokenAPI.getToken());
    }

    // Obtener todos los usuarios
    public List<Usuario> getAllUsuarios() throws IOException {
        URL url = new URL(BASE_URL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        agregarAutorizacion(con); // ← TOKEN

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        List<Usuario> usuarios = gson.fromJson(in, new TypeToken<List<Usuario>>() {}.getType());
        in.close();
        return usuarios;
    }

    // Obtener un usuario por ID
    public Usuario getMedicoById(int Id) throws IOException {
        URL url = new URL(BASE_URL + "/" + Id);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        agregarAutorizacion(con); // ← TOKEN

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        Usuario usuario = gson.fromJson(in, Usuario.class);
        in.close();
        return usuario;
    }

    // Crear un nuevo usuario
    public boolean addUsuario(Usuario usuario) throws IOException {
        URL url = new URL(BASE_URL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        agregarAutorizacion(con); // ← TOKEN
        con.setDoOutput(true);

        String json = gson.toJson(usuario);
        try (OutputStream os = con.getOutputStream()) {
            os.write(json.getBytes(StandardCharsets.UTF_8));
        }

        int responseCode = con.getResponseCode();
        return responseCode == HttpURLConnection.HTTP_CREATED || responseCode == HttpURLConnection.HTTP_OK;
    }

    // Actualizar un usuario
    public boolean updateUsuario(Usuario usuario) throws IOException {
        URL url = new URL(BASE_URL + "/" + usuario.getIdUsuario());
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("PUT");
        con.setRequestProperty("Content-Type", "application/json");
        agregarAutorizacion(con); // ← TOKEN
        con.setDoOutput(true);

        String json = gson.toJson(usuario);
        try (OutputStream os = con.getOutputStream()) {
            os.write(json.getBytes(StandardCharsets.UTF_8));
        }

        int responseCode = con.getResponseCode();
        return responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED;
    }

    // Eliminar un usuario
    public boolean deleteUsuario(int Id) throws IOException {
        URL url = new URL(BASE_URL + "/" + Id);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("DELETE");
        agregarAutorizacion(con); // ← TOKEN

        int responseCode = con.getResponseCode();
        return responseCode == HttpURLConnection.HTTP_NO_CONTENT || responseCode == HttpURLConnection.HTTP_OK;
    }

    // Login SIN token (no agregar Authorization aquí)
    public Usuario loginUsuario(String nombreUsuario, String contraseña) throws IOException {
        URL url = new URL("http://localhost:5065/api/Usuarios/login");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setNombreUsuario(nombreUsuario);
        loginRequest.setContraseña(contraseña);

        String json = gson.toJson(loginRequest);
        try (OutputStream os = con.getOutputStream()) {
            os.write(json.getBytes(StandardCharsets.UTF_8));
        }

        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
            Usuario usuarioLogueado = gson.fromJson(in, Usuario.class);
            in.close();
            return usuarioLogueado;
        } else {
            return null; // Login fallido
        }
    }
}



