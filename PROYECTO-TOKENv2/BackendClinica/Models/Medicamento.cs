namespace BackendClinica.Models
{
    public class Medicamento
    {
        public int IdMedicamento { get; set; }
        public string Nombre { get; set; } = null!;
        public string? Descripcion { get; set; }
        public decimal Precio { get; set; }
        public int Stock { get; set; }
    }
}
