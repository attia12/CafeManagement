package com.inn.cafe2.com.inn.cafe2.dao;

import com.inn.cafe2.com.inn.cafe2.models.User;
import com.inn.cafe2.com.inn.cafe2.wrapper.UserWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface UserDAO extends JpaRepository<User,Integer> {
    User findByEmailId(@Param("email") String email);
    List<UserWrapper>getAllUser();
    //wherever you update query put this annotation
    @Transactional
    @Modifying
    Integer updateStatus(@Param("status") String status,@Param("id") Integer id );
}
