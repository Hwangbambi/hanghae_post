package com.example.homework.entity;


import com.example.homework.dto.ArticleRequestDto;
import com.example.homework.dto.ArticleResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Article extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    //private String name;
    private String content;
    //private String password;

    //private String username;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY)
    @OrderBy("id asc")
    private List<Comment> commentList = new ArrayList<>();

    public Article(ArticleRequestDto requestDto , User user){
        this.title = requestDto.getTitle();
        //this.name = requestDto.getName();
        this.content = requestDto.getContent();
        //this.password = requestDto.getPassword();
        this.user = user;
    }
    public void update(ArticleResponseDto responseDto){
        this.title = responseDto.getTitle();
        //this.name = responseDto.getName();
        this.content = responseDto.getContent();

    }
}
