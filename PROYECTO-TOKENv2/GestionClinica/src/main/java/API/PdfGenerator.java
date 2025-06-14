package API;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.awt.Color;
import java.io.FileOutputStream;

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

            // Encabezado "CLINICA MEDICA"
            Paragraph encabezado = new Paragraph("CLINICA MEDICA", encabezadoFont);
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

            // Encabezado "RECETA MEDICA"
            Paragraph encabezado = new Paragraph("RECETA MEDICA", encabezadoFont);
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

            // Nota final
            Paragraph nota = new Paragraph("Siga las indicaciones al pie de la letra.", new Font(Font.HELVETICA, 10, Font.ITALIC));
            nota.setAlignment(Element.ALIGN_CENTER);
            nota.setSpacingBefore(20f);
            document.add(nota);

            document.close();

            System.out.println("PDF de receta generado en: " + archivo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}