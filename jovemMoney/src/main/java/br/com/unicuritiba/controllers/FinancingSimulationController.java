package br.com.unicuritiba.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.unicuritiba.models.FinancingSimulation;
import br.com.unicuritiba.services.FinancingSimulationService;

@RestController
@RequestMapping("/api/users/{userId}/financing-simulations")
public class FinancingSimulationController {

    @Autowired
    private FinancingSimulationService service;

    @PostMapping
    public ResponseEntity<FinancingSimulation> create(
            @PathVariable Long userId,
            @RequestBody FinancingSimulation sim) {
        return ResponseEntity.ok(service.createSimulation(userId, sim));
    }

    @GetMapping
    public ResponseEntity<List<FinancingSimulation>> getAll(
            @PathVariable Long userId) {
        return ResponseEntity.ok(service.getAllByUser(userId));
    }

    @GetMapping("/{simId}")
    public ResponseEntity<FinancingSimulation> getOne(
            @PathVariable Long userId,
            @PathVariable Long simId) {
        return ResponseEntity.ok(service.getById(userId, simId));
    }

    @PutMapping("/{simId}")
    public ResponseEntity<FinancingSimulation> update(
            @PathVariable Long userId,
            @PathVariable Long simId,
            @RequestBody FinancingSimulation sim) {
        return ResponseEntity.ok(service.updateSimulation(userId, simId, sim));
    }

    @DeleteMapping("/{simId}")
    public void delete(
            @PathVariable Long userId,
            @PathVariable Long simId) {
        service.deleteSimulation(userId, simId);
    }
}
