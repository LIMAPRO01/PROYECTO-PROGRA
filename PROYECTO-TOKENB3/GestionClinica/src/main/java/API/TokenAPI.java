package API;

import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

public class TokenAPI {
    private static final String LOGIN_URL = "http://localhost:5065/api/Auth/login";
    private static String token = null;
    private static long tokenExpiraEn = 0; // epoch segundos de expiración
    private static final Gson gson = new Gson();

    private static void login() throws IOException {
        System.out.println("? Solicitando nuevo token a las: " + LocalDateTime.now());

        URL url = new URL(LOGIN_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        String jsonInput = "{\"usuario\":\"admin\",\"contraseña\":\"1234\"}";

        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonInput.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        int status = conn.getResponseCode();
        if (status == 200) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                StringBuilder responseStr = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    responseStr.append(line);
                }

                String json = responseStr.toString();
                System.out.println("🔐 JSON recibido: " + json);

                TokenResponse tr = gson.fromJson(json, TokenResponse.class);
                if (tr == null || tr.token == null || tr.token.isEmpty()) {
                    throw new IOException("Token no recibido en la respuesta.");
                }

                token = tr.token;
                // Extraemos expiración del token (opcional)
                tokenExpiraEn = extraerExpiracionToken(token);
                System.out.println("✅ Token obtenido: " + token);
            }
        } else {
            throw new IOException("Error en login, código HTTP: " + status);
        }

        conn.disconnect();
    }

    private static long extraerExpiracionToken(String token) {
        // Puedes implementar parseo JWT para extraer "exp"
        // Por simplicidad, no lo hacemos aquí
        // Solo guardamos el tiempo actual + 9 seg para renovar
        return System.currentTimeMillis() / 1000 + 9;
    }

    // Devuelve token solo si no está expirado o falta
    public static synchronized String getToken() throws IOException {
        long ahora = System.currentTimeMillis() / 1000;
        if (token == null || ahora >= tokenExpiraEn) {
            login();
        }
        return token;
    }

    // Fuerza renovar token
    public static synchronized void renovarToken() throws IOException {
        login();
    }

    private static class TokenResponse {
        String token;
    }
}







