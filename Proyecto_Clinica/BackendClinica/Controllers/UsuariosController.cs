using BackendClinica.Data;
using BackendClinica.Models;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace BackendClinica.Controllers
{
    
    [ApiController]
    [Route("api/[controller]")]
    public class UsuariosController : ControllerBase
    {
        private readonly ClinicaContext _context;
        private readonly PasswordHasher<Usuario> _passwordHasher;

        public UsuariosController(ClinicaContext context)
        {
            _context = context; _passwordHasher = new PasswordHasher<Usuario>();
        }

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
            nuevo.contraseña = _passwordHasher.HashPassword(nuevo, nuevo.contraseña);

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
                .FirstOrDefault(u => u.usuario == login.NombreUsuario && u.activo);

            if (usuario == null)
            {
                return Unauthorized(new { mensaje = "Credenciales incorrectas o usuario inactivo." });
            }

            var result = _passwordHasher.VerifyHashedPassword(usuario, usuario.contraseña, login.Contraseña);
            if (result == PasswordVerificationResult.Failed)
            {
                return Unauthorized(new { mensaje = "Credenciales incorrectas o usuario inactivo." });
            }

            return Ok(usuario); // devuelve todo el objeto Usuario
        }

        [HttpPut("{id}")]
        public async Task<ActionResult> UpdateUsuario(int id, [FromBody] Usuario actualizado)
        {
            var u = await _context.Usuarios.FindAsync(id);
            if (u == null) return NotFound();
            u.usuario = actualizado.usuario;
            // Verificar si la contraseña ha cambiado
            if (!string.IsNullOrEmpty(actualizado.contraseña))
            {
                u.contraseña = _passwordHasher.HashPassword(u, actualizado.contraseña);
            }
            else
            {
                // Si no se proporciona una nueva contraseña, mantener la actual
                u.contraseña = u.contraseña;
            }
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

        [HttpPost("actualizar-contraseñas")]
        public async Task<ActionResult> ActualizarContraseñas()
        {
            var usuarios = await _context.Usuarios.ToListAsync();
            foreach (var usuario in usuarios)
            {
                if (usuario.contraseña.Length <= 30)
                {
                    usuario.contraseña = _passwordHasher.HashPassword(usuario, usuario.contraseña);
                    _context.Usuarios.Update(usuario);
                }
            }
            await _context.SaveChangesAsync();
            return Ok(new { mensaje = "Contraseñas actualizadas correctamente." });
        }
    }
}
