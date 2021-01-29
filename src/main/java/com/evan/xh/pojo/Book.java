package com.evan.xh.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "book")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    // 实际上是把 category 对象的 id 属性作为 cid 进行了查询
    @ManyToOne
    @JoinColumn(name = "cid")
    private Category category;
    String cover;
    String title;
    String author;
    String date;
    String press;
    String abs;

    public int getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public String getCover() {
        return cover;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDate() {
        return date;
    }

    public String getPress() {
        return press;
    }

    public String getAbs() {
        return abs;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public void setAbs(String abs) {
        this.abs = abs;
    }
}
