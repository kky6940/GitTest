package com.example.company.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;



@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "employee")
public class EmployeeEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "pw")
    private String pw;

    @Column(name = "name")
    private String name;

    @Column(name = "tel")
    private String tel;

    @Column(name = "address")
    private String address;

    @Column(name = "jumin")
    private String jumin;

    @Column(name = "hobby")
    private String hobby;

    @Builder
    public EmployeeEntity(String id, String pw, String name, String tel, String address, String jumin, String hobby) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.tel = tel;
        this.address = address;
        this.jumin = jumin;
        this.hobby = hobby;
    }

}