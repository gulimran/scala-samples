package imran.scala.rest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

public class TestRestService extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        out.println("Get method called");
        out.println("parameters: " + parameters(request));
        out.println("headers: " + headers(request));
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        out.println("Post method called");
        out.println("parameters: " + parameters(request));
        out.println("headers: " + headers(request));
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        out.println("Delete method called");
    }

    private String parameters(HttpServletRequest request) {

        StringBuilder builder = new StringBuilder();

        for (Enumeration e = request.getParameterNames(); e.hasMoreElements();) {

            String name = (String)e.nextElement();

            builder.append("|")
                    .append(name)
                    .append("->")
                    .append(request.getParameter(name));
        }

        return builder.toString();
    }

    private String headers(HttpServletRequest request) {

        StringBuilder builder = new StringBuilder();

        for (Enumeration e = request.getHeaderNames(); e.hasMoreElements();) {

            String name = (String)e.nextElement();

            builder.append("|")
                    .append(name)
                    .append("->")
                    .append(request.getHeader(name));
        }

        return builder.toString();
    }
}
