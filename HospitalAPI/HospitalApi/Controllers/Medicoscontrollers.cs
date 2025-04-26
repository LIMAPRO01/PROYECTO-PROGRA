using HospitalAPI.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Data.SqlClient;
using System.Data;

namespace HospitalAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class MedicosController : ControllerBase
    {
        private readonly IConfiguration _configuration;

        public MedicosController(IConfiguration configuration)
        {
            _configuration = configuration;
        }

        // GET: api/Medicos
        [HttpGet]
        public JsonResult Get()
        {
            string query = "SELECT * FROM Medicos";
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
                                MedicoID = reader["MedicoID"],
                                Nombre = reader["Nombre"],
                                Apellido = reader["Apellido"],
                                Especialidad = reader["Especialidad"],
                                Telefono = reader["Telefono"],
                                Email = reader["Email"],
                                Horario = reader["Horario"]
                            });
                        }
                    }
                }
            }

            return new JsonResult(result);
        }
        // POST api/Medicos
        [HttpPost]  
        public JsonResult Post(Medicos medico)
        {
            string query = "INSERT INTO Medicos (Nombre, Apellido, Especialidad, Telefono, Email, Horario) VALUES (@Nombre, @Apellido, @Especialidad, @Telefono, @Email, @Horario)";
            string sqlDataSource = _configuration.GetConnectionString("HospitalDB") ?? throw new InvalidOperationException("Connection string 'HospitalDB' is not configured.");

            using (SqlConnection myCon = new SqlConnection(sqlDataSource))
            {
                myCon.Open();
                using (SqlCommand myCommand = new SqlCommand(query, myCon))
                {
                    myCommand.Parameters.AddWithValue("@Nombre", medico.Nombre);
                    myCommand.Parameters.AddWithValue("@Apellido", medico.Apellido);
                    myCommand.Parameters.AddWithValue("@Especialidad", medico.Especialidad);
                    myCommand.Parameters.AddWithValue("@Telefono", medico.Telefono);
                    myCommand.Parameters.AddWithValue("@Email", medico.Email);
                    myCommand.Parameters.AddWithValue("@Horario", medico.Horario);

                    myCommand.ExecuteNonQuery();
                }
            }

            return new JsonResult("Medico agregado correctamente.");
        }
        // PUT api/Medicos/5        
        [HttpPut("{id}")]
        public JsonResult Put(int id, Medicos medico)
        {
            string query = "UPDATE Medicos SET Nombre = @Nombre, Apellido = @Apellido, Especialidad = @Especialidad, Telefono = @Telefono, Email = @Email, Horario = @Horario WHERE MedicoID = @MedicoID";
            string sqlDataSource = _configuration.GetConnectionString("HospitalDB") ?? throw new InvalidOperationException("Connection string 'HospitalDB' is not configured.");

            using (SqlConnection myCon = new SqlConnection(sqlDataSource))
            {
                myCon.Open();
                using (SqlCommand myCommand = new SqlCommand(query, myCon))
                {
                    myCommand.Parameters.AddWithValue("@MedicoID", id);
                    myCommand.Parameters.AddWithValue("@Nombre", medico.Nombre);
                    myCommand.Parameters.AddWithValue("@Apellido", medico.Apellido);
                    myCommand.Parameters.AddWithValue("@Especialidad", medico.Especialidad);
                    myCommand.Parameters.AddWithValue("@Telefono", medico.Telefono);
                    myCommand.Parameters.AddWithValue("@Email", medico.Email);
                    myCommand.Parameters.AddWithValue("@Horario", medico.Horario);

                    myCommand.ExecuteNonQuery();
                }
            }

            return new JsonResult("Medico actualizado correctamente.");
        }
        // DELETE api/Medicos/5
        [HttpDelete("{id}")]    
        public JsonResult Delete(int id)
        {
            string query = "DELETE FROM Medicos WHERE MedicoID = @MedicoID";
            string sqlDataSource = _configuration.GetConnectionString("HospitalDB") ?? throw new InvalidOperationException("Connection string 'HospitalDB' is not configured.");

            using (SqlConnection myCon = new SqlConnection(sqlDataSource))
            {
                myCon.Open();
                using (SqlCommand myCommand = new SqlCommand(query, myCon))
                {
                    myCommand.Parameters.AddWithValue("@MedicoID", id);
                    myCommand.ExecuteNonQuery();
                }
            }

            return new JsonResult("Medico eliminado correctamente.");
        }
    }   
}