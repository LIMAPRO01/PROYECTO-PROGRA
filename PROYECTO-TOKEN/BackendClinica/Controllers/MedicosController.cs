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
    public class MedicosController : ControllerBase
    {
        private readonly ClinicaContext _context;

        public MedicosController(ClinicaContext context)
        {
            _context = context;
        }

        [HttpGet]
        public async Task<ActionResult<IEnumerable<Medico>>> GetMedicos()
        {
            var medicos = await _context.Medicos.ToListAsync();
            return Ok(medicos); // Siempre retorna array, aunque esté vacío
        }

        [HttpGet("{id}")]
        public async Task<ActionResult<Medico>> GetMedico(int id)
        {
            var medico = await _context.Medicos.FindAsync(id);
            if (medico == null)
                return NotFound($"Médico con ID {id} no encontrado.");
            return Ok(medico);
        }

        [HttpGet("telefono/{telefono}")]
        public async Task<ActionResult<List<Medico>>> GetMedicosPorTelefono(string telefono)
        {
            var Medicos = await _context.Medicos
                                          .Where(p => p.Telefono == telefono)
                                          .ToListAsync();

            if (Medicos == null || Medicos.Count == 0)
                return NotFound($"No se encontraron  con teléfono {telefono}.");

            return Ok(Medicos);
        }

        [HttpGet("buscar")]
        public async Task<ActionResult<List<Medico>>> BuscarMedico([FromQuery] string dato)
        {
            if (string.IsNullOrWhiteSpace(dato))
                return BadRequest("Debe proporcionar un valor de búsqueda.");

            // Convertir a minúsculas y eliminar espacios en blanco
            string datoLower = dato.ToLower().Trim();

            // Buscar pacientes por nombre, apellido, teléfono o combinación de nombre y apellido
            var Medicos = await _context.Medicos
                .Where(p =>

                    (p.Telefono != null && p.Telefono.Contains(datoLower)) ||
                    (
                        p.Nombre != null && p.Apellido != null &&
                        (p.Nombre + " " + p.Apellido).ToLower().Contains(datoLower)
                    )
                )
                .ToListAsync();

            // Si no se encontraron pacientes, retornar un mensaje adecuado
            if (Medicos == null || Medicos.Count == 0)
                return NotFound($"No se encontraron pacientes con el valor: {dato}");

            return Ok(Medicos);
        }

        [HttpPost]
        public async Task<ActionResult<Medico>> CreateMedico([FromBody] Medico nuevoMedico)
        {
            if (!ModelState.IsValid)
                return BadRequest(ModelState);
            try
            {
                nuevoMedico.FechaRegistro = DateTime.Now;
                nuevoMedico.FechaActualizacion = DateTime.Now;

                _context.Medicos.Add(nuevoMedico);
                await _context.SaveChangesAsync();
                return CreatedAtAction(nameof(GetMedico), new { id = nuevoMedico.idmedico }, nuevoMedico);
            }
            catch (Exception ex)
            {
                return StatusCode(500, $"Error al crear médico: {ex.Message}");
            }
        }
        



        [HttpPut("{id}")]
        public async Task<ActionResult> UpdateMedico(int id, [FromBody] Medico medicoActualizado)
        {
            if (!ModelState.IsValid)
                return BadRequest(ModelState);
            var medico = await _context.Medicos.FindAsync(id);
            if (medico == null)
                return NotFound($"Médico con ID {id} no encontrado para actualizar.");
            medico.Nombre = medicoActualizado.Nombre;
            medico.Apellido = medicoActualizado.Apellido;
            medico.Especialidad = medicoActualizado.Especialidad;
            medico.Telefono = medicoActualizado.Telefono;
            medico.Email = medicoActualizado.Email;
            // No sobrescribas FechaRegistro
            medico.FechaActualizacion = DateTime.Now;
           
            try
            {
                await _context.SaveChangesAsync();
                return Ok(medico);
            }
            catch (Exception ex)
            {
                return StatusCode(500, $"Error al actualizar médico: {ex.Message}");
            }
        }

        /// <summary>
        /// Elimina un médico por su ID.
        /// </summary>
        [HttpDelete("{id}")]
        public async Task<ActionResult> DeleteMedico(int id)
        {
            var medico = await _context.Medicos.FindAsync(id);
            if (medico == null)
                return NotFound($"Médico con ID {id} no encontrado para eliminar.");
            try
            {
                _context.Medicos.Remove(medico);
                await _context.SaveChangesAsync();
                return Ok($"Médico con ID {id} eliminado exitosamente.");
            }
            catch (Exception ex)
            {
                return StatusCode(500, $"Error al eliminar médico: {ex.Message}");

            }
        }
    }
}
        
    

       
    