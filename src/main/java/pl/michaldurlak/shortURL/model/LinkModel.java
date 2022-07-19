package pl.michaldurlak.shortURL.model;

import java.util.UUID;

public class LinkModel {

    private String originalLink;
    private String shortLink;
    private int dateEnd;
    private int timeEnd;


    public LinkModel(String originalLink, int dateEnd, int timeEnd) {
        this.originalLink = originalLink;
        this.dateEnd = dateEnd;
        this.timeEnd = timeEnd;

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

    @Override
    public String toString() {
        return "LinkModel{" +
                "originalLink='" + originalLink + '\'' +
                ", shortLink='" + shortLink + '\'' +
                ", dateEnd=" + dateEnd +
                ", timeEnd=" + timeEnd +
                '}';
    }

    private String generateShortLink(){
        UUID randomUUID = UUID.randomUUID();
        String key = randomUUID.toString().replaceAll("_", "");
        return key.substring(key.length()-4);
    }
}
