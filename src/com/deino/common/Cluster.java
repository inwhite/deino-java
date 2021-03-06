package com.deino.common;

import org.apache.commons.lang3.StringEscapeUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Inwhite on 4/12/2015.
 */
public class Cluster  extends CPSBase {
    String type;
    String category_id;
    Date first_date;
    Date last_date;
    List<String> article_ids;
    private String id;

    public Cluster() {
        setType("cluster");
        setCategory_id("");
        setFirst_date(new Date());
        setLast_date(new Date());
        setArticle_ids(new ArrayList<String>());
    }

    public void addArticleId(String id){
        article_ids.add(id);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public Date getFirst_date() {
        return first_date;
    }

    public void setFirst_date(Date first_date) {
        this.first_date = first_date;
    }

    public Date getLast_date() {
        return last_date;
    }

    public void setLast_date(Date last_date) {
        this.last_date = last_date;
    }

    public Integer getSize() {
        return getArticle_ids().size();
    }

    public List<String> getArticle_ids() {
        return article_ids;
    }

    public void setArticle_ids(List<String> article_ids) {
        this.article_ids = article_ids;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toXML() {
        StringBuilder s = new StringBuilder();
        s.append(String.format(
                "<document>" +
                        "<"+ID+">%s</"+ID+">" +
                        "<"+CPSBase.TYPE+">cluster</"+CPSBase.TYPE+">" +
                        "<"+CAT_ID+">%s</"+CAT_ID+">" +
                        "<"+FIRST_DATE+">%s</"+FIRST_DATE+">" +
                        "<"+LAST_DATE+">%s</"+LAST_DATE+">" +
                        "<size>%s</size>",
                StringEscapeUtils.escapeXml10(id),
                StringEscapeUtils.escapeXml10(category_id),
                StringEscapeUtils.escapeXml10(CPSBase.dateFormat.format(first_date)),
                StringEscapeUtils.escapeXml10(CPSBase.dateFormat.format(last_date)),
                StringEscapeUtils.escapeXml10(String.valueOf(article_ids.size()))
        ));

        if (article_ids != null) {
            for (String aid : article_ids) {
                s.append(String.format("<"+ARTICLE+">%s</"+ARTICLE+">",
                        StringEscapeUtils.escapeXml10(aid)));
            }
        }
        s.append("</document>");

        return s.toString();
    }
}
