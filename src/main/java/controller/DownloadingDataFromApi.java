package controller;


import model.Point;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.*;

@RequestScoped
public class DownloadingDataFromApi {

    private final String URLSTART = "https://apiv2.bitcoinaverage.com/indices/global/history/";
    private final String URLEND = "?period=alltime&?format=json";

    public List<Point> downloadDataOfCurrency(String cryptocurrency) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(URLSTART + cryptocurrency + URLEND);
        Response response = target.request().accept(MediaType.APPLICATION_JSON_TYPE).header("X-Testing", "testing").get();
        String data = response.readEntity(String.class);
        response.close();
        ObjectMapper om = new ObjectMapper();
        List<Point> list = om.readValue(data, new TypeReference<List<Point>>() {
        });
        for (Point p : list) {
            p.setTime(p.getTime().substring(0, 10).replace("-", ","));
        }
        return list;
    }

    public List<String>[] growthOrDrop(List<Point> p) {
        List[] l = new List[2];
        Set<String> growth = new HashSet<>();
        Set<String> drop = new HashSet<>();
        for (int i = 0; i < p.size() - 7; i++) {
            if (i % 7 == 0) {
                if (Double.valueOf(p.get(i).getAverage()) < Double.valueOf(p.get(i + 7).getAverage())) {
                    growth.add(p.get(i).getTime());
                } else {
                    drop.add(p.get(i).getTime());
                }
            }
        }
        l[0] = new ArrayList(growth);
        l[1] = new ArrayList(drop);
        return l;
    }

    public List[] listOfTheSameTrend(List<String>[] a, List<String>[] b, List<String>[] c) {
        List[] l = new List[2];
        List<String> growth = new ArrayList<>();
        List<String> drop = new ArrayList<>();
        for (String ax : a[0]) {
            if (b[0].contains(ax) && c[0].contains(ax)) {
                growth.add("Bitcoin/Ethereum/Litcoingrowth Growth - " + ax);
            } else if (b[0].contains(ax)) {
                growth.add("Bitcoin/Ethereum Growth - " + ax);
            } else if (c[0].contains(ax)) {
                growth.add("Bitcoin/Litcoingrowth Growth - " + ax);
            }
        }
        for (String ax : a[1]) {
            if (b[1].contains(ax) && c[1].contains(ax)) {
                drop.add("Bitcoin/Ethereum/Litcoingrowth Drop - " + ax);
            } else if (b[1].contains(ax)) {
                drop.add("Bitcoin/Ethereum Drop - " + ax);
            } else if (c[1].contains(ax)) {
                drop.add("Bitcoin/Litcoingrowth Drop - " + ax);
            }
        }
        l[0]=growth;
        l[1]=drop;
        return l;
    }


}
