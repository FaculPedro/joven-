package br.com.unicuritiba.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.unicuritiba.models.InterestComparisonSimulation;
import br.com.unicuritiba.services.InterestComparisonSimulationService;

@RestController
@RequestMapping("/api/users/{userId}/interest-comparison-simulations")
public class InterestComparisonSimulationController {

    @Autowired
    private InterestComparisonSimulationService service;

    @PostMapping
    public ResponseEntity<InterestComparisonSimulation> create(
            @PathVariable Long userId,
            @RequestBody InterestComparisonSimulation sim) {
        return ResponseEntity.ok(service.createSimulation(userId, sim));
    }

    @GetMapping
    public ResponseEntity<List<InterestComparisonSimulation>> getAll(
            @PathVariable Long userId) {
        return ResponseEntity.ok(service.getAllByUser(userId));
    }

    @GetMapping("/{simId}")
    public ResponseEntity<InterestComparisonSimulation> getOne(
            @PathVariable Long userId,
            @PathVariable Long simId) {
        return ResponseEntity.ok(service.getById(userId, simId));
    }

    @PutMapping("/{simId}")
    public ResponseEntity<InterestComparisonSimulation> update(
            @PathVariable Long userId,
            @PathVariable Long simId,
            @RequestBody InterestComparisonSimulation sim) {
        return ResponseEntity.ok(service.updateSimulation(userId, simId, sim));
    }

    @DeleteMapping("/{simId}")
    public void delete(
            @PathVariable Long userId,
            @PathVariable Long simId) {
        service.deleteSimulation(userId, simId);
    }
}
