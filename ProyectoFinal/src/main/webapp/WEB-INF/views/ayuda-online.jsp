<%@ page language="java" contentType="application/pdf; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import= "java.io.*" %>
    <%
//CODIGO JSP     
String path = "/resources/doc/MANUAL.pdf";
String realPath = getServletContext().getRealPath(path);
File file = new File(realPath);
FileInputStream ficheroInput = new FileInputStream(file);
int tamanoInput = ficheroInput.available();
byte[] datosPDF = new byte[tamanoInput];
ficheroInput.read( datosPDF, 0, tamanoInput);
 
response.setHeader("Content-disposition","inline; filename=MANUAL.pdf" );
response.setContentType("application/pdf");
response.setContentLength(tamanoInput);
response.getOutputStream().write(datosPDF);
 
ficheroInput.close();
%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Visor PDF</title>
</head>
<body>
 
</body>
</html>