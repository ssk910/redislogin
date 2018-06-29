package com.hsk.redislogin.controller;

import com.hsk.redislogin.model.User;
import com.hsk.redislogin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class MainController {
    @Autowired
    UserService userService;

    private Logger logger = Logger.getLogger(this.getClass().getName());
    private User user;

    @RequestMapping(value = "main", method = RequestMethod.GET)
    public ModelAndView showMain(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("main");
//        boolean isUpdated = (boolean) request.getAttribute("isUpdated");
        user = (User) request.getSession().getAttribute("user");


        // log
        logger.info("[id]\t" + user.getId());
        logger.info("[pw]\t" + user.getPw());
        logger.info("[name]\t" + user.getName());
        logger.info("[email]\t" + user.getEmail());

        return mav;
    }

    @RequestMapping(value = "/mainProcess", params = "edit", method = RequestMethod.POST)
    public ModelAndView editUser(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("redirect:edit");
        request.getSession().setAttribute("user", user);

        return mav;
    }

    @RequestMapping(value = "/mainProcess", params = "logout", method = RequestMethod.POST)
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("redirect:/");
        request.getSession().invalidate();
        logger.log(Level.INFO, "logged out");

        return mav;
    }

    @RequestMapping(value = "/mainProcess", params = "delete", method = RequestMethod.POST)
    public ModelAndView deleteUser(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = null;

        if (userService.deleteUser(user)) {
            // true
            mav = new ModelAndView("redirect:/");
            request.getSession().invalidate();
            logger.log(Level.INFO, "logged out");
        } else {
            // false
            mav = new ModelAndView("main");
        }

        return mav;
    }
}


