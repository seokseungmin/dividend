package zerobase.dividend.scraper;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import zerobase.dividend.model.Company;
import zerobase.dividend.model.Dividend;
import zerobase.dividend.model.ScrapedResult;
import zerobase.dividend.model.constants.Month;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class YahooFinanceScraper implements Scraper {

    private static final String STATISTICS_URL = "https://finance.yahoo.com/quote/%s/history/?frequency=1mo&period1=%d&period2=%d";
    private static final String SUMMARY_URL = "https://finance.yahoo.com/quote/%s?p=%s";
    private static final long START_TIME = 86400; // 60초 * 60분 * 24시간 = 86400

    @Override
    public ScrapedResult scrap(Company company) {
        var scrapResult = new ScrapedResult();
        scrapResult.setCompany(company);

        try {
            // 현재 시간을 밀리세건즈로 받는것. 밀리세건즈에서 초단위로 바꾸기 위해서 1000으로 나눠서 사용.
            long now = System.currentTimeMillis() / 1000;
            String url = String.format(STATISTICS_URL, company.getTicker(), START_TIME, now);
            Connection connection = Jsoup.connect(url);
            Document document = connection.get();

            Elements parsingDivs = document.getElementsByClass("table yf-ewueuo");
            Element tableElement = parsingDivs.get(0); //table 전체

            Element tbody = tableElement.children().get(1); // thead(0) - tbody(1) - tfoot(2)
            List<Dividend> dividends = new ArrayList<>();

            for (Element e : tbody.children()) {
                String txt = e.text();
                if (!txt.endsWith("Dividend")) {
                    continue;
                }

                String[] spilts = txt.split(" ");
                int month = Month.strToNumber(spilts[0]);

                int day = Integer.valueOf(spilts[1].replace(",", ""));
                int year = Integer.valueOf(spilts[2]);
                String dividend = spilts[3];

                if (month < 0) {
                    throw new RuntimeException("Unexpected Month enum value -> " + month);
                }

                dividends.add(new Dividend(LocalDateTime.of(year, month, day, 0, 0, 0), dividend));
            }

            scrapResult.setDividendEntities(dividends);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return scrapResult;
    }

    @Override
    public Company scrapCompanyByTicker(String ticker) {
        String url = String.format(SUMMARY_URL, ticker, ticker);
        try {
            Document document = Jsoup.connect(url).get();
            Element titleElement = document.select("h1.yf-3a2v0c").first();
            String title = titleElement.text().split(" \\(")[0].trim();
            // 3M Company (MMM)
            // 3M Company
            return new Company(ticker, title);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
