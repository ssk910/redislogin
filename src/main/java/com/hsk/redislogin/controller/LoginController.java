package com.hsk.redislogin.controller;

import com.hsk.redislogin.model.Login;
import com.hsk.redislogin.model.User;
import com.hsk.redislogin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

@Controller
public class LoginController {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    UserService userService;

    @RequestMapping(value = "/*", method = RequestMethod.GET) // "/*"로 메인페이지를 설정할 수 있음
    public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("msg", "");
        return mav;
    }

//    @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
//    public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response,
//                                     @ModelAttribute("login") Login login) {

@RequestMapping(value = "/loginProcess", params = "login", method = RequestMethod.POST)
public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response, Login login) {
        ModelAndView mav = null;
        User user = userService.validateUser(login);

        if (null != user) {
            /** 왜 /login/main/으로 redirect 되는지 모르겠음. */
            mav = new ModelAndView("redirect:main");
            mav.addObject("user", user);
            request.getSession().setAttribute("user", user);
        } else {
//            mav = new ModelAndView("redirect:login");
            mav = new ModelAndView("login");
            mav.addObject("msg", "ID 또는 Password가 일치하지 않습니다.");
            // 추후에 id, password 중 뭐가 틀렸는지 처리
        }

        return mav;
    }

    @RequestMapping(value = "/loginProcess", params = "join", method = RequestMethod.POST)
    public ModelAndView goJoin(HttpServletRequest request, HttpServletResponse response, SessionStatus status) {
        ModelAndView mav = null;

        mav = new ModelAndView("redirect:register");
        return mav;
    }
}