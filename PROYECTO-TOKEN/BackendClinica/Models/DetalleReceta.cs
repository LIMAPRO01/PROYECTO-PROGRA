namespace BackendClinica.Models
{
    public class DetalleReceta
    {
        public int IdDetalle { get; set; }
        public int IdReceta { get; set; }
        public int IdMedicamento { get; set; }
        public int Cantidad { get; set; }
        public string Dosis { get; set; } = null!;

        // Propiedades de navegación necesarias para los Includes
        public Medicamento? Medicamento { get; set; }
    }
}
