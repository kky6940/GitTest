package com.example.company.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "goods")
@SequenceGenerator(
        name="goods_seq_generator",
        sequenceName = "goods_seq",
        initialValue = 1,
        allocationSize = 1)
public class GoodsEntity {

    @Column(name = "buydate")
    private String buydate;

    @Column(name = "goodsname")
    private String goodsname;

    @Column(name = "buyqty")
    private int buyqty;

    @Column(name = "area")
    private String area;

    @Column(name = "goodsimg")
    private String goodsimg;

    @Id
    @Column(name = "buynum")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator ="goods_seq_generator")
    private int buynum;

    @Builder
    public GoodsEntity(String buydate, String goodsname, int buyqty, String area, String goodsimg, int buynum) {
        this.buydate = buydate;
        this.goodsname = goodsname;
        this.buyqty = buyqty;
        this.area = area;
        this.goodsimg = goodsimg;
        this.buynum = buynum;

    }

}