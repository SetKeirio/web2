package servlets;

import beans.ResultListBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClearTableServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResultListBean results = (ResultListBean) req.getSession().getAttribute("results");
        if (results == null){
            results = new ResultListBean();
        }
        else {
            results.getResults().clear();
        }
        req.getSession().setAttribute("results", results);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
        dispatcher.forward(req, resp);
    }
}
