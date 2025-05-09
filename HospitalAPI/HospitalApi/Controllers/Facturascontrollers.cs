using HospitalAPI.Controllers;
using HospitalAPI.Models;
using Microsoft.AspNetCore.Mvc; 
using Microsoft.Data.SqlClient;
using System.Data;

namespace HospitalAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class FacturasController : ControllerBase
    {
        private readonly IConfiguration _configuration;

        public FacturasController(IConfiguration configuration)
        {
            _configuration = configuration;
        }

        // GET: api/Facturas
        [HttpGet]
        public JsonResult Get()
        {
            string query = @"
                SELECT 
                    Facturas.FacturaID, 
                    Facturas.PacienteID, 
                    Pacientes.Nombre + ' ' + Pacientes.Apellido AS PacienteNombreCompleto, 
                    Facturas.FechaEmision, 
                    Facturas.Monto, 
                    Facturas.Estado
                FROM Facturas
                INNER JOIN Pacientes ON Facturas.PacienteID = Pacientes.PacienteID";

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
                                FacturaID = reader["FacturaID"],
                                PacienteID = reader["PacienteID"],
                                PacienteNombreCompleto = reader["PacienteNombreCompleto"],
                                FechaEmision = reader["FechaEmision"],
                                Monto = reader["Monto"],
                                Estado = reader["Estado"],
                            });
                        }
                    }
                }
            }

            return new JsonResult(result);
        } // Cierre del método Get


       //Post: api/Facturas
        [HttpPost]
        public JsonResult Post(Facturas factura)
        {
            string query = @"
                INSERT INTO Facturas (CitaID,PacienteID, FechaEmision, Monto, Estado) 
                VALUES (@CitaID,@PacienteID, @FechaEmision, @Monto, @Estado)";

            string sqlDataSource = _configuration.GetConnectionString("HospitalDB") ?? throw new InvalidOperationException("Connection string 'HospitalDB' is not configured.");

            using (SqlConnection myCon = new SqlConnection(sqlDataSource))
            {
                myCon.Open();
                using (SqlCommand myCommand = new SqlCommand(query, myCon))
                {
                    myCommand.Parameters.AddWithValue("@CitaID", factura.CitaID);
                    myCommand.Parameters.AddWithValue("@PacienteID", factura.PacienteID);
                    myCommand.Parameters.AddWithValue("@FechaEmision", factura.FechaEmision);
                    myCommand.Parameters.AddWithValue("@Monto", factura.Monto);
                    myCommand.Parameters.AddWithValue("@Estado", factura.Estado);
                    myCommand.ExecuteNonQuery();
                }
            }

            return new JsonResult("Factura agregada correctamente.");
        } // Cierre del método Post


        // PUT: api/Facturas/{id}
        [HttpPut("{id}")]    
        public JsonResult Put(int id, Facturas factura)
        {
            string query = @"
                UPDATE Facturas 
                SET CitaID = @CitaID, PacienteID = @PacienteID, FechaEmision = @FechaEmision, Monto = @Monto, Estado = @Estado 
                WHERE FacturaID = @FacturaID";

            string sqlDataSource = _configuration.GetConnectionString("HospitalDB") ?? throw new InvalidOperationException("Connection string 'HospitalDB' is not configured.");

            using (SqlConnection myCon = new SqlConnection(sqlDataSource))
            {
                myCon.Open();
                using (SqlCommand myCommand = new SqlCommand(query, myCon))
                {
                    myCommand.Parameters.AddWithValue("@CitaID", factura.CitaID);
                    myCommand.Parameters.AddWithValue("@PacienteID", factura.PacienteID);
                    myCommand.Parameters.AddWithValue("@FechaEmision", factura.FechaEmision);
                    myCommand.Parameters.AddWithValue("@Monto", factura.Monto);
                    myCommand.Parameters.AddWithValue("@Estado", factura.Estado);
                    myCommand.Parameters.AddWithValue("@FacturaID", id);
                    myCommand.ExecuteNonQuery();
                }
            }

            return new JsonResult("Factura actualizada correctamente.");
        } // Cierre del método Put


        // DELETE: api/Facturas/{id}
        [HttpDelete("{id}")]    


        public JsonResult Delete(int id)
        {
            string query = @"
                DELETE FROM Facturas 
                WHERE FacturaID = @FacturaID";

            string sqlDataSource = _configuration.GetConnectionString("HospitalDB") ?? throw new InvalidOperationException("Connection string 'HospitalDB' is not configured.");

            using (SqlConnection myCon = new SqlConnection(sqlDataSource))
            {
                myCon.Open();
                using (SqlCommand myCommand = new SqlCommand(query, myCon))
                {
                    myCommand.Parameters.AddWithValue("@FacturaID", id);
                    myCommand.ExecuteNonQuery();
                }
            }

            return new JsonResult("Factura eliminada correctamente.");
        } // Cierre del método Delete


    } // Cierre de la clase FacturasController
} // Cierre del namespace HospitalAPI.Controllers 
