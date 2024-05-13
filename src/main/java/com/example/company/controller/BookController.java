package com.example.company.controller;

import com.example.company.entity.BookEntity;
import com.example.company.entity.GoodsEntity;
import com.example.company.model.BookDTO;
import com.example.company.service.BookService;
import com.example.company.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class BookController {

    static final String CURRENT_IMAGE_REPO_PATH = "C:\\이젠디지탈12\\spring_boot\\company\\src\\main\\resources\\static\\bookImg";

    @Autowired
    BookService bookService;

    @GetMapping("/bookInsert")
    public String bookInsertPage(Model model){
        model.addAttribute("bookDTO",new BookDTO());
        return "book/bookInsert";
    }

    @PostMapping("/bookInsert")
    public String bookInsert(@ModelAttribute("bookDTO") BookDTO bookDTO, MultipartHttpServletRequest request) throws IOException {
        MultipartFile mf = request.getFile("file");
        String bookimg = mf.getOriginalFilename();
        bookDTO.setBookimg(bookimg);
        mf.transferTo(new File(CURRENT_IMAGE_REPO_PATH+"\\"+bookimg));
        BookEntity bookEntity = bookDTO.toEntity();
        bookService.bookInsert(bookEntity);
        return "redirect:/bookInsert";
    }

    @GetMapping("/bookOut")
    public String bookOutPage(Model model){
        List<BookEntity> list = bookService.bookOut();
        model.addAttribute("list",list);
        return "book/bookOut";
    }

    @GetMapping("/bookDetail")
    public String bookDetailPage(@RequestParam("booknum") int booknum, Model model){
        BookEntity book = bookService.bookDetail(booknum);
        bookService.updateViews(booknum);
        model.addAttribute("book",book);
        return "book/bookDetail";
    }
}
