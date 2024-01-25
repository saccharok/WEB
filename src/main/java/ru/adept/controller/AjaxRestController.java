package ru.adept.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.adept.entity.Plant;
import ru.adept.entity.PreservesAndPlant;
import ru.adept.model.PlantModel;
import ru.adept.repo.PlantRepository;
import ru.adept.repo.PreserveRepository;

import java.util.ArrayList;

@RestController
public class AjaxRestController {

    @Autowired
    PlantRepository plantRepository;
    @Autowired
    PreserveRepository preserveRepository;

    @PostMapping("/ajax/add-herb")
    @ResponseBody
    public ResponseEntity<?> addHerb(@ModelAttribute("plant") PlantModel plant) {
        Plant entityPlant = new Plant();
        entityPlant.setId(0L);
        entityPlant.setName(plant.getName());
        entityPlant.setDescription(plant.getDescription());
        entityPlant.setResearchers(plant.getResearchers());
        entityPlant.setStatus(plant.getStatus());
        entityPlant.setPreserves(new ArrayList<>());
        for (var elem : plant.getPreserves()) {
            PreservesAndPlant entity = new PreservesAndPlant();
            entity.setPlant(entityPlant);
            entity.setPreserve(preserveRepository.findById(elem.getId()).orElseThrow());
            entityPlant.getPreserves().add(entity);
        }
        plantRepository.save(entityPlant);
        return ResponseEntity.ok(plantRepository.findByName(entityPlant.getName()));
    }

    @DeleteMapping("/herb/{id}")
    public ResponseEntity<?> deletePlant(@PathVariable Long id) {
        plantRepository.deleteById(id);
        return ResponseEntity.ok("Plant by id=" + id + " has been deleted!");
    }

    @GetMapping("/ajax/herb/{id}")
    public String viewPlant(@PathVariable Long id) {
        Plant plant = plantRepository.findById(id).orElseThrow();
        return plant.toString();
    }

    @GetMapping("/ajax/loadHerb/{id}")
    public ModelAndView loadHerbPage(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("/ajax-herb");
        mv.addObject("herbId", id);
        return mv;
    }
}
