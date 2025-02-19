/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MAHADEV
 */
public class pro2 extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            ServletConfig config = getServletConfig();
            Enumeration<String> initParamNames = config.getInitParameterNames();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet javapracticle2</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>Init parameters</h2>");
            out.println("<table border='2' style='border-color:solid black'>");
            out.println("<tr><th>Parameter Name</th><th>Parameter Value</th></tr>");

            while (initParamNames.hasMoreElements()) {
                System.out.println();
                String paramName = initParamNames.nextElement();
                String paramValue = config.getInitParameter(paramName);
                out.println("<tr><td>" + paramName + "</td><td>" + paramValue + "</td></tr>");
           
            }
            out.println("</table>");
            out.println("<h2>Servlet header Information</h2>");
            out.println("<table border='1'>");
            out.println("<tr><th>Parameter Name</th><th>Parameter Value</th></tr>");
            
            Enumeration<String> e=request.getHeaderNames();
            while(e.hasMoreElements())
            {
                String headername=e.nextElement();
                String headervalue=request.getHeader(headername);
                out.println("<tr><td>" + headername + "</td><td>" + headervalue + "</td></tr>");
            }
            out.println("</table>");
            out.println("<h2>Client and Server Information</h2>");
            out.println("<table border='1'>");
            out.println("<tr><th>Type</th><th>Name</th><th>Value</th></tr>");

            out.println("<tr><td>Client</td><td>IP Address</td><td>" + request.getRemoteAddr() + "</td></tr>");
            out.println("<tr><td>Client</td><td>Host</td><td>" + request.getRemoteHost() + "</td></tr>");
            out.println("<tr><td>Client</td><td>User-Agent</td><td>" + request.getHeader("User-Agent") + "</td></tr>");

            out.println("<tr><td>Server</td><td>Server Name</td><td>" + request.getServerName() + "</td></tr>");
            out.println("<tr><td>Server</td><td>Server Port</td><td>" + request.getServerPort() + "</td></tr>");
            out.println("<tr><td>Server</td><td>Protocol</td><td>" + request.getProtocol() + "</td></tr>");
            out.println("<tr><td>Server</td><td>Servlet Context</td><td>" + request.getServletContext().getContextPath() + "</td></tr>");
           
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
