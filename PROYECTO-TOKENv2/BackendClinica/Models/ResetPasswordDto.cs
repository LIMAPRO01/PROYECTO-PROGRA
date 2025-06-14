using System.ComponentModel.DataAnnotations;
namespace BackendClinica.Models
{
    public class ResetPasswordDto
    {
        [Required(ErrorMessage = "El campo 'Usuario' es obligatorio.")]
        public string Usuario { get; set; } = null!;

        [Required(ErrorMessage = "El campo 'Nueva Contraseña' es obligatorio.")]
        [StringLength(100, ErrorMessage = "La contraseña debe tener al menos {2} caracteres.", MinimumLength = 6)]
        public string NuevaContraseña { get; set; } = null!;

        [Required(ErrorMessage = "El campo 'Confirmar Contraseña' es obligatorio.")]
        [Compare("NuevaContraseña", ErrorMessage = "Las contraseñas no coinciden.")]
        public string ConfirmarContraseña { get; set; } = null!;
    }
}