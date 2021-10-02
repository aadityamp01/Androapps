package com.example.newsapp.model;

public class NewsData {
    String title;
    String content;
    String imgUrl;
    String articleUrl;

    public NewsData(String title, String content, String imgUrl
    , String articleUrl) {
        this.title = title;
        this.content = content;
        this.imgUrl = imgUrl;
        this.articleUrl = articleUrl;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

}
