using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace BackendClinica.Models
{
    public class Cita
    {
        public int IdCita { get; set; }
        public int IdPaciente { get; set; }
        public int idmedico { get; set; }
        public DateTime FechaCita { get; set; }
        public required string motivo { get; set; }
        public required string Estado { get; set; } = "Programada";
        [DisplayFormat(DataFormatString = "{0:dd/MM/yyyy}", ApplyFormatInEditMode = true)]
        public required DateTime FechaRegistro { get; set; } = DateTime.Now;

        // Solo propiedades de navegación, sin [ForeignKey]
        public Paciente? Paciente { get; set; }
        public Medico? Medico { get; set; }
    }
}