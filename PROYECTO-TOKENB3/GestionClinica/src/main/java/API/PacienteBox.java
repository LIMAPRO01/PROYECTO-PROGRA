/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package API;

import Models.Paciente;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Windows
 */
public class PacienteBox {
  private static final String BASE_URL = "http://localhost:5065/api/pacientes";

    public List<Paciente> getAllPacientes() throws Exception {
        URL url = new URL(BASE_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("Authorization", "Bearer " + TokenAPI.getToken()); 

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Error HTTP: " + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            response.append(line);
        }
        conn.disconnect();

        List<Paciente> Pacientes = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(response.toString());

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObj = jsonArray.getJSONObject(i);
            
            Paciente paciente = new Paciente();
            
            paciente.setIdPaciente(jsonObj.getInt("idPaciente"));
            paciente.setNombre(jsonObj.getString("nombre"));
            paciente.setApellido(jsonObj.getString("apellido"));
            Pacientes.add(paciente);
        }

        return Pacientes;
    }
    public String getPacienteById(int idPaciente) {
    try {
        List<Paciente> Pacientes = getAllPacientes();
        for (Paciente c : Pacientes) {
            if (c.getIdPaciente() == idPaciente) {
                return c.getNombre();
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return "";
}
} 
