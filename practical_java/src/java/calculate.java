/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.*;

/**
 *
 * @author st
 */
public class calculate extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void display(PrintWriter out, String msg) {

        out.println("<html>");
        out.println("<head>");
        out.println("<style>");
        out.println("body {");
        out.println("  font-family: Arial, sans-serif;");
        out.println("  display: flex;");
        out.println("  justify-content: center;");
        out.println("  align-items: center;");
        out.println("  height: 100vh;");
        out.println("  margin: 0;");
        out.println("  background-color: #f4f4f4;");
        out.println("}");
        out.println(".alert-box {");
        out.println("  background-color: #ffffff;");
        out.println("  color: #721c24;");
        out.println("  padding: 20px;");
        out.println("  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1)");
        out.println("  border-radius: 10px;");
        out.println("  max-width: 400px;");
        out.println("  text-align: center;");
        out.println("  font-size: 16px;");
        out.println("}");
        out.println(".button {");
        out.println("  padding: 10px 20px;");
        out.println("  background-color: #007bff;");
        out.println("  color: white;");
        out.println("  text-decoration: none;");
        out.println("  border-radius: 5px;");
        out.println("  font-size: 16px;");
        out.println("  border: none;");
        out.println("  display: inline-block;");
        out.println("  margin-top: 20px;");
        out.println("}");
        out.println(".button:hover {");
        out.println("  background-color: #0056b3;");
        out.println("}");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");

        // Alert message box content
        out.println("<div class='alert-box'>");
        out.println("<strong>Warning!</strong><br>");
        out.println(msg);
        out.println("<br><br>");

        // Go back button
        out.println("<a href='pro1.html' class='button'>Go Back</a>");
        out.println("</div>");

        out.println("</body>");
        out.println("</html>");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String amount = request.getParameter("principal");
            String rate = request.getParameter("interest");
            String year = request.getParameter("year");
            String month = request.getParameter("months");
            String interval = request.getParameter("interval");
            String msg = "";
            String regex = "^[0-9]+$";
            if (amount.equals("")) {
                msg = "Please enter a name. The name cannot be blank.";
            } else if (rate.equals("")) {
                msg = "Please enter a rate. The rate cannot be blank.";
            } else if (year.equals("")) {
                msg = "Please enter a year. The year cannot be blank.";
            } else if (month.equals("")) {
                msg = "Please enter a month. The month cannot be blank.";
            } else if (!amount.matches(regex) || !rate.matches(regex) || !year.matches(regex) || !month.matches(regex)) {
                msg = "Please enter any digit not character.";
            } else if (year.length() > 3) {
                msg = "Year cannot be  enter above 999 year.";
            } else if (month.length() >= 2) {
                msg = "Month enter only two digit.";
            } else {
                long amount_val = Long.parseLong(amount);
                float rate_val = Integer.parseInt(rate);
                int year_val = Integer.parseInt(year);
                int month_val = Integer.parseInt(month);
                int interval_val=Integer.parseInt(interval);
                if (amount_val <= 0) {
                    msg = "Amount cannot be enter negative or zero.";
                } else if (rate_val <= 0 || rate_val > 101) {
                    msg = "Rate cannot be enter nagative and above 100%";
                } else if (year_val == 0 && month_val == 0) {
                    msg = "Year and Month both cannot be enter zero";
                } else if (month_val > 12) {
                    msg = "Month cannot be enter  above 11";
                } else {
                    float time = (float) ((year_val * 12 + month_val) / 12.0);

                    float total_amount = (float) (amount_val * Math.pow((1 + rate_val / (interval_val * 100)), (interval_val * time)));

                    RequestDispatcher rd = request.getRequestDispatcher("pro1.html");
                    rd.include(request, response);

                    out.println("<script> var result=document.getElementById('futureValue')");
                    float intereset = total_amount - amount_val;
                    out.println("result.textContent=" + String.format("%.2f", intereset));
                    out.println("</script>");
                }
            }

            display(out, msg);

//            float time = (float) ((year * 12 + month) / 12.0);
//
//            float total_amount = (float) (amount * Math.pow((1 + rate / (interval * 100)), (interval * time)));
//
//            RequestDispatcher rd = request.getRequestDispatcher("pro1.html");
//            rd.include(request, response);
//
//            out.println("<script> var result=document.getElementById('futureValue')");
//            float intereset = total_amount - amount;
//            out.println("result.textContent=" + String.format("%.2f", intereset));
//            out.println("</script>");
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
