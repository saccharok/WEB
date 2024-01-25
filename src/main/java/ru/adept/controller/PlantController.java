package ru.adept.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.adept.entity.Plant;
import ru.adept.entity.Preserve;
import ru.adept.entity.PreservesAndPlant;
import ru.adept.model.PlantModel;
import ru.adept.repo.PlantRepository;
import ru.adept.repo.PreserveRepository;
import ru.adept.repo.PreservesNPlantsRepository;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class PlantController {
    @Autowired
    PlantRepository plantRepository;

    @Autowired
    PreservesNPlantsRepository pNpRepository;

    @Autowired
    PreserveRepository preserveRepository;

    @GetMapping(value = {"/", "/index-admin"})
    public String loadIndex(Model model, HttpServletRequest request) {
        List<Plant> plants = plantRepository.findAll(Sort.by("name"));
        model.addAttribute("plants", plants);
        String requestURI = request.getRequestURI();
        if (requestURI.equals("/"))
            return "index";
        else
            return "index-admin";
    }

    @GetMapping("/edit-herb/{id}")
    public String editPlant(@PathVariable Long id, Model model) {
        Optional<Plant> plant = plantRepository.findById(id);
        if (plant.isPresent()) {
            Plant plantBuf = plant.get();
            List<Preserve> buf = new ArrayList<>();
            plantBuf.getPreserves().forEach(connection -> buf.add(connection.getPreserve()));
            PlantModel inputModel = PlantModel.builder()
                    .id(plantBuf.getId())
                    .name(plantBuf.getName())
                    .description(plantBuf.getDescription())
                    .researchers(plantBuf.getResearchers())
                    .status(plantBuf.getStatus())
                    .preserves(new ArrayList<>(buf))
                    .build();
            model.addAttribute("plant", inputModel);
        }
        else
            model.addAttribute("plant", new PlantModel());
        model.addAttribute("herbId", id);
        List<Preserve> preserves = preserveRepository.findAll(Sort.by("name"));
        model.addAttribute("allPreserves", preserves);
        return "edit-herb";
    }

    @PostMapping("/edit-herb/{id}")
    public String updatePlant(@PathVariable Long id, @ModelAttribute("plant") PlantModel plant) {
        Plant entityPlant = new Plant();
        entityPlant.setId(id);
        entityPlant.setName(plant.getName());
        entityPlant.setDescription(plant.getDescription());
        entityPlant.setResearchers(plant.getResearchers());
        entityPlant.setStatus(plant.getStatus());
        entityPlant.setPreserves(new ArrayList<>());
        for (var elem : plant.getPreserves()) {
            PreservesAndPlant entity = new PreservesAndPlant();
            entity.setPlant(plantRepository.findById(id).orElseThrow());
            entity.setPreserve(preserveRepository.findById(elem.getId()).orElseThrow());
            entityPlant.getPreserves().add(entity);
        }
        plantRepository.save(entityPlant);
        return "redirect:/index-admin";
    }

    @GetMapping("/add-herb")
    public String addPlantForm(Model model) {
        model.addAttribute("plant", new PlantModel());
        List<Preserve> preserves = preserveRepository.findAll(Sort.by("name"));
        model.addAttribute("allPreserves", preserves);
        return "add-herb";
    }

    @PostMapping("/add-herb")
    public String addPlant(@ModelAttribute("plant") PlantModel plant) {
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
        return "redirect:/";
    }

    @GetMapping("/herb/{id}")
    public String viewPlant(@PathVariable Long id, Model model) {
        model.addAttribute("plant", plantRepository.findById(id).orElseThrow());
        return "herb";
    }
}
