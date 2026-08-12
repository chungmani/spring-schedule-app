package com.example.springscheduleapp.entity;

import com.example.springscheduleapp.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", length = 30)
    private String title;
    @Column(name = "content", length = 200)
    private String content;
    @Column(name = "author", length = 20)
    private String author;

    private String password;

    public Schedule(String title, String content, String author, String password) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.password = password;
    }

    public void updateSchedule(String title, String author) {
        this.title = title;
        this.author = author;
    }

}
