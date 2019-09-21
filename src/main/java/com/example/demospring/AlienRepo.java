package com.example.demospring;

import com.example.demospring.model.Alien;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlienRepo extends JpaRepository<Alien,Integer>{

    List<Alien> findByName(String name);
}