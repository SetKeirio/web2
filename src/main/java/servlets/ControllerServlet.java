package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import beans.ResultBean;
import beans.ResultListBean;

public class ControllerServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
        //ResultListBean results = new ResultListBean();
        //log("Отправляю1");
        //results.getResults().add(new ResultBean(true, "10", "120", -1, 2, 1));
        //req.getSession().setAttribute("results", results);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("empty").equals("true") && req.getParameter("empty") != null){
            getServletContext().getNamedDispatcher("ClearTableServlet").forward(req, resp);
        }
        else if (req.getParameter("x") != null && req.getParameter("y") != null && req.getParameter("r") != null){
            log(req.getParameter("x") + " " + req.getParameter("y") + " " + req.getParameter("r"));
            String[] names = req.getParameterValues("x");
            for (String n: names){
                log(n);
            }
            getServletContext().getNamedDispatcher("AreaCheckServlet").forward(req, resp);
        }
        else{
            //ResultListBean results = new ResultListBean();
            //results.getResults().add(new ResultBean(true, "10", "120", -1, 2, 1));
            //req.getSession().setAttribute("results", results);
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
        }

    }
}
