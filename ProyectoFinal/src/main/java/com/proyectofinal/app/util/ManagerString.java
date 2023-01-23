package com.proyectofinal.app.util;

public class ManagerString {

	public static String cleanString(String cadena) {
		
		if(cadena != null) {
			String cleanString = cadena.replaceAll("ñ", "&ntilde;");
			cleanString = cleanString.replaceAll("Ñ", "&Ntilde;");
			
			cleanString = cleanString.replaceAll("á", "&aacute;");
			cleanString = cleanString.replaceAll("Á", "&Aacute;");
			
			cleanString = cleanString.replaceAll("é", "&eacute;");
			cleanString = cleanString.replaceAll("É", "&Eacute;");
			
			cleanString = cleanString.replaceAll("í", "&iacute;");
			cleanString = cleanString.replaceAll("Í", "&Iacute;");
			
			cleanString = cleanString.replaceAll("ó", "&oacute;");
			cleanString = cleanString.replaceAll("Ó", "&Oacute;");
			
			cleanString = cleanString.replaceAll("ú", "&uacute;");
			cleanString = cleanString.replaceAll("Ú", "&Uacute;");
			
			return cleanString;			
		} else {
			return cadena;
		}

	}
}
