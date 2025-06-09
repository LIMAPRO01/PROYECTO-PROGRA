using BackendClinica.Data;
using BackendClinica.Models;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace BackendClinica.Controllers
{
    [Authorize]
    [ApiController]
    [Route("api/[controller]")]
    public class PacientesController : ControllerBase
    {
        private readonly ClinicaContext _context;

        public PacientesController(ClinicaContext context)
        {
            _context = context;
        }

        [HttpGet]
        public async Task<ActionResult<IEnumerable<Paciente>>> GetPacientes()
        {
            var pacientes = await _context.Pacientes.ToListAsync();
            return Ok(pacientes); // Siempre retorna array, aunque esté vacío
        }

        [HttpGet("{id}")]
        public async Task<ActionResult<Paciente>> GetPaciente(int id)
        {
            var paciente = await _context.Pacientes.FindAsync(id);
            if (paciente == null)
               
                return NotFound($"Paciente con ID {id} no encontrado.");
            return Ok(paciente);
        }
        [HttpGet("telefono/{telefono}")]
        public async Task<ActionResult<List<Paciente>>> GetPacientesPorTelefono(string telefono)
        {
            var pacientes = await _context.Pacientes
                                          .Where(p => p.Telefono == telefono)
                                          .ToListAsync();

            if (pacientes == null || pacientes.Count == 0)
                return NotFound($"No se encontraron pacientes con teléfono {telefono}.");

            return Ok(pacientes);
        }
        [HttpGet("buscar")]
        public async Task<ActionResult<List<Paciente>>> BuscarPaciente([FromQuery] string dato)
        {
            if (string.IsNullOrWhiteSpace(dato))
                return BadRequest("Debe proporcionar un valor de búsqueda.");

            // Convertir a minúsculas y eliminar espacios en blanco
            string datoLower = dato.ToLower().Trim();

            // Buscar pacientes por nombre, apellido, teléfono o combinación de nombre y apellido
            var pacientes = await _context.Pacientes
                .Where(p =>
                  
                    (p.Telefono != null && p.Telefono.Contains(datoLower)) ||
                    (
                        p.Nombre != null && p.Apellido != null &&
                        (p.Nombre + " " + p.Apellido).ToLower().Contains(datoLower)
                    )
                )
                .ToListAsync();

            // Si no se encontraron pacientes, retornar un mensaje adecuado
            if (pacientes == null || pacientes.Count == 0)
                return NotFound($"No se encontraron pacientes con el valor: {dato}");

            return Ok(pacientes);
        }



        [HttpPost]
        public async Task<ActionResult<Paciente>> CreatePaciente([FromBody] Paciente nuevoPaciente)
        {
            if (!ModelState.IsValid)
                return BadRequest(ModelState);
            try
            {
                nuevoPaciente.FechaRegistro = DateTime.Now;
                nuevoPaciente.FechaActualizacion = DateTime.Now; // Asignar fecha de actualización actual


                _context.Pacientes.Add(nuevoPaciente);
                await _context.SaveChangesAsync();
                return CreatedAtAction(nameof(GetPaciente), new { id = nuevoPaciente.IdPaciente }, nuevoPaciente);
            }
            catch (Exception ex)
            {
                return StatusCode(500, $"Error al crear paciente: {ex.Message}");
            }
        }

        [HttpPut("{id}")]
        public async Task<ActionResult> UpdatePaciente(int id, [FromBody] Paciente pacienteActualizado)
        {
            if (!ModelState.IsValid)
                return BadRequest(ModelState);
            var paciente = await _context.Pacientes.FindAsync(id);
            if (paciente == null)
                return NotFound($"Paciente con ID {id} no encontrado para actualizar.");
            paciente.Nombre = pacienteActualizado.Nombre;
            paciente.Apellido = pacienteActualizado.Apellido;
            paciente.FechaNacimiento = pacienteActualizado.FechaNacimiento;
            paciente.Genero = pacienteActualizado.Genero;
            paciente.Direccion = pacienteActualizado.Direccion;
            paciente.Telefono = pacienteActualizado.Telefono;
            paciente.Email = pacienteActualizado.Email;
            // No sobrescribas FechaRegistro
            paciente.FechaActualizacion = DateTime.Now;
            try
            {
                await _context.SaveChangesAsync();
                return Ok(paciente);
            }
            catch (Exception ex)
            {
                return StatusCode(500, $"Error al actualizar paciente: {ex.Message}");
            }
        }

        [HttpDelete("{id}")]
        public async Task<ActionResult> DeletePaciente(int id)
        {
            var paciente = await _context.Pacientes.FindAsync(id);
            if (paciente == null)
                return NotFound($"Paciente con ID {id} no encontrado para eliminar.");
            try
            {
                _context.Pacientes.Remove(paciente);
                await _context.SaveChangesAsync();
                return Ok($"Paciente con ID {id} eliminado exitosamente.");
            }
            catch (Exception ex)
            {
                return StatusCode(500, $"Error al eliminar paciente: {ex.Message}");
            }
        }
    }
}