package com.example.company.service;

import com.example.company.entity.BookEntity;
import com.example.company.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    @Override
    public void bookInsert(BookEntity bookEntity) {
        bookRepository.save(bookEntity);
    }

    @Override
    public List<BookEntity> bookOut() {
        return bookRepository.findAll();
    }

    @Override
    public BookEntity bookDetail(int booknum) {
        return bookRepository.findById(booknum).orElse(null);
    }

    @Override
    public int updateViews(int booknum) {
        return bookRepository.updateView(booknum);
    }
}
