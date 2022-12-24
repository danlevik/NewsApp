package com.mirea.newsapp.models;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tag")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private int id;

    @Column(name = "tag_name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    private List<Article> articleList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    @Override
//    public String toString() {
//        return "Tag{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                '}';
//    }
}
