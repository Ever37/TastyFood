package com.proyectofinal.app.print;

import java.util.Calendar;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

@Controller
public class HeaderPdfPageEvent extends PdfPageEventHelper  {

	@Autowired
	ServletContext servletContext;
	
    protected static final Font FONT_COURIER_10 = new Font(Font.FontFamily.COURIER, 10, Font.NORMAL);
    protected static final Font FONT_ARIAL_8 = FontFactory.getFont("arial",8,Font.BOLD,BaseColor.BLACK);
    protected static final Font FONT_ARIAL_10 = FontFactory.getFont("arial",10,Font.BOLD,BaseColor.BLACK);
    protected static final Font FONT_ARIAL_9 = FontFactory.getFont("arial",9,Font.NORMAL,BaseColor.BLACK);
    protected static final Font FONT_ARIAL_12 = FontFactory.getFont("arial",10,Font.BOLD,BaseColor.BLACK);
    protected static final Font FONT_ARIAL_14 = new Font(Font.FontFamily.HELVETICA, 14, Font.NORMAL);
    public static final Font FONT_COURIER_18_BOLD = new Font(Font.FontFamily.HELVETICA, 21, Font.BOLD);  
    Image image;

    @Override
    public void onStartPage(PdfWriter writer, Document document) {

	Calendar fechaHoy = Calendar.getInstance();
	String dia = Integer.toString(fechaHoy.get(Calendar.DATE));
	String mes = Integer.toString(fechaHoy.get(Calendar.MONTH) + 1);
	String anio = Integer.toString(fechaHoy.get(Calendar.YEAR));

	ColumnText.showTextAligned(writer.getDirectContent(),
		Element.ALIGN_RIGHT,
		new Phrase("Fecha de emisión: " + dia + "/" + mes + "/" + anio, FONT_ARIAL_9),550,810,0);
	
	ColumnText.showTextAligned(writer.getDirectContent(),
			Element.ALIGN_LEFT,
			new Phrase("ASYT - Área de Salud y Trabajo", FONT_ARIAL_10),170,800,0);
	
	ColumnText.showTextAligned(writer.getDirectContent(),
			Element.ALIGN_LEFT,
			new Phrase("Facultad de Ciencias Médicas. Universidad Nacional de Rosario.", FONT_ARIAL_9),170,790,0);
	
	ColumnText.showTextAligned(writer.getDirectContent(),
			Element.ALIGN_LEFT,
			new Phrase("Santa Fe 3102, Rosario. CP: S2002KTR. Provincia de Santa Fe, Argentina.", FONT_ARIAL_9),170,780,0);
	
	ColumnText.showTextAligned(writer.getDirectContent(),
			Element.ALIGN_LEFT,
			new Phrase("Teléfono/Fax: 54 341 480 4556", FONT_ARIAL_9),170,770,0);
	
    }

}
