package com.example.company.model;

import com.example.company.entity.EmployeeEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class EmployeeDTO {

    @NotBlank(message = "아이디는 필수 입력입니다.")
    private String id;
    private String pw;
    private String name;
    private String tel;
    private String address;
    private String jumin;
    private String hobby;

    public EmployeeEntity toEntity(){
        return EmployeeEntity.builder()//builder는 객체 생성
                .id(id)
                .pw(pw)
                .name(name)
                .tel(tel)
                .address(address)
                .jumin(jumin)
                .hobby(hobby)
                .build();
    }
}
