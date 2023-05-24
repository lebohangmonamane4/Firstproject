package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try {
            Connection con = MyConnection.getConnection();

            String sql = "SELECT users.userID, users.roleID, roles.roleName, students.studentID, instructors.instructorID"
                    + " FROM users LEFT JOIN roles ON users.roleID = roles.roleID LEFT JOIN students ON users.userID = students.userID"
                    + " LEFT JOIN instructors ON users.userID = instructors.userID "
                    + "WHERE users.username = ? AND users.password = ?";

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                HttpSession session = request.getSession();
                session.setAttribute("userID", rs.getInt("userID"));
                session.setAttribute("roleID", rs.getInt("roleID"));
                session.setAttribute("studentID", rs.getInt("studentID"));
                session.setAttribute("instructorID", rs.getInt("instructorID"));
                session.setAttribute("rolename", rs.getString("roleName"));

                if (rs.getString("rolename").equals("Student")) {
                    session.setAttribute("username", username);
                    session.setAttribute("studentID", rs.getInt("studentID"));

                    out.println("<html>");
                    out.println("<head>");
                    out.println("<script>");
                    out.println("alert('You have successfully logged in!');");
                    out.println("window.location.replace('student/studentmenu.jsp');");
                    out.println("</script>");
                    out.println("</head>");
                    out.println("</html>");
                   

                } else if (rs.getString("rolename").equals("Instructor")) {
                    session.setAttribute("username", username);
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<script>");
                    out.println("alert('You have successfully logged in!');");
                    out.println("window.location.replace('instructor/instructormenu.jsp');");
                    out.println("</script>");
                    out.println("</head>");
                    out.println("</html>");
                  

                } else if (rs.getString("rolename").equals("Admin")) {
                    session.setAttribute("username", username);

                    out.println("<html>");
                    out.println("<head>");
                    out.println("<script>");
                    out.println("alert('You have successfully logged in!');");
                    out.println("window.location.replace('administrator/administratormenu.jsp');");
                    out.println("</script>");
                    out.println("</head>");
                    out.println("</html>");
                } else {
                    RequestDispatcher rd = request.getRequestDispatcher("invalidate.jsp");
                    rd.forward(request, response);

                }
            } else {

                out.println("<script>");
                out.println("alert('User Not Available');");
                out.println("window.location.href = 'login.jsp';");
                out.println("</script>");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
