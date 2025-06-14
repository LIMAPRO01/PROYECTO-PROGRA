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
    public class MovimientoStockController : ControllerBase
    {
        private readonly ClinicaContext _context;
        public MovimientoStockController(ClinicaContext context) { _context = context; }

        [HttpGet]
        public async Task<ActionResult<IEnumerable<MovimientoStock>>> GetMovimientos()
            => Ok(await _context.Set<MovimientoStock>().ToListAsync());

        [HttpGet("{id}")]
        public async Task<ActionResult<MovimientoStock>> GetMovimiento(int id)
        {
            var m = await _context.Set<MovimientoStock>().FindAsync(id);
            if (m == null) return NotFound();
            return Ok(m);
        }

        [HttpPost]
        public async Task<ActionResult<MovimientoStock>> CreateMovimiento([FromBody] MovimientoStock nuevo)
        {
            var medicamento = await _context.Medicamentos.FindAsync(nuevo.IdMedicamento);
            if (medicamento == null)
                return BadRequest("Medicamento no encontrado.");

            if (nuevo.TipoMovimiento == "Entrada")
            {
                medicamento.Stock += nuevo.Cantidad;
            }
            else if (nuevo.TipoMovimiento == "Salida")
            {
                if (medicamento.Stock < nuevo.Cantidad)
                    return BadRequest("Stock insuficiente.");
                medicamento.Stock -= nuevo.Cantidad;
            }
            else
            {
                return BadRequest("TipoMovimiento debe ser 'Entrada' o 'Salida'.");
            }

            _context.MovimientoStocks.Add(nuevo);

            // Guarda los cambios en ambos: medicamento y movimiento
            await _context.SaveChangesAsync();

            return CreatedAtAction(nameof(GetMovimiento), new { id = nuevo.IdMovimiento }, nuevo);
        }

        [HttpPut("{id}")]
        public async Task<ActionResult> UpdateMovimiento(int id, [FromBody] MovimientoStock actualizado)
        {
            var m = await _context.Set<MovimientoStock>().FindAsync(id);
            if (m == null) return NotFound();
            m.IdMedicamento = actualizado.IdMedicamento;
            m.TipoMovimiento = actualizado.TipoMovimiento;
            m.Cantidad = actualizado.Cantidad;
            m.FechaMovimiento = actualizado.FechaMovimiento;
            m.Descripcion = actualizado.Descripcion;
            await _context.SaveChangesAsync();
            return Ok(m);
        }

        [HttpDelete("{id}")]
        public async Task<ActionResult> DeleteMovimiento(int id)
        {
            var m = await _context.Set<MovimientoStock>().FindAsync(id);
            if (m == null) return NotFound();
            _context.Set<MovimientoStock>().Remove(m);
            await _context.SaveChangesAsync();
            return Ok();
        }
    }
}
