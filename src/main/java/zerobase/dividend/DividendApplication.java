package zerobase.dividend;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class DividendApplication {

    public static void main(String[] args) {
        //SpringApplication.run(DividendApplication.class, args);

        try {
            Connection connection = Jsoup.connect("https://finance.yahoo.com/quote/KO/history/?frequency=1mo&period1=-252322200&period2=1721908949");
            Document document = connection.get();

            Elements elementsByClass = document.getElementsByClass("table yf-ewueuo");
            Element element = elementsByClass.get(0); //table 전체

            Element tbody = element.children().get(1); // thead(0) - tbody(1) - tfoot(2)

            for(Element e : tbody.children()) {
                String txt = e.text();
                if(!txt.endsWith("Dividend")) {
                    continue;
                }

                String[] spilts = txt.split(" ");
                String month = spilts[0];
                int day = Integer.valueOf(spilts[1].replace(",",""));
                int year = Integer.valueOf(spilts[2]);
                String dividend = spilts[3];

                System.out.println(month + " " + day + " " + year + " " + dividend);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
