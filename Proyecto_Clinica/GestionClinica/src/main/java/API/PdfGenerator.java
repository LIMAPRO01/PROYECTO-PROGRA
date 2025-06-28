package API;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.lowagie.text.pdf.draw.LineSeparator;
import java.awt.Color;
import static java.awt.Color.BLACK;
import java.io.FileOutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PdfGenerator {

    public static void generarDetalleFacturaPdf(
             String factura,
            String paciente,
            String cita,
            String motivo,
            String idMed,
            String medicamento,
            String cantidad,
            String subtotal,
            String archivo) {

        try {
            Document document = new Document(PageSize.A4, 50, 50, 50, 50);
            PdfWriter.getInstance(document, new FileOutputStream(archivo));
            document.open();

            // Fuentes
            Font encabezadoFont = new Font(Font.HELVETICA, 24, Font.BOLD, new Color(0, 70, 127));
            Font labelFont = new Font(Font.HELVETICA, 12, Font.BOLD);
            Font valorFont = new Font(Font.HELVETICA, 12);
            
                //LOGO
    
               URL imageUrl = ClassLoader.getSystemResource("small.png");
              if (imageUrl != null) {
                Image logo = Image.getInstance(imageUrl);
                logo.scaleToFit(100, 100); // Escalar si es necesario
                logo.setAlignment(Image.ALIGN_RIGHT); // Puedes cambiar a ALIGN_LEFT
                document.add(logo);
            } else {
                System.out.println("⚠ imagen no encontrada. " );
            }

            // Encabezado "CLINICA MEDICA"
            Paragraph encabezado = new Paragraph("CLINICA MÉDICA", encabezadoFont);
            encabezado.setAlignment(Element.ALIGN_CENTER);
            encabezado.setSpacingAfter(25f);
            document.add(encabezado);

            // Datos principales por separado
            Paragraph pFactura = new Paragraph("Factura: " + factura, valorFont);
            pFactura.setSpacingAfter(8f);
            document.add(pFactura);

            Paragraph pPaciente = new Paragraph("Paciente: " + paciente, valorFont);
            pPaciente.setSpacingAfter(8f);
            document.add(pPaciente);

            Paragraph pCita = new Paragraph("Cita: " + cita, valorFont);
            pCita.setSpacingAfter(8f);
            document.add(pCita);

            Paragraph pMotivo = new Paragraph("Motivo de la Cita: " + motivo, valorFont);
            pMotivo.setSpacingAfter(20f);
            document.add(pMotivo);

            // Tabla para detalle medicamento, cantidad y subtotal
            PdfPTable tablaDetalle = new PdfPTable(3);
            tablaDetalle.setWidthPercentage(80);
            tablaDetalle.setWidths(new int[]{5, 2, 2});
            tablaDetalle.setSpacingBefore(10f);
            tablaDetalle.setSpacingAfter(10f);

            // Encabezados de la tabla
            PdfPCell cellMedicamento = new PdfPCell(new Phrase("Medicamento", labelFont));
            cellMedicamento.setBackgroundColor(new Color(220, 220, 220));
            cellMedicamento.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablaDetalle.addCell(cellMedicamento);

            PdfPCell cellCantidad = new PdfPCell(new Phrase("Cantidad", labelFont));
            cellCantidad.setBackgroundColor(new Color(220, 220, 220));
            cellCantidad.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablaDetalle.addCell(cellCantidad);

            PdfPCell cellSubtotal = new PdfPCell(new Phrase("Subtotal", labelFont));
            cellSubtotal.setBackgroundColor(new Color(220, 220, 220));
            cellSubtotal.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablaDetalle.addCell(cellSubtotal);

            // Datos de la tabla
            PdfPCell cellMedicamentoVal = new PdfPCell(new Phrase(medicamento, valorFont));
            tablaDetalle.addCell(cellMedicamentoVal);

            PdfPCell cellCantidadVal = new PdfPCell(new Phrase(cantidad, valorFont));
            cellCantidadVal.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablaDetalle.addCell(cellCantidadVal);

            PdfPCell cellSubtotalVal = new PdfPCell(new Phrase("Q " + subtotal, valorFont));
            cellSubtotalVal.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tablaDetalle.addCell(cellSubtotalVal);

            document.add(tablaDetalle);

            // Línea separadora: celda con borde inferior
            PdfPTable linea = new PdfPTable(1);
            linea.setWidthPercentage(80);
            PdfPCell lineaCelda = new PdfPCell(new Phrase(""));
            lineaCelda.setBorderWidthBottom(2f);
            lineaCelda.setBorderColorBottom(new Color(0, 70, 127));
            lineaCelda.setFixedHeight(5f);
            lineaCelda.setPadding(0);
            linea.addCell(lineaCelda);
            document.add(linea);

            // Nota final
            Paragraph nota = new Paragraph("Gracias por su preferencia.", new Font(Font.HELVETICA, 10, Font.ITALIC));
            nota.setAlignment(Element.ALIGN_CENTER);
            nota.setSpacingBefore(20f);
            document.add(nota);

            document.close();

            System.out.println("PDF generado en: " + archivo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void generarRecetaPdf(
        String pacienteNombre,
        String medicoNombre,
        String fechaEmision,
        String indicaciones,
        String archivo) {

        try {
            Document document = new Document(PageSize.A4, 50, 50, 50, 50);
            PdfWriter.getInstance(document, new FileOutputStream(archivo));
            document.open();

            // Fuentes
            Font encabezadoFont = new Font(Font.HELVETICA, 24, Font.BOLD, new Color(0, 70, 127));
            Font labelFont = new Font(Font.HELVETICA, 12, Font.BOLD);
            Font valorFont = new Font(Font.HELVETICA, 12);
          
            //LOGO
            URL imageUrl = ClassLoader.getSystemResource("small.png");
            if (imageUrl != null) {
                Image logo = Image.getInstance(imageUrl);
                logo.scaleToFit(100, 100); // Escalar si es necesario
                logo.setAlignment(Image.ALIGN_RIGHT); // Puedes cambiar a ALIGN_LEFT
                document.add(logo);
            } else {
                System.out.println("⚠ imagen no encontrada. ");
            }
            
            // Encabezado "RECETA MEDICA"
            Paragraph encabezado = new Paragraph("RECETA MÉDICA", encabezadoFont);
            encabezado.setAlignment(Element.ALIGN_CENTER);
            encabezado.setSpacingAfter(25f);
            document.add(encabezado);

            // Datos de la receta
            Paragraph pPaciente = new Paragraph("Paciente: " + pacienteNombre, valorFont);
            pPaciente.setSpacingAfter(8f);
            document.add(pPaciente);

            Paragraph pMedico = new Paragraph("Médico: " + medicoNombre, valorFont);
            pMedico.setSpacingAfter(8f);
            document.add(pMedico);

            Paragraph pFecha = new Paragraph("Fecha de Emisión: " + fechaEmision, valorFont);
            pFecha.setSpacingAfter(20f);
            document.add(pFecha);

            Paragraph pIndicacionesLabel = new Paragraph("Indicaciones:", labelFont);
            pIndicacionesLabel.setSpacingAfter(5f);
            document.add(pIndicacionesLabel);

            Paragraph pIndicaciones = new Paragraph(indicaciones, valorFont);
            pIndicaciones.setSpacingAfter(20f);
            document.add(pIndicaciones);

            Paragraph firma = new Paragraph("______________________________\nFirma del Médico", labelFont);
            firma.setAlignment(Element.ALIGN_CENTER);
            firma.setSpacingBefore(40f);
            document.add(firma);
            
            document.close();

            System.out.println("PDF de receta generado en: " + archivo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
      public static void generarCitaPdf(
        String pacienteNombre,
        String medicoNombre,
        String fechaCita,
        String Motivo,       
        String archivo) {

        try {
            Document document = new Document(PageSize.A4, 50, 50, 50, 50);
            PdfWriter.getInstance(document, new FileOutputStream(archivo));
            document.open();

            // Fuentes
            Font tituloFont = new Font(Font.HELVETICA, 22, Font.BOLD, new Color(0, 70, 127));
            Font labelFont = new Font(Font.HELVETICA, 12, Font.BOLD, Color.BLACK);
            Font valorFont = new Font(Font.HELVETICA, 12, Font.NORMAL, Color.DARK_GRAY);
            Font pieFont = new Font(Font.HELVETICA, 10, Font.ITALIC, Color.GRAY);
            //LOGO
    
               URL imageUrl = ClassLoader.getSystemResource("small.png");
              if (imageUrl != null) {
                Image logo = Image.getInstance(imageUrl);
                logo.scaleToFit(100, 100); // Escalar si es necesario
                logo.setAlignment(Image.ALIGN_RIGHT); // Puedes cambiar a ALIGN_LEFT
                document.add(logo);
            } else {
                System.out.println("⚠ imagen no encontrada. " );
            }

            // Encabezado "CITA MEDICA"
            Paragraph encabezado = new Paragraph("CITA MÉDICA", tituloFont);
            encabezado.setAlignment(Element.ALIGN_CENTER);
            encabezado.setSpacingAfter(20f);
            document.add(encabezado);
            
            //Linea Divisora
            LineSeparator separator = new LineSeparator();
            separator.setLineColor(Color.LIGHT_GRAY);
            document.add(new Chunk(separator));
            document.add(Chunk.NEWLINE);
          
            //Tabla
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(65);
            table.setSpacingBefore(20f);
            table.setSpacingAfter(20f);
            table.setWidths(new float[]{2f, 2f});
            

            // Rellenar tabla
            table.addCell(celda("Paciente:", labelFont, Element.ALIGN_RIGHT, null));
            table.addCell(celda(pacienteNombre, valorFont, Element.ALIGN_LEFT, null));

            table.addCell(celda("Médico:", labelFont, Element.ALIGN_RIGHT, null));
            table.addCell(celda(medicoNombre, valorFont, Element.ALIGN_LEFT, null));

            table.addCell(celda("Fecha de la cita:", labelFont, Element.ALIGN_RIGHT, null));
            table.addCell(celda(fechaCita, valorFont, Element.ALIGN_LEFT, null));
             
            document.add(table);

            // Motivo
            Paragraph motivoLabel = new Paragraph("Motivo de la cita", labelFont);
            motivoLabel.setSpacingAfter(6f);
            document.add(motivoLabel);

            Paragraph motivoText = new Paragraph(Motivo, valorFont);
            motivoText.setFirstLineIndent(0f);
            motivoText.setSpacingAfter(80f);
            document.add(motivoText);

            // Fecha automática y firma
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String fechaHoy = sdf.format(new Date());

            Paragraph fechaGeneracion = new Paragraph("Cita generada el: " + fechaHoy, pieFont);
            fechaGeneracion.setAlignment(Element.ALIGN_CENTER);
            fechaGeneracion.setSpacingBefore(30f);
            document.add(fechaGeneracion);

            Paragraph firma = new Paragraph("______________________________\nFirma del Médico", labelFont);
            firma.setAlignment(Element.ALIGN_CENTER);
            firma.setSpacingBefore(40f);
            document.add(firma);
            
               // Nota
            Paragraph nota = new Paragraph("Por favor asistir puntualmente a la cita en la fecha indicada.", pieFont);
            nota.setAlignment(Element.ALIGN_CENTER);
            nota.setSpacingBefore(10f);
            document.add(nota);
            
            document.close();
            System.out.println("PDF generado en: " + archivo);
      
        } catch (Exception e) {
            e.printStackTrace();
        }
      }

    private static PdfPCell celda(String texto, Font font, int alignment, Color bg) {
        PdfPCell cell = new PdfPCell(new Phrase(texto, font));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(alignment);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        if (bg != null) cell.setBackgroundColor(bg);
        cell.setPadding(5f);
        return cell;
    }
}