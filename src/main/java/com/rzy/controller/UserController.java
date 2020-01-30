package com.rzy.controller;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rzy.model.Address;
import com.rzy.model.User;
import com.rzy.service.AddressService;
import com.rzy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    //注册页面
    @GetMapping("/regist.html")
    public String registPage(Model model){
        List<Address> addresses = addressService.getAllAddresses();
        model.addAttribute("addresses",addresses);
        return "regist";
    }

    /**
     * 注册功能实现
     * @param session
     * @return
     */

    @PostMapping("/regist.html")
    public String regist(User user,Model model){
        User user1 = userService.regist(user);
        if(user1 == null){
            user.setRegDate(new Date());
            userService.add(user);
            model.addAttribute("loginError","注册成功！");
            return "login";
        }else{
            model.addAttribute("registError","用户已存在，请重新注册！");
            return "regist";
        }
    }

    @GetMapping("/login.html")
    public String loginPage(HttpSession session){
        boolean flag = session.getAttribute("loginUser") == null?true:false;
        if(flag){
            return "login";
        }
        else{
            return "redirect:/main.html";
        }
    }

    //登录
    @PostMapping("/login.html")
    public String login(User user, Model model, HttpSession session){
        User user1 = userService.login(user);
        if(user1 != null){
            session.setAttribute("loginUser",user1);
            return "main";
        }
        else{
            model.addAttribute("loginError","用户名或者密码不匹配！");
            return "login";
        }
    }

    //主页面
    @GetMapping("/main.html")
    public String main(){
        return "main";
    }

    //退出
    @GetMapping("/logout.html")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login.html";
    }

    //用户列表
    @GetMapping("/userlist.html")
    public String userlist(Model model, @RequestParam(value = "pageNum", defaultValue = "0") int pageNum, @RequestParam(value = "pageSize", defaultValue = "8") int pageSize) {
        Page<User> users=userService.getUserList(pageNum, pageSize);
        model.addAttribute("users", users);
        return "userlist";
    }

    @GetMapping("/user.html")
    public String user(Model model,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,6);
        List<User> list = userService.getAllUsers();
        PageInfo<User> pageInfo = new PageInfo<User>(list);
        model.addAttribute("pageInfo",pageInfo);
        return "user";
    }

    @GetMapping("/page.html")
    public String list(Model model, @RequestParam(value = "pageNum", defaultValue = "0") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        Page<User> users=userService.getUserList(pageNum, pageSize);
        model.addAttribute("users", users);
        return "page";
    }

    //模糊查询
    @PostMapping("/searchuser.html")
    public String searchUser(String searchByUsername,Model model){
        List<User> users = userService.getByUsernameLike("%"+searchByUsername+"%");
        model.addAttribute("users",users);
        return "userlist1";
    }

    //跳转到添加user页面
    @GetMapping("/adduser.html")
    public String adduser(Model model){
        List<Address> addresses = addressService.getAllAddresses();
        model.addAttribute("addresses",addresses);
        return "add";
    }

    //添加user
    @PostMapping("/add.html")
    public String add(User user){
        user.setRegDate(new Date());
        userService.add(user);
        return "redirect:/userlist.html";
    }

    //根据id删除
    @GetMapping("/deleteuser.html")
    public String delete(Integer id){
        System.out.println(id);
        userService.delete(id);
        return "redirect:/userlist.html";
    }

    //根据id查询user
    @GetMapping("/updateuser.html")
    public String updateuser(Integer id,Model model){
        System.out.println(id);
        User user = userService.get(id);
        model.addAttribute("user",user);
        List<Address> addresses = addressService.getAllAddresses();
        model.addAttribute("addresses",addresses);
        return "updateuser";
    }

    //得到查询的结果，修改更新
    @PostMapping("/updateuser.html")
    public String update(User user){
        userService.update(user);
        return "redirect:/userlist.html";
    }
}
