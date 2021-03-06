package com.wafer.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wafer.domain.User;
import com.wafer.security.domain.SysUser;
import com.wafer.service.UserService;
import com.wafer.vo.PasswordVo;
import com.wafer.vo.ResponseResult;

@Controller
public class AuthController {

  @Autowired
  UserService userService;

  @RequestMapping(value = "/")
  public ModelAndView indexView() {
    SysUser principal =
        (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String userName = "";
    int userRole = 1;
    if (principal instanceof SysUser) {
      userName = principal.getName();
      userRole = principal.getUserAuthority();
    }
    ModelAndView view = new ModelAndView();
    view.setViewName("index");
    view.addObject("userName", userName);
    view.addObject("userRole", userRole);
    return view;
  }

  @RequestMapping(value = "/login")
  public String loginView() {
    return "login";
  }

  @RequestMapping(value = "/changepwd", method = RequestMethod.POST)
  @ResponseBody
  public ResponseResult passwordModify(PasswordVo passwordVo) {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String userName = "";
    if (principal instanceof SysUser) {
      userName = ((SysUser) principal).getUsername();
    }
    User user = userService.findByUserName(userName);
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    boolean flag = encoder.matches(passwordVo.getOldPassword(), user.getPassword());
    if (flag) {
      user.setPassword(encoder.encode(passwordVo.getNewPassword()));
      user.setUpdateTime(new Date());
      userService.userSave(user);
      return ResponseResult.success(user);
    } else {
      return ResponseResult.failure("原密码输入错误！");
    }
  }
}
