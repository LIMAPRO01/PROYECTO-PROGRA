using System.ComponentModel.DataAnnotations;
using System.Globalization;
namespace BackendClinica.Models
{
    public class Medico
    {
        public int idmedico { get; set; }
        public required string Nombre { get; set; }
        public required string Apellido { get; set; }
        public required string Especialidad { get; set; }
        public required string Telefono { get; set; }
        public required string Email { get; set; }



         [DisplayFormat(DataFormatString = "{0:dd/MM/yyyy}", ApplyFormatInEditMode = true)]
        public DateTime FechaRegistro { get; set; } = DateTime.Now;
        [DisplayFormat(DataFormatString = "{0:dd/MM/yyyy}", ApplyFormatInEditMode = true)]
        public DateTime? FechaActualizacion { get; set; } = DateTime.Now;


        public bool Activo { get; set; } = true;
        public string Estado => Activo ? "Activo" : "Inactivo";
    }
}
