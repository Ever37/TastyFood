package com.proyectofinal.app.util;

public class ManagerString {

	public static String cleanString(String cadena) {
		
		if(cadena != null) {
			String cleanString = cadena.replaceAll("�", "&ntilde;");
			cleanString = cleanString.replaceAll("�", "&Ntilde;");
			
			cleanString = cleanString.replaceAll("�", "&aacute;");
			cleanString = cleanString.replaceAll("�", "&Aacute;");
			
			cleanString = cleanString.replaceAll("�", "&eacute;");
			cleanString = cleanString.replaceAll("�", "&Eacute;");
			
			cleanString = cleanString.replaceAll("�", "&iacute;");
			cleanString = cleanString.replaceAll("�", "&Iacute;");
			
			cleanString = cleanString.replaceAll("�", "&oacute;");
			cleanString = cleanString.replaceAll("�", "&Oacute;");
			
			cleanString = cleanString.replaceAll("�", "&uacute;");
			cleanString = cleanString.replaceAll("�", "&Uacute;");
			
			return cleanString;			
		} else {
			return cadena;
		}

	}
}
