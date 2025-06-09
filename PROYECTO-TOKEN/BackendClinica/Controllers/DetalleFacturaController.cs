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
    public class DetalleFacturaController : ControllerBase
    {
        private readonly ClinicaContext _context;
        public DetalleFacturaController(ClinicaContext context) { _context = context; }

        [HttpGet]
        public async Task<ActionResult<IEnumerable<DetalleFactura>>> GetDetalles()
            => Ok(await _context.Set<DetalleFactura>().ToListAsync());

        [HttpGet("{id}")]
        public async Task<ActionResult<DetalleFactura>> GetDetalle(int id)
        {
            var d = await _context.Set<DetalleFactura>().FindAsync(id);
            if (d == null) return NotFound();
            return Ok(d);
        }

        [HttpPost]
        public async Task<ActionResult<DetalleFactura>> CreateDetalle([FromBody] DetalleFactura nuevo)
        {
            _context.Set<DetalleFactura>().Add(nuevo);
            await _context.SaveChangesAsync();
            return CreatedAtAction(nameof(GetDetalle), new { id = nuevo.IdDetalle }, nuevo);
        }

        [HttpPut("{id}")]
        public async Task<ActionResult> UpdateDetalle(int id, [FromBody] DetalleFactura actualizado)
        {
            var d = await _context.Set<DetalleFactura>().FindAsync(id);
            if (d == null) return NotFound();
            d.IdFactura = actualizado.IdFactura;
            d.IdCita = actualizado.IdCita;
            d.IdMedicamento = actualizado.IdMedicamento;
            d.Cantidad = actualizado.Cantidad;
            d.Subtotal = actualizado.Subtotal;
            await _context.SaveChangesAsync();
            return Ok(d);
        }

        [HttpDelete("{id}")]
        public async Task<ActionResult> DeleteDetalle(int id)
        {
            var d = await _context.Set<DetalleFactura>().FindAsync(id);
            if (d == null) return NotFound();
            _context.Set<DetalleFactura>().Remove(d);
            await _context.SaveChangesAsync();
            return Ok();
        }
    }
}