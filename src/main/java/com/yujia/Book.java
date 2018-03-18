package com.yujia;

public class Book implements Comparable{
    private String name;
    private Double score;
    private Integer commentCount;
    private String author;
    private String publishingHouse;
    private String date;
    private String price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    public Book(String name,Double score,Integer commentCount,String author,String publishingHouse,String date,String price){
        this.name=name;
        this.score=score;
        this.commentCount=commentCount;
        this.author=author;
        this.publishingHouse=publishingHouse;
        this.date=date;
        this.price=price;
    }
    public int compareTo(Object o) {
        if((this.score-((Book)o).score)<0){
            return -1;
        }
        else if((this.score-((Book)o).score)==0){
            return 0;
        }
        else
            return 1;
    }

}
