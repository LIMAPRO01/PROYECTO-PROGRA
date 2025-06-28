namespace BackendClinica.Models
{
    public class MovimientoStock
    {
        public int IdMovimiento { get; set; }
        public int IdMedicamento { get; set; }
        public string TipoMovimiento { get; set; } = null!;
        public int Cantidad { get; set; }
        public DateTime FechaMovimiento { get; set; } = DateTime.Now;
        public string? Descripcion { get; set; }
    }
}
