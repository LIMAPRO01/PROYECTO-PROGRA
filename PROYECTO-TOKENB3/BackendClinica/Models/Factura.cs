namespace BackendClinica.Models
{
    public class Factura
    {
        public int IdFactura { get; set; }
        public int IdPaciente { get; set; }
        public DateTime FechaEmision { get; set; } = DateTime.Now;
        public decimal Total { get; set; }

        // Propiedad de navegación
        public Paciente? Paciente { get; set; }
    }
}
