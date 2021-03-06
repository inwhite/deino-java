package com.deino.article_reader;

import com.deino.common.CPSBase;
import org.apache.commons.lang3.StringEscapeUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Inwhite on 07.04.2015..
 */
public class Article extends CPSBase {

    public static String URLtoID(String URL) {
        return URL.replaceAll("\\W", "");
    }

    private Date publication_date;
    private String title;
    private String description;
    private String predefined_category;
    private String URL;
    private String category;
    private String img_url;
    private String source;
    private String id;
    private String type;
    private String text;
    private String cluster = "-1";
    private HashMap<String, Double> keywords;


    public Article() {
        title = description = predefined_category = URL = img_url = source = id = category = "";
        publication_date = new Date();
        type = "article";
    }

    public Date getPublication_date() {
        return publication_date;
    }

    public void setPublication_date(String publication_date) throws ParseException {
        DateFormat format = new SimpleDateFormat("EEE, d MMM yyyy H:m:s", Locale.ENGLISH);
        Date date = format.parse(publication_date);
        this.publication_date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPredefineCategory() {
        return predefined_category;
    }

    public void setPredefinedCategory(String predefined_category) {
        this.predefined_category = predefined_category;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
        id = URLtoID(URL);
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setPublication_date(Date publication_date) {
        this.publication_date = publication_date;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getId() {
        return id;
    }

    public HashMap<String, Double> getKeywords() {
        return keywords;
    }

    public void setKeywords(HashMap<String, Double> keywords) {
        this.keywords = keywords;
    }

    public String toXML() {
        StringBuilder s = new StringBuilder();
        s.append(String.format(
                "<document>" +
                        "<" + ID + ">%s</" + ID + ">" +
                        "<"+CPSBase.TYPE+">article</"+CPSBase.TYPE+">" +
                        "<" + DATE + ">%s</" + DATE + ">" +
                        "<" + TITLE + ">%s</" + TITLE + ">" +
                        "<" + DESCRIPTION + ">%s</" + DESCRIPTION + ">" +
                        "<" + CAT_ID + ">%s</" + CAT_ID + ">" +
                        "<" + CPSBase.URL + ">%s</" + CPSBase.URL + ">" +
                        "<" + PRED_CAT + ">%s</" + PRED_CAT + ">" +
                        "<" + CPSBase.IMG_URL + ">%s</"+CPSBase.IMG_URL+">" +
                        "<" + SRC + ">%s</" + SRC + ">" +
                        "<" + CLUST_ID + ">%s</" + CLUST_ID + ">",
                StringEscapeUtils.escapeXml10(id),
                StringEscapeUtils.escapeXml10(dateFormat.format(publication_date)),
                StringEscapeUtils.escapeXml10(title),
                StringEscapeUtils.escapeXml10(description),
                StringEscapeUtils.escapeXml10(category),
                StringEscapeUtils.escapeXml10(URL),
                StringEscapeUtils.escapeXml10(predefined_category),
                StringEscapeUtils.escapeXml10(img_url),
                StringEscapeUtils.escapeXml10(source),
                StringEscapeUtils.escapeXml10(cluster)
        ));

        s.append("<tokens>");
        if (keywords != null) {
            for (Map.Entry<String, Double> entry : keywords.entrySet()) {
                s.append(String.format("<" + TOKEN + " " + FREQ + "=\"%s\">%s</" + TOKEN + ">",
                        Double.toString(entry.getValue()),
                        StringEscapeUtils.escapeXml10(entry.getKey())));
            }
        }
        s.append("</tokens></document>");

        return s.toString();
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

