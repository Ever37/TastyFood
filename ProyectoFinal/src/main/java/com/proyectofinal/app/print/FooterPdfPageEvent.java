package com.proyectofinal.app.print;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.security.core.context.SecurityContextHolder;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.proyectofinal.app.core.security.CustomUserDetails;

public class FooterPdfPageEvent extends  PdfPageEventHelper {
 
    protected static final Font FONT_COURIER_10 = new Font(
    	    Font.FontFamily.COURIER, 10, Font.NORMAL);
    
 @Override
 public void onEndPage(PdfWriter writer, Document document) {
	 
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date hoy = new Date();
		String hoy1 = formatoFecha.format(hoy);
		
	 	CustomUserDetails user = (CustomUserDetails)
				SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ColumnText.showTextAligned
		(writer.getDirectContent(), 
				Element.ALIGN_CENTER, 
				new Phrase("Documento generado por: " + user.getNombreUsuario() + " - " + hoy1, FONT_COURIER_10),				
				300,20,0);
  
 }
}