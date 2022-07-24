package pl.michaldurlak.shortURL.model;



import org.json.JSONObject;

import java.util.Map;
import java.util.UUID;

public class LinkModel {

    private final String originalLink;
    private final String shortLink;
    private final int dateEnd;
    private final int timeEnd;




    public LinkModel(String originalLink, String dateEnd, String timeEnd) {
        this.originalLink = originalLink;
        this.dateEnd = Integer.parseInt(dateEnd.replaceAll("-",""));
        this.timeEnd = Integer.parseInt(timeEnd.replaceAll(":",""));
        this.shortLink = generateShortLink();
    }

    public String getOriginalLink() {
        return originalLink;
    }

    public String getShortLink() {
        return shortLink;
    }

    public int getDateEnd() {
        return dateEnd;
    }

    public int getTimeEnd() {
        return timeEnd;
    }

//    @Override
//    public String toString() {
//        return "LinkModel{" +
//                "originalLink='" + originalLink + '\'' +
//                ", shortLink='" + shortLink + '\'' +
//                ", dateEnd=" + dateEnd +
//                ", timeEnd=" + timeEnd +
//                '}';
//    }


    @Override
    public String toString() {
        JSONObject result = new JSONObject();
        result.put("originalLink", originalLink);
        result.put("shortLink",shortLink);
        result.put("dateEnd",dateEnd);
        result.put("timeEnd",timeEnd);
        return result.toString();
    }

    private String generateShortLink(){
        UUID randomUUID = UUID.randomUUID();
        String key = randomUUID.toString().replaceAll("_", "");
        return key.substring(key.length()-4);
    }
}
