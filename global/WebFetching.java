package global;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Klassen används för att hämta dagens datum från en hemsida.
 * @author Simon Länsberg
 * @version 2021-02-26
 */
public class WebFetching {
    private String test;

    public WebFetching() {
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

    /**
     * @return Dagens datum.
     */
    public String getDat(){
        return this.test;
    }
}
