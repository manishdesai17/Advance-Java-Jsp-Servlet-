/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;


/**
 *
 * @author MAHADEV
 */
public class pro4 extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void display(PrintWriter out, HttpServletRequest request, HttpServletResponse response, String msg) {
        try {
            RequestDispatcher rd = request.getRequestDispatcher("pro4.html");
            rd.include(request, response);
            out.println("<script>error=document.getElementById('error');");
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
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String role = request.getParameter("role");
            String msg = "";
            int success = 0;
            java.sql.Connection conn = null;
            if (email.isEmpty() || password.isEmpty() || role.isEmpty()) {
                msg = "Please fill the fill field";
            } else if (!email.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")) {
                msg = "Enter valid email.";
            } else {
                try {

                    Class.forName("com.mysql.cj.jdbc.Driver");
                    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sms", "root", "");
                    String s = "select email,password,role from user where email='" + email + "' and password='" + password + "' and role='" + role + "'";
                    Statement st = conn.createStatement();
                    ResultSet rs = st.executeQuery(s);
                    if (rs.next()) {
                        String email_id = rs.getString("email");
                        String pass = rs.getString("password");
                        String role_user = rs.getString("role");

                        Statement sst = conn.createStatement();
                        ResultSet rss = sst.executeQuery("select email,role from user_role where email='" + email + "' and role='" + role + "'");
                        if (rss.next()) {
                            success = 0;
                            if (role_user.equalsIgnoreCase("registeredUser")) {
                                response.sendRedirect("pro4_user.html");
                            } else if (role_user.equalsIgnoreCase("admin")) {
                                response.sendRedirect("pro4_admin.html");
                            }
                            else
                            {
                                response.sendRedirect("pro4_guest.html");
                            }
                        } else {
                            Statement statment = conn.createStatement();
                            statment.executeUpdate("insert into user_role (email,role) values('" + email + "','" + role + "')");
                            if (role_user.equalsIgnoreCase("registeredUser")) {
                                response.sendRedirect("pro4_user.html");
                            } else if (role_user.equalsIgnoreCase("admin")) {
                                response.sendRedirect("pro4_admin.html");
                            }
                             else
                            {
                                response.sendRedirect("pro4_guest.html");
                            }
                        }
                    } else {
                        msg = "Detail not match..!";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            display(out, request, response, msg);
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
