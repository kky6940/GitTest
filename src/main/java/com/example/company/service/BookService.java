package com.example.company.service;

import com.example.company.entity.BookEntity;

import java.util.List;

public interface BookService {

    public void bookInsert(BookEntity bookEntity);

    public List<BookEntity> bookOut();

    BookEntity bookDetail(int booknum);

    public int updateViews(int booknum);
}
