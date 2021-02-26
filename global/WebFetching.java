package global;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class WebFetching {
    private String test;

    public WebFetching(){
        final String url = "https://www.calendardate.com/todays.htm";
        try {
            Document doc = Jsoup.connect(url).userAgent("mozzilla/17.0").get();
            int n = 0;
            for(Element row : doc.select("table.todreg tr")){
                n++;
              if(n == 8){
                   test = row.select("tr#indtod > td").text().substring(12);
              }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }  

    public String getDat(){
        return this.test;
    }
}
