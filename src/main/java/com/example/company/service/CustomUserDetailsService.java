package com.example.company.service;

import com.example.company.entity.EmployeeEntity;
import com.example.company.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {//회원정보를 담은 인터페이스를 상속받음

    private final EmployeeRepository employeeRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String id) {//회원정보를 담는

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();//GrantedAuthority: 현재 사용자의 권한 admin or user를 role로 표시(role=역할)

        EmployeeEntity employeeEntity = employeeRepository.findOneById(id);//findOneById의 정보를 가져와서 user에 담음

        if (employeeEntity != null) {
            grantedAuthorities.add(new SimpleGrantedAuthority("USER")); // USER 라는 역할을 넣어준다. SimpleGrantedAuthority는 GrantedAuthority를 상속받은 클래스, role를 USER로 설정(역할을 user로 설정)
            return new User(employeeEntity.getId(), employeeEntity.getPw(), grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("can not find User : " + id);
        }
    }

}