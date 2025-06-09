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
    public class UsuariosController : ControllerBase
    {
        private readonly ClinicaContext _context;
        public UsuariosController(ClinicaContext context) { _context = context; }

        [HttpGet]
        public async Task<ActionResult<IEnumerable<Usuario>>> GetUsuarios()
            => Ok(await _context.Usuarios.ToListAsync());

        [HttpGet("{id}")]
        public async Task<ActionResult<Usuario>> GetUsuario(int id)
        {
            var u = await _context.Usuarios.FindAsync(id);
            if (u == null) return NotFound();
            return Ok(u);
        }

        [HttpPost]
        public async Task<ActionResult<Usuario>> CreateUsuario([FromBody] Usuario nuevo)
        {

            _context.Usuarios.Add(nuevo);
            await _context.SaveChangesAsync();
            return CreatedAtAction(nameof(GetUsuario), new { id = nuevo.idusuario }, nuevo);
        }
        //para login
        [HttpPost("login")]
        [AllowAnonymous]
        public IActionResult Login([FromBody] Login login)
        {
            var usuario = _context.Usuarios
                .FirstOrDefault(u => u.usuario == login.NombreUsuario && u.contraseña == login.Contraseña && u.activo);

            if (usuario == null)
            {
                return Unauthorized( new { mensaje = "Credenciales incorrectas o usuario inactivo." });
            }

            return Ok(usuario); // devuelve todo el objeto Usuario
        }

        [HttpPut("{id}")]
        public async Task<ActionResult> UpdateUsuario(int id, [FromBody] Usuario actualizado)
        {
            var u = await _context.Usuarios.FindAsync(id);
            if (u == null) return NotFound();
            u.usuario = actualizado.usuario;
            u.contraseña = actualizado.contraseña;
            u.rol = actualizado.rol;
            u.idmedico = actualizado.idmedico;
            u.fechaRegistro = actualizado.fechaRegistro;
            u.activo = actualizado.activo;
            await _context.SaveChangesAsync();
            return Ok(u);
        }

        [HttpDelete("{id}")]
        public async Task<ActionResult> DeleteUsuario(int id)
        {
            var u = await _context.Usuarios.FindAsync(id);
            if (u == null) return NotFound();
            _context.Usuarios.Remove(u);
            await _context.SaveChangesAsync();
            return Ok();
        }
    }
}
