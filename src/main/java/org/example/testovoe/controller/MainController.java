package org.example.testovoe.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.example.testovoe.DTO.CatDto;
import org.example.testovoe.entity.Cat;
import org.example.testovoe.repository.CatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "main_methods")

@Slf4j
@RestController
@RequiredArgsConstructor

public class MainController {

    private final CatRepo catRepo;

    @Operation(summary = "кладет нового  котика в базу",

            description = "Получает DTO кота и билдером собирает и сохраняет сущность в базу")
    @PostMapping("/api/add")

    public void addCat(@RequestBody CatDto catDTO) {
        log.info(" Mew row : " + catRepo.save(
                Cat.builder()
                        .name(catDTO.getName())
                        .age(catDTO.getAge())
                        .weight(catDTO.getWeight())

                .build()));

    }

    @SneakyThrows
    @GetMapping("/api/all")
    public List<Cat> getAll() {
        return catRepo.findAll();

    }

    @GetMapping("/api")
    public Cat getCat(@RequestParam int id) {
        return catRepo.findById(id).orElseThrow();
    }

    @DeleteMapping("/api")
    public void deleteCat(@RequestParam int id) {
        catRepo.deleteById(id);
    }

    @PutMapping("/api/add")
    public String updateCat(@RequestBody Cat cat) {
        if (!catRepo.existsById(cat.getId())) {
            return "No such row";
        }
        return catRepo.save(cat).toString();
    }


}

