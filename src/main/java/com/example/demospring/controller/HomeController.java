package com.example.demospring.controller;

import com.example.demospring.reposiotry.AlienRepository;
import com.example.demospring.model.Alien;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class HomeController {


    private AlienRepository repo;

    public HomeController(AlienRepository repo) {
        this.repo = repo;
    }

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

    @GetMapping("getAlienByName")
    public String getAlienByName(@RequestParam("name2") String name, Model m){
        m.addAttribute("result",repo.find(name));
        return "showAliens";
    }

    @PostMapping("addAlien")
    public String addAlien(@ModelAttribute("a1") Alien alien){
        repo.save(alien);
        return "result";
    }
}
