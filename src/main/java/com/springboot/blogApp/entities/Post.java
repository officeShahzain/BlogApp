package com.springboot.blogApp.entities;

import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.OneToMany;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString

public class Post {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;
    private String title;
    private String content;
    private String image;

    //Create date annotation
    private Date addDate;

    @ManyToOne
    private User user;
    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Comments> comments = new HashSet<>();
}
