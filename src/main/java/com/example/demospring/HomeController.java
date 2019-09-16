package com.example.demospring;

import com.example.demospring.model.Alien;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


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

    @GetMapping("getAliens")
    public String getAliens(Model m){
        List <Alien> aliens = Arrays.asList(new Alien(101,"a"), new Alien(102,"b"));
        m.addAttribute("result",aliens);
        return "showAliens";
    }

    @PostMapping("addAlien")
    public String addAlien(@ModelAttribute("a1") Alien alien){
        return "result";
    }
}
