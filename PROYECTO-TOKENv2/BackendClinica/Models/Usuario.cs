using System.ComponentModel.DataAnnotations;

namespace BackendClinica.Models
{
    public class Usuario
    {
        [Key]
        public int idusuario { get; set; }
        [System.ComponentModel.DataAnnotations.Schema.Column("usuario")]
        public string usuario { get; set; } = null!;
        public string contraseña { get; set; } = null!;
        public string rol { get; set; } = null!;
        public int? idmedico { get; set; }
        public DateTime fechaRegistro { get; set; } = DateTime.Now;
        public bool  activo { get; set; } = true;
        public string? PasswordResetToken { get; set; }
        public DateTime? PasswordResetTokenExpiration { get; set; }
    }
}
