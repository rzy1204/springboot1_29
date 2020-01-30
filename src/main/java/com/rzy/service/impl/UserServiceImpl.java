package com.rzy.service.impl;

import com.rzy.dao.UserDao;
import com.rzy.model.User;
import com.rzy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     *
     * @param 保存user
     */
    @Override
    public void add(User user) {
        userDao.save(user);
    }

    /**
     *
     * @param 根据id删除
     */
    @Override
    public void delete(Integer id) {
        userDao.deleteById(id);
    }

    /**
     *
     * @param 更新user
     */
    @Override
    public void update(User user) {
        userDao.saveAndFlush(user);
    }

    /**
     *
     * @param 根据id获取用户user
     * @return
     */
    @Override
    public User get(Integer id) {
        return userDao.getOne(id);
    }

    /**
     *
     * @return 获取用户列表
     */
    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    public User regist(User user) {
        User user1 = userDao.getUserByUsername(user.getUsername());
        if(user1 != null){
            return user1;
        }
        return null;
    }

    /**
     *
     * @param user登录
     * @return
     */
    @Override
    public User login(User user) {
        User user1 = userDao.getUserByUsername(user.getUsername());
        if(user1 == null){
            return null;
        }
        if(!user1.getPassword().equals(user.getPassword())){
            return null;
        }
        return user1;
    }

    /**
     *
     * @param 根据searchByUsername模糊查询
     * @return
     */

    @Override
    public List<User> getByUsernameLike(String searchByUsername) {
        return userDao.getByUsernameLike(searchByUsername);
    }


    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Page<User> getUserList(int pageNum, int pageSize) {
        Sort.Order order=new Sort.Order(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(order));
        Page<User> users = userDao.findAll(pageable);
        return users;
    }
}
