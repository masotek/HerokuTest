package servlets;

import controller.TemplateProvider;
import controller.DownloadingDataFromApi;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import model.Point;


import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet("/chart")
public class ChartServlet extends HttpServlet{
    @Inject
    DownloadingDataFromApi downloadingDataFromApi;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        List listOfBTC = downloadingDataFromApi.downloadDataOfCurrency("BTCUSD");
        List listOfETH = downloadingDataFromApi.downloadDataOfCurrency("ETHUSD");
        List listOfLTC = downloadingDataFromApi.downloadDataOfCurrency("LTCUSD");
        List[] b = downloadingDataFromApi.growthOrDrop(listOfBTC);
        List[] e = downloadingDataFromApi.growthOrDrop(listOfETH);
        List[] l = downloadingDataFromApi.growthOrDrop(listOfLTC);
List[] trendList = downloadingDataFromApi.listOfTheSameTrend(b, e, l);
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("listOfBTC", listOfBTC);
        dataModel.put("listOfETH", listOfETH);
        dataModel.put("listOfLTC", listOfLTC);
        dataModel.put("listOfGrowth", trendList[0]);
        dataModel.put("listOfDrop", trendList[1]);
        Template template = TemplateProvider.createTemplate(getServletContext(), "chart.ftlh");
        try {
            template.process(dataModel, writer);
        } catch (TemplateException ee) {
         writer.print("There is a problem with freemarker template");
        }
    }
}
