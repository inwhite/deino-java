package com.deino;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.HashMap;

/**
 * Created by Inwhite on 08.04.2015..
 */
public class TvnetParser extends RSSFeedParser {
    private static final String FEED_URL = "http://www.delfi.lv/rss.php";

    public TvnetParser() {
        super(FEED_URL);
        messages = new HashMap<String, Article>();
        category_urls.put("local", "http://feeds.tvnet.lv/tvnet/zinas/latest?format=xml");
        category_urls.put("business", "http://feeds.tvnet.lv/tvnet/financenet/latest?format=xml");
        category_urls.put("sport", "http://feeds.tvnet.lv/tvnet/sports/latest?format=xml");
        category_urls.put("culture", "http://feeds.tvnet.lv/tvnet/izklaide/latest?format=xml");
        category_urls.put("car", "http://feeds.tvnet.lv/tvnet/auto/latest?format=xml");
        category_urls.put("technology", "http://feeds.tvnet.lv/tvnet/tehnologijas/latest?format=xml");
        category_urls.put("entertainment", "http://feeds.tvnet.lv/tvnet/izklaide/latest?format=xml");
    }

    @Override
    public boolean readFeed() {
        try {
            URLConnection urlConnection = url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(in);

            NodeList items = doc.getElementsByTagName("item");

            for (int i = 0; i < items.getLength(); i++) {
                Element item = (Element) items.item(i);
                Article art = new Article();
                art.setTitle(getValue(item, TITLE));
                String raw_description = getValue(item, DESCRIPTION,1);
                HTMLParser htmlParser = new HTMLParser(raw_description);
                art.setDescription(htmlParser.getText());
                art.setImg_url(htmlParser.getLastImgURL());

                art.setPublication_date(getValue(item, PUB_DATE));
                art.setPredefined_category(getUrl_category());
                art.setURL(getValue(item, LINK));
                art.setSource("TVNET");
                addMessage(art);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

}