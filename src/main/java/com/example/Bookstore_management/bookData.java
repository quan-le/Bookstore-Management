package com.example.Bookstore_management;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.sql.Date;
import java.time.LocalDate;

public class bookData {

    private Integer bookId;
    private String title;
    private String author;
    private String genre;
    private Date pubDate;
    private Double price;
    private String image;

    public bookData(Integer bookId, String title, String author,
                    String genre, Date pubDate, Double price, String image){
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.pubDate = pubDate;
        this.price = price;
        this.image = image;
    }

    public Integer getBookId(){
        return bookId;
    }

    public String getTitle(){
        return title;
    }

    public String getAuthor(){
        return author;
    }

    public String getGenre(){
        return genre;
    }

    public Date getPubDate(){
        return pubDate;
    }

    public Double getPrice(){
        return price;
    }

    public String getImage() {
        return image;
    }
}
