package com.hsk.redislogin.controller;

import com.hsk.redislogin.model.User;
import com.hsk.redislogin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

@Controller
public class RegistrationController {
    @Autowired
    public UserService userService;

    Logger logger = Logger.getLogger(this.getClass().getName());

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("register");
        mav.addObject("user", new User());
        return mav;
    }

    @RequestMapping(value = "/registerProcess", params = "submit", method = RequestMethod.POST)
    public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response, User user) {
        ModelAndView mav;

        if (userService.register(user)) {
            // true
            mav = new ModelAndView("redirect:main");
            mav.addObject("user", user);
            request.getSession().setAttribute("user", user);
            logger.info("register complete");
        } else {
            // false
            mav = new ModelAndView("register");
            logger.info("register failed");
        }

        return mav;
    }

    @RequestMapping(value = "/registerProcess", params = "cancel", method = RequestMethod.POST)
    public ModelAndView cancel(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = null;

        mav = new ModelAndView("redirect:/");
        return mav;
    }
}
