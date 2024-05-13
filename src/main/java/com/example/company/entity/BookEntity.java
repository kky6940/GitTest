package com.example.company.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;




@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "book0516")
@SequenceGenerator(
        name="book0516_seq_generator",
        sequenceName = "book0516_seq",
        initialValue = 1,
        allocationSize = 1)
public class BookEntity {

    @Id
    @Column(name = "booknum")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator ="book0516_seq_generator")
    private int booknum;
    @Column(name = "bookname")
    private String bookname;
    @Column(name = "author")
    private String author;
    @Column(name = "bookdate")
    private String bookdate;
    @Column(name = "contents")
    private String contents;
    @Column(name = "price")
    private int price;
    @Column(name = "bookimg")
    private String bookimg;
    @Column(name = "views")
    @ColumnDefault("0")
    private int views;

    @Builder
    public BookEntity(int booknum, String bookname, String author, String bookdate, String contents, int price, String bookimg, int views) {
        this.booknum = booknum;
        this.bookname = bookname;
        this.author = author;
        this.bookdate = bookdate;
        this.contents = contents;
        this.price = price;
        this.bookimg = bookimg;
        this.views = views;
    }

}