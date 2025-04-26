using HospitalAPI.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Data.SqlClient;
using System.Data;

namespace HospitalAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class PacientesController : ControllerBase
    {
        private readonly IConfiguration _configuration;

        public PacientesController(IConfiguration configuration)
        {
            _configuration = configuration;
        }

        // GET: api/Pacientes
        [HttpGet]
public JsonResult Get()
{
    string query = "SELECT * FROM Pacientes";
    List<object> result = new List<object>();
    string sqlDataSource = _configuration.GetConnectionString("HospitalDB") ?? throw new InvalidOperationException("Connection string 'HospitalDB' is not configured.");

    using(SqlConnection myCon = new SqlConnection(sqlDataSource))
    {
        myCon.Open();
        using(SqlCommand myCommand = new SqlCommand(query, myCon))
        {
            using(SqlDataReader reader = myCommand.ExecuteReader())
            {
                while (reader.Read())
                {
                    result.Add(new
                    {
                        PacienteID = reader["PacienteID"],
                        Nombre = reader["Nombre"],
                        Apellido = reader["Apellido"],
                        FechaNacimiento = reader["FechaNacimiento"],
                        Genero = reader["Genero"],
                        Direccion = reader["Direccion"],
                        Telefono = reader["Telefono"],
                        Email = reader["Email"],
                        FechaRegistro = reader["FechaRegistro"]
                    });
                }
            }
        }
    }

    return new JsonResult(result);
}

//POST api/Pacientes
        [HttpPost]  

        public JsonResult Post(Pacientes paciente)
        {
            string query = @"
                INSERT INTO Pacientes (Nombre, Apellido, FechaNacimiento, Genero, Direccion, Telefono, Email) 
                VALUES (@Nombre, @Apellido, @FechaNacimiento, @Genero, @Direccion, @Telefono, @Email)";
            
            DataTable table = new DataTable();
            string sqlDataSource = _configuration.GetConnectionString("HospitalDB") ?? throw new InvalidOperationException("Connection string 'HospitalDB' is not configured.");
            SqlDataReader myReader;
            
            using(SqlConnection myCon = new SqlConnection(sqlDataSource))
            {
                myCon.Open();
                using(SqlCommand myCommand = new SqlCommand(query, myCon))
                {
                    myCommand.Parameters.AddWithValue("@Nombre", paciente.Nombre);
                    myCommand.Parameters.AddWithValue("@Apellido", paciente.Apellido);
                    myCommand.Parameters.AddWithValue("@FechaNacimiento", paciente.FechaNacimiento);
                    myCommand.Parameters.AddWithValue("@Genero", paciente.Genero);
                    myCommand.Parameters.AddWithValue("@Direccion", paciente.Direccion ?? "");
                    myCommand.Parameters.AddWithValue("@Telefono", paciente.Telefono ?? "");
                    myCommand.Parameters.AddWithValue("@Email", paciente.Email ?? "");
                    
                    myReader = myCommand.ExecuteReader();
                    table.Load(myReader);
                    myReader.Close();
                    myCon.Close();
                }
            }

            return new JsonResult("Paciente agregado correctamente");
        }

        // PUT api/Pacientes/5
        [HttpPut("{id}")]
        public JsonResult Put(int id, Pacientes paciente)
        {
            string query = @"
                UPDATE Pacientes SET 
                Nombre = @Nombre,
                Apellido = @Apellido,
                FechaNacimiento = @FechaNacimiento,
                Genero = @Genero,
                Direccion = @Direccion,
                Telefono = @Telefono,
                Email = @Email
                WHERE PacienteID = @PacienteID";
            
            DataTable table = new DataTable();
            string sqlDataSource = _configuration.GetConnectionString("HospitalDB") ?? throw new InvalidOperationException("Connection string 'HospitalDB' is not configured.");
            SqlDataReader myReader;
            
            using(SqlConnection myCon = new SqlConnection(sqlDataSource))
            {
                myCon.Open();
                using(SqlCommand myCommand = new SqlCommand(query, myCon))
                {
                    myCommand.Parameters.AddWithValue("@PacienteID", id);
                    myCommand.Parameters.AddWithValue("@Nombre", paciente.Nombre);
                    myCommand.Parameters.AddWithValue("@Apellido", paciente.Apellido);
                    myCommand.Parameters.AddWithValue("@FechaNacimiento", paciente.FechaNacimiento);
                    myCommand.Parameters.AddWithValue("@Genero", paciente.Genero);
                    myCommand.Parameters.AddWithValue("@Direccion", paciente.Direccion ?? "");
                    myCommand.Parameters.AddWithValue("@Telefono", paciente.Telefono ?? "");
                    myCommand.Parameters.AddWithValue("@Email", paciente.Email ?? "");
                    
                    myReader = myCommand.ExecuteReader();
                    table.Load(myReader);
                    myReader.Close();
                    myCon.Close();
                }
            }

            return new JsonResult("Paciente actualizado correctamente");
        }

        // DELETE api/Pacientes/5
        [HttpDelete("{id}")]
        public JsonResult Delete(int id)
        {
            string query = @"
                DELETE FROM Pacientes
                WHERE PacienteID = @PacienteID";
            
            DataTable table = new DataTable();
            string sqlDataSource = _configuration.GetConnectionString("HospitalDB") ?? throw new InvalidOperationException("Connection string 'HospitalDB' is not configured.");
            SqlDataReader myReader;
            
            using(SqlConnection myCon = new SqlConnection(sqlDataSource))
            {
                myCon.Open();
                using(SqlCommand myCommand = new SqlCommand(query, myCon))
                {
                    myCommand.Parameters.AddWithValue("@PacienteID", id);
                    
                    myReader = myCommand.ExecuteReader();
                    table.Load(myReader);
                    myReader.Close();
                    myCon.Close();
                }
            }

            return new JsonResult("Paciente eliminado correctamente");
        }
    }
}