/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package API;

import Models.Medico;
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
public class MedicoBox {
  private static final String BASE_URL = "http://localhost:5065/api/medicos";

    public List<Medico> getAllMedicos() throws Exception {
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

        List<Medico> Medicos = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(response.toString());

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObj = jsonArray.getJSONObject(i);
            
            Medico medico = new Medico();
            
            medico.setIdMedico(jsonObj.getInt("idmedico"));
            medico.setNombre(jsonObj.getString("nombre"));
            medico.setApellido(jsonObj.getString("apellido"));
            Medicos.add(medico);
        }

        return Medicos;
    }
    public String getAllMedicos(int idmedico) {
    try {
        List<Medico> Medicos = getAllMedicos();
        for (Medico c : Medicos) {
            if (c.getIdMedico() == idmedico) {
                return c.getNombre();
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return "";
}
   
}

