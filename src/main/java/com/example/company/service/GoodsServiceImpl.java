package com.example.company.service;

import com.example.company.entity.GoodsEntity;
import com.example.company.model.GoodsDTO;
import com.example.company.model.GoodsInterface;
import com.example.company.repository.GoodsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GoodsServiceImpl implements GoodsService{

    private final GoodsRepository goodsRepository;

    @Override
    public void goodsInsert(GoodsEntity goodsEntity) {
        goodsRepository.save(goodsEntity);
    }

    @Override
    public List<GoodsInterface> goodsOut() {
        return goodsRepository.goodsOut();
    }


}
