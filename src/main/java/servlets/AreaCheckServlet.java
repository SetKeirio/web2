package servlets;

import beans.ResultBean;
import beans.ResultListBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class AreaCheckServlet extends HttpServlet {

    private boolean checkR(String r){
        boolean validate;
        try {
            double real = Double.parseDouble(r);
            validate = ((real >= 1) && (real <= 4));
        }
        catch (NumberFormatException e){
            validate = false;
        }
        return validate;
    }

    private boolean checkX(String x, boolean table){
        boolean validate;
        try {
            double real = Double.parseDouble(x);
            if (table){
                validate = ((real >= -4.0) && (real <= 4.0));
            }
            else {
                validate = (real == -4.0 || real == -3.0 || real == -2.0 || real == -1.0 || real == 0.0 || real == 1.0 || real == 2.0 || real == 3.0 || real == 4.0);
            }
        }
        catch (NumberFormatException e){
            validate = false;
        }
        return validate;
    }

    private boolean checkY(String y){
        boolean validate;
        try {
            double real = Double.parseDouble(y);
            validate = ((real >= -5) && (real <= 3));
        }
        catch (NumberFormatException e){
            validate = false;
        }
        return validate;
    }

    private boolean validate(String x, String y, String r, boolean table){
        return (checkX(x, table) && checkY(y) && checkR(r));
    }

    private boolean inSquare(double x, double y, double r){
        return (x <= 0 && y <= 0 && x >= -r && y >= -r);
    }

    private boolean inTriangle(double x, double y, double r){
        return (x >= 0 && y <= 0 && y >= x - r);
    }

    private boolean inCircle(double x, double y, double r){
        return (x >= 0 && y >= 0 && Math.sqrt(x*x + y*y) <= r);
    }

    private boolean inArea(double x, double y, double r){
        return (inSquare(x, y, r) || inTriangle(x, y, r) || inCircle(x, y, r));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long now = System.nanoTime() / 1000; //milliseconds;
        String r = req.getParameter("r").replace(",", ".");
        //String x = req.getParameter("x").replace(",", ".");
        String[] x = req.getParameterValues("x");
        String y = req.getParameter("y").replace(",", ".");
        String table = req.getParameter("table");
        boolean[] valid = new boolean[x.length];

        for (int i = 0; i < x.length; i++) {
            valid[i] = validate(x[i], y, r, table.equals("true"));
        }
        double realR, realX, realY;
        realR = 0.0;
        realX = 0.0;
        realY = 0.0;
        boolean isInArea = false;
        String nowTime, execTime;
        nowTime = "";
        execTime = "";
        for (int i = 0; i < x.length; i++) {
            if (valid[i]) {
                realR = Double.parseDouble(r);
                realX = Double.parseDouble(x[i]);
                realY = Double.parseDouble(y);
                isInArea = inArea(realX, realY, realR);
                OffsetDateTime offset = OffsetDateTime.now(ZoneId.systemDefault());
                nowTime = offset.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                ResultListBean results = (ResultListBean) req.getSession().getAttribute("results");
                if (results == null) {
                    results = new ResultListBean();
                }
                execTime = String.valueOf((System.nanoTime() / 1000) - now);
                results.getResults().add(new ResultBean(isInArea, nowTime, execTime, realX, realY, realR));
                req.getSession().setAttribute("results", results);
            }
        }

            if (table.equals("true")) {
                log("диспатчер сюда");
                resp.setContentType("text/plain");
                resp.getWriter().write(String.valueOf(realX) + "\n");
                resp.getWriter().write(String.valueOf(realY) + "\n");
                resp.getWriter().write(String.valueOf(realR) + "\n");
                resp.getWriter().write(String.valueOf(nowTime) + "\n");
                resp.getWriter().write(String.valueOf(execTime) + "\n");
                resp.getWriter().write(String.valueOf(isInArea));
                //RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
                //dispatcher.forward(req, resp);
            } else {
                resp.setContentType("text/html");
                //RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/table.html");
                //dispatcher.forward(req, resp);
                PrintWriter out = resp.getWriter();
                out.print("<!DOCTYPE html>\n" +
                        "<html lang=\"en\"\n>");
                out.print("<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                        "    <title>335099_web_1</title>\n" +
                        "    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/table.css\">\n" +
                        "</head>\n");
                out.print("<body>\n" +
                        "<div id=\"main\">\n" +
                        "    <div id=\"status\" class=\"content-class\">\n" +
                        "        <table id=\"status-table\" class=\"header-class\">\n");
                out.print("<tr class=\"status-header\">\n" +
                        "                <th>x</th>\n" +
                        "                <th>y</th>\n" +
                        "                <th>R</th>\n" +
                        "                <th>Execute time (microseconds)</th>\n" +
                        "                <th>Now time</th>\n" +
                        "                <th>In area</th>\n" +
                        "            </tr>\n");


                ResultListBean results = (ResultListBean) req.getSession().getAttribute("results");
                if (results == null) {
                    results = new ResultListBean();
                }
                for (ResultBean result : results.getResults()) {
                    out.print("<tr class=\"status-header\">\n" +
                            "                <th>" + result.getX() + "</th>\n" +
                            "                <th>" + result.getY() + "</th>\n" +
                            "                <th>" + result.getR() + "</th>\n" +
                            "                <th>" + result.getExecuteTime() + "</th>\n" +
                            "                <th>" + result.getNowTime() + "</th>\n" +
                            "                <th>" + result.isInArea() + "</th>\n" +
                            "            </tr>\n");
                }
                out.print("</table>\n" +
                        "        <a href=\"http://localhost:8080/web-lab2/\">\n" +
                        "            Main page\n" +
                        "        </a>\n" +
                        "    </div>\n");
                out.print("</div>\n" +
                        "</body>\n" +
                        "</html>\n");
                out.close();
            }




    }
}
