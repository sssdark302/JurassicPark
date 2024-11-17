package com.example.jurassicpark.controllers;
import com.example.jurassicpark.models.Dinosaurio;
import com.example.jurassicpark.models.entidades.Dinos;
import com.example.jurassicpark.service.DinosaurioService;
import com.example.jurassicpark.service.InstalacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.jurassicpark.repository.DinosaurioRepository;

import java.util.List;

@RestController
@RequestMapping("/dinosaurios")
public class DinosaurioController {

}
