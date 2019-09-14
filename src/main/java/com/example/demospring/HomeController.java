package com.example.demospring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home(){
        return "index.jsp";
    }

    @RequestMapping("add")
    public String add(HttpServletRequest req ){
        int i1 = Integer.parseInt(req.getParameter("num1"));
        int i2 = Integer.parseInt(req.getParameter("num2"));
        int num3 = i1 + i2;

        HttpSession session = req.getSession();
        session.setAttribute("num3",num3);
        return "result.jsp";
    }
}
