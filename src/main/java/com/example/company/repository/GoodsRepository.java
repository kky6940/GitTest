package com.example.company.repository;

import com.example.company.entity.EmployeeEntity;
import com.example.company.entity.GoodsEntity;
import com.example.company.model.EmployeeDtoInterface;
import com.example.company.model.GoodsInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GoodsRepository extends JpaRepository<GoodsEntity, Integer> {

    @Query(value = "select buynum, buydate, goodsname, buyqty, area, goodsimg, " +
            "case when buydate < sysdate - interval '1' year then '폐기' " +
            "else '판매 중' end as goodsstate " +
            "from goods", nativeQuery = true)
    List<GoodsInterface> goodsOut();
}
