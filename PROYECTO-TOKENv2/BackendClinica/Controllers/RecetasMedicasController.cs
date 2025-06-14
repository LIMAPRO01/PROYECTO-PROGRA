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
    public class RecetasMedicasController : ControllerBase
    {
        private readonly ClinicaContext _context;
        public RecetasMedicasController(ClinicaContext context) { _context = context; }

        [HttpGet]
        public async Task<ActionResult<IEnumerable<object>>> GetRecetasMedicas()
        {
            var recetas = await _context.RecetasMedicas
                .Include(r => r.Paciente)
                .Include(r => r.Medico)
                .Select(r => new {
                    r.IdReceta,
                    r.IdPaciente,
                    PacienteNombre = r.Paciente != null ? r.Paciente.Nombre + " " + r.Paciente.Apellido : "",
                    r.IdMedico,
                    MedicoNombre = r.Medico != null ? r.Medico.Nombre + " " + r.Medico.Apellido : "",
                    r.Indicaciones,
                    r.FechaEmision
                })
                .ToListAsync();

            return Ok(recetas);
        }

        [HttpGet("{id}")]
        public async Task<ActionResult<RecetaMedica>> GetReceta(int id)
        {
            var r = await _context.Set<RecetaMedica>().FindAsync(id);
            if (r == null) return NotFound();
            return Ok(r);
        }

        [HttpPost]
        public async Task<ActionResult<RecetaMedica>> CreateReceta([FromBody] RecetaMedica nueva)
        {
            _context.Set<RecetaMedica>().Add(nueva);
            await _context.SaveChangesAsync();
            return CreatedAtAction(nameof(GetReceta), new { id = nueva.IdReceta }, nueva);
        }

        [HttpPut("{id}")]
        public async Task<ActionResult> UpdateReceta(int id, [FromBody] RecetaMedica actualizada)
        {
            var r = await _context.Set<RecetaMedica>().FindAsync(id);
            if (r == null) return NotFound();
            r.IdPaciente = actualizada.IdPaciente;
            r.IdMedico = actualizada.IdMedico;
            r.Indicaciones = actualizada.Indicaciones;
            r.FechaEmision = actualizada.FechaEmision;
            await _context.SaveChangesAsync();
            return Ok(r);
        }

        [HttpDelete("{id}")]
        public async Task<ActionResult> DeleteReceta(int id)
        {
            var r = await _context.Set<RecetaMedica>().FindAsync(id);
            if (r == null) return NotFound();
            _context.Set<RecetaMedica>().Remove(r);
            await _context.SaveChangesAsync();
            return Ok();
        }
    }
}
