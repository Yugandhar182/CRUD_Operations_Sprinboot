package com.springboot.crud.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.crud.entity.Studentdata;

@Repository
public interface StudentdataRepository extends JpaRepository<Studentdata ,Integer>{


}
