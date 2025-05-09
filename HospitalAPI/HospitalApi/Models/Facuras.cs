namespace HospitalAPI.Models
{
    public class Facturas
    {
        public int FacturaID { get; set; }
        // Propiedades de la clase Facturas
        public int CitaID { get; set; }
        public int PacienteID { get; set; }
        public DateTime FechaEmision { get; set; }
        public decimal Monto { get; set; }
        public required string Estado { get; set; } // Requiere inicialización
    }
}