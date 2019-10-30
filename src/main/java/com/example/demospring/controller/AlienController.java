package com.example.demospring.controller;

import com.example.demospring.reposiotry.AlienRepository;
import com.example.demospring.model.Alien;
import org.springframework.web.bind.annotation.*;

@RestController
public class AlienController {

    private AlienRepository alienRepository;

    public AlienController(AlienRepository alienRepository) {
        this.alienRepository = alienRepository;
    }

    @GetMapping("/aliens")
    public Iterable<Alien> getAliens(){
        return alienRepository.findAll();
    }

    @PostMapping("/alien")
    public void addAlien(@RequestParam String name){
        Alien alien = new Alien();
        alien.setName(name);
        alienRepository.save(alien);
    }

    @GetMapping("aliens/{id}")
    public Alien getAlien(@PathVariable("id") int id){
        return alienRepository.findById(id).get();
    }
}
