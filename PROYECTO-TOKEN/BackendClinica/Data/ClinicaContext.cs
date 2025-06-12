using Microsoft.EntityFrameworkCore;
using BackendClinica.Models;

namespace BackendClinica.Data
{
    public class ClinicaContext : DbContext
    {
        public ClinicaContext(DbContextOptions<ClinicaContext> options) : base(options)
        {
        }

        public DbSet<Paciente> Pacientes { get; set; }
        public DbSet<Medico> Medicos { get; set; }
        public DbSet<Cita> Citas { get; set; }
        public DbSet<Medicamento> Medicamentos { get; set; }
        public DbSet<RecetaMedica> RecetasMedicas { get; set; }
        public DbSet<DetalleReceta> DetalleRecetas { get; set; }
        public DbSet<Factura> Facturas { get; set; }
        public DbSet<DetalleFactura> DetalleFacturas { get; set; }
        public DbSet<Usuario> Usuarios { get; set; }
        public DbSet<MovimientoStock> MovimientoStocks { get; set; }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);

            modelBuilder.Entity<Paciente>(entity =>
            {
                entity.HasKey(e => e.IdPaciente);
                entity.Property(e => e.Nombre).IsRequired().HasMaxLength(100);
                entity.Property(e => e.Apellido).IsRequired().HasMaxLength(100);
                entity.Property(e => e.Direccion).IsRequired().HasMaxLength(200);
                entity.Property(e => e.Telefono).IsRequired().HasMaxLength(15);
                entity.Property(e => e.Email).IsRequired().HasMaxLength(100);
                entity.Property(e => e.FechaNacimiento).IsRequired();
                entity.Property(e => e.FechaRegistro).IsRequired();
                entity.Property(e => e.Genero).HasMaxLength(50);
                entity.Property(e => e.FechaActualizacion);
            });

            modelBuilder.Entity<Cita>(entity =>
            {
                entity.HasKey(e => e.IdCita);
                entity.Property(e => e.IdPaciente).IsRequired();
                entity.Property(e => e.idmedico).IsRequired();
                entity.Property(e => e.FechaCita).IsRequired();
                entity.Property(e => e.motivo).IsRequired().HasMaxLength(200);
                entity.Property(e => e.Estado).IsRequired().HasMaxLength(50);
                entity.Property(e => e.FechaRegistro).IsRequired();

                // Configuración explícita de las relaciones
                entity.HasOne(e => e.Paciente)
                    .WithMany()
                    .HasForeignKey(e => e.IdPaciente)
                    .OnDelete(DeleteBehavior.Restrict);

                entity.HasOne(e => e.Medico)
                    .WithMany()
                    .HasForeignKey(e => e.idmedico)
                    .OnDelete(DeleteBehavior.Restrict);
            });

            modelBuilder.Entity<DetalleFactura>(entity =>
            {
                entity.HasKey(e => e.IdDetalle);
                entity.Property(e => e.IdFactura).IsRequired();
                entity.Property(e => e.IdCita).IsRequired();
                entity.Property(e => e.IdMedicamento).IsRequired();
                entity.Property(e => e.Cantidad).IsRequired();
                entity.Property(e => e.Subtotal).IsRequired().HasColumnType("decimal(18,2)");

                // Relación con Factura
                entity.HasOne(e => e.Factura)
                    .WithMany()
                    .HasForeignKey(e => e.IdFactura)
                    .OnDelete(DeleteBehavior.Restrict);

                // Relación con Cita
                entity.HasOne(e => e.Cita)
                    .WithMany()
                    .HasForeignKey(e => e.IdCita)
                    .OnDelete(DeleteBehavior.Restrict);

                // Relación con Medicamento
                entity.HasOne(e => e.Medicamento)
                    .WithMany()
                    .HasForeignKey(e => e.IdMedicamento)
                    .OnDelete(DeleteBehavior.Restrict);
            });

