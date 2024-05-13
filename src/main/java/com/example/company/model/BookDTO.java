package com.example.company.model;

import com.example.company.entity.BookEntity;
import com.example.company.entity.GoodsEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookDTO {

    private int booknum;
    private String bookname;
    private String author;
    private String bookdate;
    private String contents;
    private int price;
    private String bookimg;
    private int views;


    public BookEntity toEntity(){
        return BookEntity.builder()//builder는 객체 생성
                .booknum(booknum)
                .bookname(bookname)
                .author(author)
                .bookdate(bookdate)
                .contents(contents)
                .price(price)
                .bookimg(bookimg)
                .views(views)
                .build();
    }
}
