package com.mirea.newsapp.models;

import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

//(
//        comment_id serial primary key,
//        person_id bigint unsigned not null,
//        article_id bigint unsigned not null,
//        comment_content text not null,
//        comment_date date not null
//        )

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private int id;

//    @Column(name = "person_id")
//    private int personId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person person;

//    @Column(name = "article_id")
//    private int articleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    @Column(name = "comment_content")
    private String content;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "comment_date")

    private Date date;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public int getPersonId() {
//        return personId;
//    }
//
//    public void setPersonId(int personId) {
//        this.personId = personId;
//    }
//
//    public int getArticleId() {
//        return articleId;
//    }
//
//    public void setArticleId(int articleId) {
//        this.articleId = articleId;
//    }


    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
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

//    @Override
//    public String toString() {
//        return "Comment{" +
//                "id=" + id +
//                ", personId=" + personId +
//                ", articleId=" + articleId +
//                ", content='" + content + '\'' +
//                ", date=" + date +
//                '}';
//    }
}
