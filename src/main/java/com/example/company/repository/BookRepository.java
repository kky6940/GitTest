package com.example.company.repository;

import com.example.company.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {
    @Transactional
    @Modifying
    @Query(value = "update book0516 b set b.views = b.views + 1 where b.booknum = :booknum", nativeQuery = true)
    int updateView(@Param("booknum") int booknum);
}
