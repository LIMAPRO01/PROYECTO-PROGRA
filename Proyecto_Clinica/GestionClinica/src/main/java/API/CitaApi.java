package API;
import API.TokenAPI;
import Models.Cita;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 *
 * @author Mario
 */
public class CitaApi {
    
   private final String BASE_URL = "http://localhost:5065/api/citas"; 
    private final Gson gson = new Gson();

    public List<Cita> getAllCita() throws IOException {
        URL url = new URL(BASE_URL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Authorization", "Bearer " + TokenAPI.getToken());

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        List<Cita> citas = gson.fromJson(in, new TypeToken<List<Cita>>() {}.getType());
        in.close();
        return citas;
    }

    public Cita getCitaById(int id) throws IOException {
        URL url = new URL(BASE_URL + "/" + id);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Authorization", "Bearer " + TokenAPI.getToken());

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        Cita cita = gson.fromJson(in, Cita.class);
        in.close();
        return cita;
    }
 // --- NUEVO MÉTODO: Buscar Recetas por Nombre (usando HttpURLConnection) ---
   public List<Cita> buscarcita(String dato) throws IOException {
        URL url = new URL(BASE_URL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Authorization", "Bearer " + TokenAPI.getToken());

        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            Type listType = new TypeToken<List<Cita>>() {
            }.getType();
            return gson.fromJson(in, listType);
        }
    }
    // 
    public boolean addCita(Cita Cita) throws IOException {
        URL url = new URL(BASE_URL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + TokenAPI.getToken());
        con.setDoOutput(true);

        String json = gson.toJson(Cita);
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = json.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int responseCode = con.getResponseCode();
        return responseCode == HttpURLConnection.HTTP_CREATED || responseCode == HttpURLConnection.HTTP_OK;
    }
    // PUT
  public boolean updateCita(Cita cita) throws IOException {
        URL url = new URL(BASE_URL + "/" + cita.getIdCita());
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("PUT");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + TokenAPI.getToken());
        con.setDoOutput(true);

        String json = gson.toJson(cita);
         System.out.println("== JSON ENVIADO ==");
    System.out.println(json);
    System.out.println("== URL LLAMADA ==");
    System.out.println(url.toString());
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = json.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        return con.getResponseCode() == HttpURLConnection.HTTP_OK;
    }


   public boolean deleteCita(int id) throws IOException {
        URL url = new URL(BASE_URL + "/" + id);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("DELETE");
        con.setRequestProperty("Authorization", "Bearer " + TokenAPI.getToken());

        return con.getResponseCode() == HttpURLConnection.HTTP_NO_CONTENT || con.getResponseCode() == HttpURLConnection.HTTP_OK;
    }

} 
    

