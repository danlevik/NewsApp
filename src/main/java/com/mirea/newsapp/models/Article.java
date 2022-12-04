package com.mirea.newsapp.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

//(
//        article_id serial primary key,
//        tag_id bigint unsigned not null,
//        author_id bigint unsigned not null,
//        article_title varchar(100) not null,
//        article_content text not null,
//        article_date date not null,
//        picture_link varchar(256),
//        foreign key (author_id) references person (person_id) on delete cascade,
//        foreign key (tag_id) references tag (tag_id) on delete cascade
//        )


@Entity
@Table(name = "article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private int id;

    @Column(name = "tag_id")
    private int tagId;

    @Column(name = "author_id")
    private int authorId;

    @Column(name = "article_title")
    private String title;

    @Column(name = "article_content")
    private String content;

//    ???
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "article_date")
    private Date date;

    @Column(name = "picture_link")
    private String pictureLink;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPictureLink() {
        return pictureLink;
    }

    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", tagId=" + tagId +
                ", authorId=" + authorId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", pictureLink='" + pictureLink + '\'' +
                '}';
    }
}
