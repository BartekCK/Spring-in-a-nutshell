package com.example.demospring;

import com.example.demospring.model.Alien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@Controller
public class HomeController {

    @Autowired
    AlienRepo repo;

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
        m.addAttribute("result",repo.findAll());
        return "showAliens";
    }

    @GetMapping("getAlien")
    public String getAlien(@RequestParam int id, Model m){
        m.addAttribute("result",repo.getOne(id));
        return "showAliens";
    }

    @PostMapping("addAlien")
    public String addAlien(@ModelAttribute("a1") Alien alien){
        repo.save(alien);
        return "result";
    }
}
