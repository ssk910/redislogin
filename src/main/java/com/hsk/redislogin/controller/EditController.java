package com.hsk.redislogin.controller;

import com.hsk.redislogin.model.User;
import com.hsk.redislogin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

@Controller
public class EditController {
    @Autowired
    public UserService userService;

    private Logger logger = Logger.getLogger(this.getClass().getName());
    private User user;

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView showEdit(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("edit");
        user = (User) request.getSession().getAttribute("user");

        // log
        logger.info("[id]\t" + user.getId());
        logger.info("[pw]\t" + user.getPw());
        logger.info("[name]\t" + user.getName());
        logger.info("[email]\t" + user.getEmail());

        return mav;
    }

    @RequestMapping(value = "/editProcess", params = "submit", method = RequestMethod.POST)
    public ModelAndView editUser(HttpServletRequest request, HttpServletResponse response, User user) {
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

    @RequestMapping(value = "/editProcess", params = "cancel", method = RequestMethod.POST)
    public ModelAndView cancel(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = null;

        mav = new ModelAndView("redirect:login");
        return mav;
    }
}
