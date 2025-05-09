using HospitalAPI.Controllers;
using HospitalAPI.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Data.SqlClient;
using System.Data;

namespace HospitalAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class CitasController : ControllerBase
    {
        private readonly IConfiguration _configuration;

        public CitasController(IConfiguration configuration)
        {
            _configuration = configuration;
        }

        // GET: api/Citas
        [HttpGet]
        public JsonResult Get()
        {
            string query = @"
                SELECT 
                    Citas.CitaID, 
                    Citas.PacienteID, 
                    Pacientes.Nombre + ' ' + Pacientes.Apellido AS PacienteNombreCompleto, 
                    Citas.MedicoID, 
                    Medicos.Nombre + ' ' + Medicos.Apellido AS MedicoNombreCompleto, 
                    Citas.FechaHora, 
                    Citas.Motivo, 
                    Citas.Estado
                FROM Citas
                INNER JOIN Pacientes ON Citas.PacienteID = Pacientes.PacienteID
                INNER JOIN Medicos ON Citas.MedicoID = Medicos.MedicoID";

            List<object> result = new List<object>();
            string sqlDataSource = _configuration.GetConnectionString("HospitalDB") ?? throw new InvalidOperationException("Connection string 'HospitalDB' is not configured.");

            using (SqlConnection myCon = new SqlConnection(sqlDataSource))
            {
                myCon.Open();
                using (SqlCommand myCommand = new SqlCommand(query, myCon))
                {
                    using (SqlDataReader reader = myCommand.ExecuteReader())
                    {
                        while (reader.Read())
                        {
                            result.Add(new
                            {
                                CitaID = reader["CitaID"],
                                PacienteID = reader["PacienteID"],
                                PacienteNombreCompleto = reader["PacienteNombreCompleto"],
                                MedicoID = reader["MedicoID"],
                                MedicoNombreCompleto = reader["MedicoNombreCompleto"],
                                FechaHora = reader["FechaHora"],
                                Motivo = reader["Motivo"],
                                Estado = reader["Estado"],
                            });
                        }
                    }
                }
            }

            return new JsonResult(result);
        }

        // POST api/Citas
        [HttpPost]
        public JsonResult Post(Citas citas)
        {
            string query = "INSERT INTO Citas (PacienteID, MedicoID, FechaHora, Motivo, Estado) VALUES (@PacienteID, @MedicoID, @FechaHora, @Motivo, @Estado)";
            string sqlDataSource = _configuration.GetConnectionString("HospitalDB") ?? throw new InvalidOperationException("Connection string 'HospitalDB' is not configured.");

            using (SqlConnection myCon = new SqlConnection(sqlDataSource))
            {
                myCon.Open();
                using (SqlCommand myCommand = new SqlCommand(query, myCon))
                {
                    myCommand.Parameters.AddWithValue("@PacienteID", citas.PacienteID);
                    myCommand.Parameters.AddWithValue("@MedicoID", citas.MedicoID);
                    myCommand.Parameters.AddWithValue("@FechaHora", citas.FechaHora);
                    myCommand.Parameters.AddWithValue("@Motivo", citas.Motivo);
                    myCommand.Parameters.AddWithValue("@Estado", citas.Estado);

                    myCommand.ExecuteNonQuery();
                }
            }

            return new JsonResult("Cita agregado correctamente.");
        }

        // PUT api/Citas/5      
        [HttpPut("{id}")]
        public JsonResult Put(int id, Citas citas)
        {
            string query = "UPDATE Citas SET PacienteID = @PacienteID, MedicoID = @MedicoID, FechaHora = @FechaHora, Motivo = @Motivo, Estado = @Estado WHERE CitaID = @CitaID";
            string sqlDataSource = _configuration.GetConnectionString("HospitalDB") ?? throw new InvalidOperationException("Connection string 'HospitalDB' is not configured.");

            using (SqlConnection myCon = new SqlConnection(sqlDataSource))
            {
                myCon.Open();
                using (SqlCommand myCommand = new SqlCommand(query, myCon))
                {
                    myCommand.Parameters.AddWithValue("@CitaID", id);
                    myCommand.Parameters.AddWithValue("@PacienteID", citas.PacienteID);
                    myCommand.Parameters.AddWithValue("@MedicoID", citas.MedicoID);
                    myCommand.Parameters.AddWithValue("@FechaHora", citas.FechaHora);
                    myCommand.Parameters.AddWithValue("@Motivo", citas.Motivo);
                    myCommand.Parameters.AddWithValue("@Estado", citas.Estado);

                    myCommand.ExecuteNonQuery();
                }
            }

            return new JsonResult("Cita actualizada correctamente.");
        }
  // DELETE api/Medicos/5
        [HttpDelete("{id}")]    
        public JsonResult Delete(int id)
        {
            string query = "DELETE FROM Citas WHERE CitaID = @CitaID";
            string sqlDataSource = _configuration.GetConnectionString("HospitalDB") ?? throw new InvalidOperationException("Connection string 'HospitalDB' is not configured.");

            using (SqlConnection myCon = new SqlConnection(sqlDataSource))
            {
                myCon.Open();
                using (SqlCommand myCommand = new SqlCommand(query, myCon))
                {
                    myCommand.Parameters.AddWithValue("@CitaID", id);
                    myCommand.ExecuteNonQuery();
                }
            }

            return new JsonResult("Cita eliminada correctamente.");
        }
    }   
}




