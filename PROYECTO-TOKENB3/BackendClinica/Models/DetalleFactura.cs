namespace BackendClinica.Models
{
    public class DetalleFactura
    {
        public int IdDetalle { get; set; }
        public int IdFactura { get; set; }
        public int IdCita { get; set; }
        public int IdMedicamento { get; set; }
        public int Cantidad { get; set; }
        public decimal Subtotal { get; set; }

        // Propiedades de navegación necesarias para los Includes
        public Factura? Factura { get; set; }
        public Cita? Cita { get; set; }
        public Medicamento? Medicamento { get; set; }
    }
}