            modelBuilder.Entity<DetalleReceta>(entity =>
            {
                entity.HasKey(e => e.IdDetalle);
                entity.Property(e => e.IdReceta).IsRequired();
                entity.Property(e => e.IdMedicamento).IsRequired();
                entity.Property(e => e.Cantidad).IsRequired();
                entity.Property(e => e.Dosis).IsRequired().HasMaxLength(100);

               

                entity.HasOne(e => e.Medicamento)
                    .WithMany()
                    .HasForeignKey(e => e.IdMedicamento)
                    .OnDelete(DeleteBehavior.Restrict);
            });

            modelBuilder.Entity<Factura>(entity =>
            {
                entity.HasKey(e => e.IdFactura);
                entity.Property(e => e.IdPaciente).IsRequired();
                entity.Property(e => e.FechaEmision).IsRequired();
                entity.Property(e => e.Total).IsRequired().HasColumnType("decimal(18,2)");

                // Configuración explícita de la relación
                entity.HasOne(e => e.Paciente)
                    .WithMany()
                    .HasForeignKey(e => e.IdPaciente)
                    .OnDelete(DeleteBehavior.Restrict);
            });

            modelBuilder.Entity<Medicamento>(entity =>
            {
                entity.HasKey(e => e.IdMedicamento);
                entity.Property(e => e.Nombre).IsRequired().HasMaxLength(100);
                entity.Property(e => e.Precio).IsRequired().HasColumnType("decimal(10,2)");
                entity.Property(e => e.Stock).IsRequired();
            });

            modelBuilder.Entity<Medico>(entity =>
            {
                entity.HasKey(e => e.idmedico);
                entity.Property(e => e.Nombre).IsRequired().HasMaxLength(100);
                entity.Property(e => e.Apellido).IsRequired().HasMaxLength(100);
                entity.Property(e => e.Especialidad).IsRequired().HasMaxLength(100);
                entity.Property(e => e.Telefono).IsRequired().HasMaxLength(20);
                entity.Property(e => e.Email).IsRequired().HasMaxLength(100);
                entity.Property(e => e.FechaRegistro).IsRequired();
                entity.Property(e => e.FechaActualizacion);
                entity.Property(e => e.Activo).IsRequired();
            });

            modelBuilder.Entity<MovimientoStock>(entity =>
            {
                entity.HasKey(e => e.IdMovimiento);
                entity.Property(e => e.IdMedicamento).IsRequired();
                entity.Property(e => e.TipoMovimiento).IsRequired().HasMaxLength(50);
                entity.Property(e => e.Cantidad).IsRequired();
                entity.Property(e => e.FechaMovimiento).IsRequired();
            });

            modelBuilder.Entity<RecetaMedica>(entity =>
            {
                entity.HasKey(e => e.IdReceta);
                entity.Property(e => e.IdPaciente).IsRequired();
                entity.Property(e => e.idmedico).IsRequired();
                entity.Property(e => e.Indicaciones).HasMaxLength(500);
                entity.Property(e => e.FechaEmision).IsRequired();

                // Configuración explícita de las relaciones
                entity.HasOne(e => e.Paciente)
                    .WithMany()
                    .HasForeignKey(e => e.IdPaciente)
                    .OnDelete(DeleteBehavior.Restrict);

                entity.HasOne(e => e.Medico)
                    .WithMany()
                    .HasForeignKey(e => e.idmedico)
                    .OnDelete(DeleteBehavior.Restrict);
            });

            modelBuilder.Entity<Usuario>(entity =>
            {
                entity.HasKey(e => e.idusuario);
                entity.Property(e => e.usuario).HasColumnName("usuario").IsRequired().HasMaxLength(50);
                entity.Property(e => e.contraseña).IsRequired().HasMaxLength(255);
                entity.Property(e => e.rol).IsRequired().HasMaxLength(50);
                entity.Property(e => e.fechaRegistro).IsRequired();
                entity.Property(e => e.activo).IsRequired();
            });
        }
    }
}