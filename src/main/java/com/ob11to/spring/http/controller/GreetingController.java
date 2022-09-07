package com.ob11to.spring.http.controller;

import com.ob11to.spring.database.repository.CompanyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/api/v1")
public class GreetingController {

    @GetMapping("/hello/{id}")
//    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ModelAndView hello(ModelAndView modelAndView,
                              HttpServletRequest request,
                              CompanyRepository companyRepository,
                              @RequestParam Integer age,
                              @RequestHeader String accept,
                              @CookieValue("JSESSIONID") String JSESSIONID,
                              @PathVariable("id") Integer id) {
        var ageParam = request.getParameter("age");
        var acceptHeader = request.getHeader("accept");
        var cookies = request.getCookies();
        modelAndView.setViewName("greeting/hello");

        return modelAndView;
    }

    @GetMapping("/bye")
    public ModelAndView bye() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("greeting/bye");

        return modelAndView;
    }

}
