namespace HospitalAPI.Models
{
    public class Pacientes
    {
        public int PacienteID { get; set; }
        public required string Nombre { get; set; }       // C# 11: required
        public required string Apellido { get; set; }     // Obliga a inicializar
        public DateTime FechaNacimiento { get; set; }
        public required string Genero { get; set; }
        
        // Propiedades opcionales
        public string? Direccion { get; set; }
        public string? Telefono { get; set; }
        public string? Email { get; set; }
        
        public DateTime FechaRegistro { get; set; } = DateTime.Now;
    }
}