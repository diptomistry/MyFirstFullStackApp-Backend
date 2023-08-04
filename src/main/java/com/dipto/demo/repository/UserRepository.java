package com.dipto.demo.repository;

import com.dipto.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
//Long is the datatype of the prmary key
public interface UserRepository extends JpaRepository<User,Long> {//to see the methods ,japarepo will give hold cmd and click on jpaRepository
}
