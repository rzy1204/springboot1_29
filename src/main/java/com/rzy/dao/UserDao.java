package com.rzy.dao;

import com.rzy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDao extends JpaRepository<User,Integer> {
    /**
     * 根据username查询，得到用户user
     * @param username
     * @return
     */
    User getUserByUsername(String username);

    /**
     * 模糊查询
     * @param searchByUsername
     * @return
     */
    List<User> getByUsernameLike(String searchByUsername);


}
