package com.example.company.controller;

import com.example.company.entity.GoodsEntity;
import com.example.company.model.EmployeeDTO;
import com.example.company.model.EmployeeDtoInterface;
import com.example.company.model.GoodsDTO;
import com.example.company.model.GoodsInterface;
import com.example.company.service.EmployeeService;
import com.example.company.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class GoodsController {

    static final String CURRENT_IMAGE_REPO_PATH = "C:\\이젠디지탈12\\spring_boot\\company\\src\\main\\resources\\static\\goodsImg";

    @Autowired
    GoodsService goodsService;

    @GetMapping("/goodsInsert")
    public String employeeInsertPage(Model model){
        model.addAttribute("goodsDTO",new GoodsDTO());
        return "goods/goodsInsert";
    }

    @PostMapping("/goodsInsert")
    public String employeeInsert(@ModelAttribute("goodsDTO") GoodsDTO goodsDTO, MultipartHttpServletRequest request) throws IOException {
        MultipartFile mf = request.getFile("file");
        String goodsimg = mf.getOriginalFilename();
        goodsDTO.setGoodimg(goodsimg);
        mf.transferTo(new File(CURRENT_IMAGE_REPO_PATH+"\\"+goodsimg));
        GoodsEntity goodsEntity = goodsDTO.toEntity();
        goodsService.goodsInsert(goodsEntity);
        return "redirect:/goodsInsert";
    }

    @GetMapping("/goodsOut")
    public String employeeOutPage(Model model){
        List<GoodsInterface> list = goodsService.goodsOut();
        model.addAttribute("list",list);
        return "goods/goodsOut";
    }

    @GetMapping("/goodsSortOutS")
    public String goodsSortOutS(Model model){
        List<GoodsInterface> list = goodsService.goodsOut();
        model.addAttribute("list",list);
        return "goods/goodsSortOutS";
    }
}
