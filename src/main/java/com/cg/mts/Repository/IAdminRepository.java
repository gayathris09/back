package com.cg.mts.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.mts.entities.*;

@Repository
public interface IAdminRepository extends JpaRepository<Admin, Integer> {

}
