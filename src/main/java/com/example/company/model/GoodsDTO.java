package com.example.company.model;

import com.example.company.entity.GoodsEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class GoodsDTO {

    private String buydate;
    private String goodsname;
    private int buyqty;
    private String area;
    private String goodimg;
    private int buynum;

    public GoodsEntity toEntity(){
        return GoodsEntity.builder()//builder는 객체 생성
                .buydate(buydate)
                .goodsname(goodsname)
                .buyqty(buyqty)
                .area(area)
                .goodsimg(goodimg)
                .buynum(buynum)
                .build();
    }


}
