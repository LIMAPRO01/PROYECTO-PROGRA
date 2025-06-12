namespace BackendClinica.Models
{
    public class RecetaMedica
    {
        public int IdReceta { get; set; }
        public int IdPaciente { get; set; }
        public int idmedico { get; set; }
        public string? Indicaciones { get; set; }
        public DateTime FechaEmision { get; set; } = DateTime.Now;

        // Propiedades de navegación
        public Paciente? Paciente { get; set; }
        public Medico? Medico { get; set; }
    }
}
