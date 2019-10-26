package com.example.demospring.controller;

import com.example.demospring.AlienRepo;
import com.example.demospring.model.Alien;
import org.springframework.web.bind.annotation.*;

@RestController
public class AlienController {

    private AlienRepo alienRepo;

    public AlienController(AlienRepo alienRepo) {
        this.alienRepo = alienRepo;
    }

    @GetMapping("/aliens")
    public Iterable<Alien> getAliens(){
        return alienRepo.findAll();
    }

    @PostMapping("/alien")
    public void addAlien(@RequestParam String name){
        Alien alien = new Alien();
        alien.setName(name);
        alienRepo.save(alien);
    }

    @GetMapping("aliens/{id}")
    public Alien getAlien(@PathVariable("id") int id){
        return alienRepo.findById(id).get();
    }
}
