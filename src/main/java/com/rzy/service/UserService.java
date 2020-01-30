

package com.rzy.service;

import com.rzy.model.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    public void add(User user);

    public void delete(Integer id);

    public void update(User user);

    public User get(Integer id);

    public List<User> getAllUsers();

    public User regist(User user);

    public User login(User user);

    public List<User> getByUsernameLike(String searchByUsername);

    Page<User> getUserList(int pageNum,int pageSize);
}
