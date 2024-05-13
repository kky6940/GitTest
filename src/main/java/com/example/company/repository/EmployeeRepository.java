package com.example.company.repository;

import com.example.company.entity.EmployeeEntity;
import com.example.company.model.EmployeeDTO;
import com.example.company.model.EmployeeDtoInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, String> {

    @Query(value =  "select id, name, tel, address, hobby, " +
                        "TO_NUMBER(TO_CHAR(SYSDATE, 'YYYY'))-TO_NUMBER( " +
                        "CASE WHEN SUBSTR(jumin,8,1) IN('1','2','5','6') THEN '19' " +
                             "WHEN SUBSTR(jumin,8,1) IN('3','4','7','8') THEN '20' " +
                        "END || substr(jumin,1,2))+1 as age, " +
                        "case when SUBSTR(jumin,8,1) in ('1','3') then '남자' " +
                             "when SUBSTR(jumin,8,1) in ('2','4') then '여자' " +
                        "end as sb " +
                    "from employee ", nativeQuery = true)
    public List<EmployeeDtoInterface> empOut();

    public EmployeeEntity findOneById(String id);
}
