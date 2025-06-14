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
    public class MedicamentosController : ControllerBase
    {
        private readonly ClinicaContext _context;
        public MedicamentosController(ClinicaContext context) { _context = context; }

        [HttpGet]
        public async Task<ActionResult<IEnumerable<Medicamento>>> GetMedicamentos()
            => Ok(await _context.Set<Medicamento>().ToListAsync());

        [HttpGet("{id}")]
        public async Task<ActionResult<Medicamento>> GetMedicamento(int id)
        {
            var m = await _context.Set<Medicamento>().FindAsync(id);
            if (m == null) return NotFound();
            return Ok(m);
        }

        [HttpPost]
        public async Task<ActionResult<Medicamento>> CreateMedicamento([FromBody] Medicamento nuevo)
        {
            _context.Set<Medicamento>().Add(nuevo);
            await _context.SaveChangesAsync();
            return CreatedAtAction(nameof(GetMedicamento), new { id = nuevo.IdMedicamento }, nuevo);
        }

        [HttpPut("{id}")]
        public async Task<ActionResult> UpdateMedicamento(int id, [FromBody] Medicamento actualizado)
        {
            var m = await _context.Set<Medicamento>().FindAsync(id);
            if (m == null) return NotFound();
            m.Nombre = actualizado.Nombre;
            m.Descripcion = actualizado.Descripcion;
            m.Precio = actualizado.Precio;
            m.Stock = actualizado.Stock;
            await _context.SaveChangesAsync();
            return Ok(m);
        }

        [HttpDelete("{id}")]
        public async Task<ActionResult> DeleteMedicamento(int id)
        {
            var m = await _context.Set<Medicamento>().FindAsync(id);
            if (m == null) return NotFound();
            _context.Set<Medicamento>().Remove(m);
            await _context.SaveChangesAsync();
            return Ok();
        }
    }
}
