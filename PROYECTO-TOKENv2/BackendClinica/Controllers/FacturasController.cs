using BackendClinica.Data;
using BackendClinica.Models;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace BackendClinica.Controllers
{
    //[Authorize]
    [ApiController]
    [Route("api/[controller]")]
    public class FacturasController : ControllerBase
    {
        private readonly ClinicaContext _context;
        public FacturasController(ClinicaContext context) { _context = context; }

        [HttpGet]
        public async Task<ActionResult<IEnumerable<object>>> GetFacturas()
        {
            var facturas = await _context.Facturas
                .Include(f => f.Paciente)
                .Select(f => new {
                    f.IdFactura,
                    f.IdPaciente,
                    PacienteNombre = f.Paciente != null ? f.Paciente.Nombre + " " + f.Paciente.Apellido : "",
                    f.FechaEmision,
                    f.Total
                })
                .ToListAsync();

            return Ok(facturas);
        }

        [HttpGet("{id}")]
        public async Task<ActionResult<Factura>> GetFactura(int id)
        {
            var f = await _context.Set<Factura>().FindAsync(id);
            if (f == null) return NotFound();
            return Ok(f);
        }

        [HttpPost]
        public async Task<ActionResult<Factura>> CreateFactura([FromBody] Factura nueva)
        {
            _context.Set<Factura>().Add(nueva);
            await _context.SaveChangesAsync();
            return CreatedAtAction(nameof(GetFactura), new { id = nueva.IdFactura }, nueva);
        }

        [HttpPut("{id}")]
        public async Task<ActionResult> UpdateFactura(int id, [FromBody] Factura actualizada)
        {
            var f = await _context.Set<Factura>().FindAsync(id);
            if (f == null) return NotFound();
            f.IdPaciente = actualizada.IdPaciente;
            f.FechaEmision = actualizada.FechaEmision;
            f.Total = actualizada.Total;
            await _context.SaveChangesAsync();
            return Ok(f);
        }

        [HttpDelete("{id}")]
        public async Task<ActionResult> DeleteFactura(int id)
        {
            var f = await _context.Set<Factura>().FindAsync(id);
            if (f == null) return NotFound();
            _context.Set<Factura>().Remove(f);
            await _context.SaveChangesAsync();
            return Ok();
        }
    }
}
