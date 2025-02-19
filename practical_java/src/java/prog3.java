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

public class prog3 extends HttpServlet {

    public void display(String msg, PrintWriter out, HttpServletRequest request, HttpServletResponse response, String fname, String lname, String password, String conf, String email, String mobile, String address, int success) {
        try {

            RequestDispatcher rd = request.getRequestDispatcher("pro3.html");
            rd.include(request, response);

            out.println("<script>error=document.getElementById('error');");
            out.println("document.getElementById('fname').value='" + fname + "';");
            out.println("document.getElementById('lname').value='" + lname + "';");
            out.println("document.getElementById('password').value='" + password + "';");
            out.println("document.getElementById('confPassword').value='" + conf + "';");
            out.println("document.getElementById('email').value='" + email + "';");
            out.println("document.getElementById('mobile').value='" + mobile + "';");
            out.println("document.getElementById('address').value='" + address + "';");
            out.println("error.style.display = 'block'");
            out.println(" var div = document.getElementById('error');");
            if (success == 1) {
                out.println("div.style.backgroundColor = '#ecc2c2';");
                out.println("div.style.color='green';");
            }
            else
            {
                out.println("div.style.backgroundColor = '#ecc2c2';");
                out.println("div.style.color='red';");
            }
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
            String fname = request.getParameter("fname");
            String lname = request.getParameter("lname");
            String password = request.getParameter("password");
            String confpassword = request.getParameter("confPassword");
            String email = request.getParameter("email");
            String mobile = request.getParameter("mobile");
            String address = request.getParameter("address");
            String role = request.getParameter("role");
            String msg = "";
            int success = 0;
//            char firstname=fname.charAt(0);
//            char compare_name=Character.toUpperCase(firstname);
//            char lastname=lname.charAt(0);
//            char compare_lname=Character.toUpperCase(lastname);
            Connection conn = null;
            String match_character = "[a-zA-Z]";
            System.out.println(fname);
            System.out.println(lname);
            System.out.println(password);
            System.out.println(confpassword);
            System.out.println(email);
            System.out.println(mobile);
            System.out.println(address);
            System.out.println(role);

            if (fname.isEmpty() || lname.isEmpty() || password.isEmpty() || confpassword.isEmpty() || email.isEmpty() || address.isEmpty() || mobile.isEmpty() || role.isEmpty()) {
                System.out.println("In if");
                msg = "Please fill in the required fields.";
            } else if (fname.length() < 6 || fname.length()>15) {
                msg = "Please enter firstname grater than 6 character & less then 15.";
            } else if (lname.length() < 2 || lname.length()>12 ) {
                msg = "Please enter lastname grater then 2 character && less then 12.";
            } else if (password.length() < 6 || password.length()>15) {
                msg = "Please enter password grater then 6 character & less then 15.";
            } else if (!confpassword.equalsIgnoreCase(password)) {
                msg = "Password not match enter valid password.";
            } 
            else if (address.length()>250)
            {
                msg="enter address length less then 250 .";
            }
            else if (!mobile.matches("\\d+") || mobile.length() != 10) {
                msg = "Mobile number invalid.Please cannot be enter character and space.";
            } else if (!fname.matches("[a-zA-Z ]+")) {
                msg = "Firstname is invalid.Name cannot contain any digit and special character.";
            } else if (!lname.matches("[a-zA-Z ]+")) {
                msg = "Lastname is invalid.Name cannot contain any digit and special character.";
            } else if (!email.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")) {
                msg = "Email is invalid.Enter valid email.";
            } else {
                try {
                    
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sms", "root", "");
                    String s = "insert into user(fname,lname,password,email,mobile,address,role) values('" + fname + "','" + lname + "','" + password + "','" + email + "','" + mobile + "','" + address + "','" + role + "')";
                    Statement st = conn.createStatement();
                    st.executeUpdate(s);
                    msg = "Data successfully inserted....";
                    success = 1;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            display(msg, out, request, response, fname, lname, password, confpassword, email, mobile, address, success);

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
