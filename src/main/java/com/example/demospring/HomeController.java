package com.example.demospring;

import com.example.demospring.model.Alien;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HomeController {

    @ModelAttribute
    public void modelData(Model m){
        m.addAttribute("name","Aliens");
    }

    @RequestMapping("/")
    public String home(){
        return "index";
    }

    @RequestMapping("add")
    public String add(@RequestParam("num1") int i, @RequestParam("num2")int j, ModelMap m){

        m.addAttribute("num3",i+j);
        return "result";
    }

    @PostMapping("addAlien")
    public String addAlien(@ModelAttribute("a1") Alien alien){
        return "result";
    }
}
