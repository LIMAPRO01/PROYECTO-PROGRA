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
    public class CitasController : ControllerBase
    {
        private readonly ClinicaContext _context;
        public CitasController(ClinicaContext context)
        {
            _context = context;
        }

        [HttpGet]
        public async Task<ActionResult<IEnumerable<object>>> GetCitas()
        {
            var citas = await _context.Citas
                .Include(c => c.Paciente)
                .Include(c => c.Medico)
                .Select(c => new {
                    c.IdCita,
                    PacienteId = c.IdPaciente,
                    PacienteNombre = c.Paciente != null ? c.Paciente.Nombre + " " + c.Paciente.Apellido : "",
                    MedicoId = c.idmedico,
                    MedicoNombre = c.Medico != null ? c.Medico.Nombre + " " + c.Medico.Apellido : "",
                    c.FechaCita,
                    c.motivo,
                    c.Estado,
                    c.FechaRegistro
                })
                .ToListAsync();

            return Ok(citas);
        }

        [HttpGet("{id}")]
        public async Task<ActionResult<Cita>> GetCita(int id)
        {
            var cita = await _context.Citas.FindAsync(id);
            if (cita == null)
                return NotFound($"Cita con ID {id} no encontrada.");
            return Ok(cita);
        }

        [HttpPost]
        public async Task<ActionResult<Cita>> CreateCita(Cita nuevaCita)
        {
            _context.Citas.Add(nuevaCita);
            await _context.SaveChangesAsync();

            // Vuelve a consultar la cita con los datos relacionados
            var citaConNombres = await _context.Citas
                .Include(c => c.Paciente)
                .Include(c => c.Medico)
                .Where(c => c.IdCita == nuevaCita.IdCita)
                .Select(c => new {
                    c.IdCita,
                    c.IdPaciente,
                    PacienteNombre = c.Paciente != null ? c.Paciente.Nombre + " " + c.Paciente.Apellido : "",
                    c.idmedico,
                    MedicoNombre = c.Medico != null ? c.Medico.Nombre + " " + c.Medico.Apellido : "",
                    c.FechaCita,
                    c.motivo,
                    c.Estado,
                    c.FechaRegistro
                })
                .FirstOrDefaultAsync();

            return Ok(citaConNombres);
        }

        [HttpPut("{id}")]
        public async Task<ActionResult> UpdateCita(int id, [FromBody] Cita citaActualizada)
        {
            var cita = await _context.Citas.FindAsync(id);
            if (cita == null)
                return NotFound($"Cita con ID {id} no encontrada para actualizar.");
            cita.IdPaciente = citaActualizada.IdPaciente;
            cita.idmedico = citaActualizada.idmedico;
            cita.FechaCita = citaActualizada.FechaCita;
            cita.motivo = citaActualizada.motivo;
            cita.Estado = citaActualizada.Estado;
            cita.FechaRegistro =  DateTime.Now;
            await _context.SaveChangesAsync();
            return Ok(cita);
            
        }

        [HttpDelete("{id}")]
        public async Task<ActionResult> DeleteCita(int id)
        {
            var cita = await _context.Citas.FindAsync(id);
            if (cita == null)
                return NotFound($"Cita con ID {id} no encontrada para eliminar.");
            _context.Citas.Remove(cita);
            await _context.SaveChangesAsync();
            return Ok($"Cita con ID {id} eliminada exitosamente.");
        }
    }
}
