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
    public void display(PrintWriter out, String msg, HttpServletRequest request, HttpServletResponse response, String amount, String rate, String year, String month) {
        try {

            RequestDispatcher rd = request.getRequestDispatcher("pro1.html");
            rd.include(request, response);

            out.println("<script>error=document.getElementById('error');");
            out.println("document.getElementById('principal').value='" + amount + "';");
            out.println("document.getElementById('rate').value='" + rate + "';");
            out.println("document.getElementById('Year').value='" + year + "';");
            out.println("document.getElementById('months').value='" + month + "';");
            out.println("error.style.display = 'block'");
            out.println("error.textContent='" + msg + "';");
            out.println("</script>");
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            int success = 0;
            String regex = "^[0-9]+$";
            if (amount.equals("")) {
                msg = "Please enter a amount.";
            } else if (rate.equals("")) {
                msg = "Please enter a rate.";
            } else if (year.equals("")) {
                msg = "Please enter a year.";
            } else if (month.equals("")) {
                msg = "Please enter a month.";
            } else if (!amount.matches(regex) || !rate.matches(regex) || !year.matches(regex) || !month.matches(regex)) {
                msg = "Please enter any positive digit";
            } else {
                long amount_val = Long.parseLong(amount);
                int rate_val = Integer.parseInt(rate);
                int year_val = Integer.parseInt(year);
                int month_val = Integer.parseInt(month);
                int interval_val = Integer.parseInt(interval);
                if (amount_val <= 0) {
                    msg = "Amount cannot be enter negative or zero.";
                } else if (rate_val <= 0 || rate_val > 100) {
                    msg = "Rate cannot be enter 0 and above 100%";
                } else if (year_val == 0 && month_val == 0) {
                    msg = "Year and Month both cannot be enter zero";
                } else if (month_val > 12) {
                    msg = "Month cannot be enter above 11";
                } else if (year_val > 1000) {
                    msg = "Year cannot be  enter above 999 year.";
                }
//                else if(interval_val!=360 ||interval_val!=365 ||interval_val!=52 ||interval_val!=26 ||interval_val!=24 ||interval_val!=12 ||interval_val!=6||interval_val!=4 ||interval_val!=2 ||interval_val!=1)
//                {
//                     msg = "interval is not valid any problem.";
//                }
                else {
                    System.out.println("yes call");
                    success = 1;
                    System.out.println(amount_val);
                    System.out.println(interval_val);
                    System.out.println(year_val);
                    System.out.println(month_val);
                    float time = (float) ((year_val * 12 + month_val) / 12.0);
                    System.out.println(time);
                    double exp2 = rate_val / (interval_val * 100.0); // Ensure decimal division
                    double exp3 = interval_val * time;
                    double total_amount = amount_val * Math.pow((1 + exp2), exp3);
                    System.out.println(exp2 + " " + exp3);
                    System.out.println(total_amount);
                    RequestDispatcher rd = request.getRequestDispatcher("pro1.html");
                    rd.include(request, response);
                    out.println("<script> var result=document.getElementById('futureValue')");
                    double intereset = total_amount - amount_val;
                    System.out.println(intereset);
                    out.println("result.textContent='Interest is : " + String.format("%.2f", intereset) + "';");
                    out.println("</script>");
                }
            }
            if (success != 1) {
                display(out, msg, request, response, amount, rate, year, month);
            }
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
