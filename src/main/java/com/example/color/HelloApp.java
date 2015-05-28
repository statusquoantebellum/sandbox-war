package com.example.color;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HelloApp extends HttpServlet {
    private PrintWriter out ;
    private int factor = 5 ;

    public HelloApp() {
        super() ;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response ) throws ServletException, IOException {
        final String path = req.getPathTranslated();
        response.setContentType("text/html");
        out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("</head>");
        out.println("<body>");
        out.println("<ul>");
        printLi("Auth type", req.getAuthType()) ;
        printLi("Context path", req.getContextPath()) ;
        printLi("Method", req.getMethod()) ;
        printLi("Path info", req.getPathInfo()) ;
        printLi("Path translated", req.getPathTranslated()) ;
        printLi("Query string", req.getQueryString()) ;
        printLi("Request URI", req.getRequestURI()) ;
        printLi("Servlet path", req.getServletPath()) ;
        final String[] params = req.getPathInfo().split("/") ;
        final int paramFactor ;
        if( params.length > 1 ) {
            paramFactor = Integer.parseInt( params[1]) ;
        } else {
            paramFactor = 5 ;
        }
        factor = paramFactor > 0 ? paramFactor : 5 ;
        printTables( factor ) ;
        out.println("</ul>");
        out.println("</body>");
        out.println("</html>");
    }

    private void printLi( final String key, final String value ){
        out.println(String.format("<li>%s=%s</li>", key, value)) ;
    }

    private void printTables( final int factor ) {
        for( int red : new ColorIterator( factor )) {
            printTable( red, factor, factor ) ;
            out.println("<p/>") ;
        }
    }

    private void printTable( final int red, final int rows, final int columns ) {
        out.print( "<table");
        out.print(" style=\"font-family : monospace\"") ;
        out.println(">") ;
        for( int green : new ColorIterator( rows )) {
            printRow(red, green, columns) ;
        }
        out.println( "</table>" ) ;
    }

    private void printRow( final int red, final int green, final int columns ) {
        out.print("<tr>") ;
        for( int blue : new ColorIterator( columns )) {
            printColumn( red, green, blue ) ;
        }
        out.println("</tr>") ;
    }

    private void printColumn( final int red, final int green, final int blue ) {
        final String color = getColor( red, green, blue ) ;
        final double brightness = getBrightness( red, green, blue ) ;
        out.print("<td") ;
        out.print(String.format(" style=\"background-color : %s",  color ));
        if( brightness < 128 ){
            out.print(";color : #ffffff");
        }
        out.print("\"");
        out.print(">");
        out.print( color ) ;
        out.print("</td>") ;
    }

    private double getBrightness(  final int red, final int green, final int blue ) {
        return 0.2989 * red + 0.5870 * green + 0.1140 * blue ;
    }

    private String getColor( final int red, final int green, final int blue ) {
        return String.format("#%02x%02x%02x", red, green, blue ) ;
    }

}
