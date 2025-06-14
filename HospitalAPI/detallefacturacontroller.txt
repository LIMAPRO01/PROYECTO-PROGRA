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
    public class DetalleFacturaController : ControllerBase
    {
        private readonly ClinicaContext _context;
        public DetalleFacturaController(ClinicaContext context) { _context = context; }

        [HttpGet]
        public async Task<ActionResult<IEnumerable<object>>> GetDetalles()
        {
            const decimal valorConsulta = 100.00m;
            var detalles = await _context.Set<DetalleFactura>()
                .Include(d => d.Medicamento)
                .Include(d => d.Cita)
                    .ThenInclude(c => c.Paciente)
                .Select(d => new {
                    d.IdDetalle,
                    d.IdFactura,
                    d.IdCita,
                    d.IdMedicamento,
                    MedicamentoNombre = d.Medicamento != null ? d.Medicamento.Nombre : null,
                    d.Cantidad,
                    PrecioUnitario = d.Medicamento != null ? d.Medicamento.Precio : 0,
                    Subtotal = ((d.Medicamento != null ? d.Cantidad * d.Medicamento.Precio : 0) + valorConsulta).ToString("F2"),
                    PacienteNombre = d.Cita != null && d.Cita.Paciente != null ? d.Cita.Paciente.Nombre : null,
                    MotivoCita = d.Cita != null ? d.Cita.motivo : null
                })
                .ToListAsync();
            return Ok(detalles);
        }

        [HttpGet("{id}")]
        public async Task<ActionResult<object>> GetDetalle(int id)
        {
            const decimal valorConsulta = 100.00m;
            var d = await _context.Set<DetalleFactura>()
                .Include(x => x.Medicamento)
                .Include(x => x.Cita)
                    .ThenInclude(c => c.Paciente)
                .Where(x => x.IdDetalle == id)
                .Select(x => new {
                    x.IdDetalle,
                    x.IdFactura,
                    x.IdCita,
                    x.IdMedicamento,
                    MedicamentoNombre = x.Medicamento != null ? x.Medicamento.Nombre : null,
                    x.Cantidad,
                    PrecioUnitario = x.Medicamento != null ? x.Medicamento.Precio : 0,
                    Subtotal = ((x.Medicamento != null ? x.Cantidad * x.Medicamento.Precio : 0) + valorConsulta).ToString("F2"),
                    PacienteNombre = x.Cita != null && x.Cita.Paciente != null ? x.Cita.Paciente.Nombre : null,
                    MotivoCita = x.Cita != null ? x.Cita.motivo : null
                })
                .FirstOrDefaultAsync();
            if (d == null) return NotFound();
            return Ok(d);
        }

        [HttpPost]
        public async Task<ActionResult<DetalleFactura>> CreateDetalle([FromBody] DetalleFactura nuevo)
        {
            var medicamento = await _context.Set<Medicamento>().FindAsync(nuevo.IdMedicamento);
            const decimal valorConsulta = 100.00m;
            decimal precioUnitario = medicamento != null ? medicamento.Precio : 0;
            nuevo.Subtotal = Math.Round((nuevo.Cantidad * precioUnitario) + valorConsulta, 2);
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
            var medicamento = await _context.Set<Medicamento>().FindAsync(actualizado.IdMedicamento);
            const decimal valorConsulta = 100.00m;
            decimal precioUnitario = medicamento != null ? medicamento.Precio : 0;
            d.Subtotal = Math.Round((d.Cantidad * precioUnitario) + valorConsulta, 2);
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