namespace HospitalAPI.Models
{
    public class Citas
    {
        public int CitaID { get; set; }
        public int PacienteID { get; set; }
        public int MedicoID { get; set; }
        public DateTime FechaHora { get; set; }
        public string Motivo { get; set; }
        public string Estado { get; set; }
        
    }
}