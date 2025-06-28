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
    public class DetalleRecetaController : ControllerBase
    {
        private readonly ClinicaContext _context;
        public DetalleRecetaController(ClinicaContext context) { _context = context; }

        [HttpGet]
        public async Task<ActionResult<IEnumerable<object>>> GetDetalles()
        {
            var detalles = await _context.DetalleRecetas
                .Include(d => d.Medicamento)
                .Select(d => new {
                    d.IdDetalle,
                    d.IdReceta,
                    d.IdMedicamento,
                    MedicamentoNombre = d.Medicamento != null ? d.Medicamento.Nombre : "",
                    d.Cantidad,
                    d.Dosis
                })
                .ToListAsync();

            return Ok(detalles);
        }

        [HttpGet("{id}")]
        public async Task<ActionResult<DetalleReceta>> GetDetalle(int id)
        {
            var d = await _context.Set<DetalleReceta>().FindAsync(id);
            if (d == null) return NotFound();
            return Ok(d);
        }

        [HttpPost]
        public async Task<ActionResult<DetalleReceta>> CreateDetalle([FromBody] DetalleReceta nuevo)
        {
            _context.Set<DetalleReceta>().Add(nuevo);
            await _context.SaveChangesAsync();
            return CreatedAtAction(nameof(GetDetalle), new { id = nuevo.IdDetalle }, nuevo);
        }

        [HttpPut("{id}")]
        public async Task<ActionResult> UpdateDetalle(int id, [FromBody] DetalleReceta actualizado)
        {
            var d = await _context.Set<DetalleReceta>().FindAsync(id);
            if (d == null) return NotFound();
            d.IdReceta = actualizado.IdReceta;
            d.IdMedicamento = actualizado.IdMedicamento;
            d.Cantidad = actualizado.Cantidad;
            d.Dosis = actualizado.Dosis;
            await _context.SaveChangesAsync();
            return Ok(d);
        }

        [HttpDelete("{id}")]
        public async Task<ActionResult> DeleteDetalle(int id)
        {
            var d = await _context.Set<DetalleReceta>().FindAsync(id);
            if (d == null) return NotFound();
            _context.Set<DetalleReceta>().Remove(d);
            await _context.SaveChangesAsync();
            return Ok();
        }
    }
}
