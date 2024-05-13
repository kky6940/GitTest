package com.example.company.service;

import com.example.company.entity.GoodsEntity;
import com.example.company.model.GoodsDTO;
import com.example.company.model.GoodsInterface;

import java.util.List;

public interface GoodsService {

    public void goodsInsert(GoodsEntity goodsEntity);


    List<GoodsInterface> goodsOut();
}
