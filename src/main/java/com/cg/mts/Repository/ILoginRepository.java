package com.cg.mts.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.mts.entities.Login;



/*
 * Created By Titas Sarkar
 */
@Repository
public interface ILoginRepository extends JpaRepository<Login, Integer> {

}
