package br.com.unicuritiba.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.unicuritiba.models.SmallExpenseSimulation;
import br.com.unicuritiba.services.SmallExpenseSimulationService;

@RestController
@RequestMapping("/small-expenses")
public class SmallExpenseSimulationController {

    @Autowired
    private SmallExpenseSimulationService service;

    // Get all simulations by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SmallExpenseSimulation>> getByUserId(@PathVariable Long userId) {
        List<SmallExpenseSimulation> list = service.getAllByUserId(userId);
        return ResponseEntity.ok(list);
    }

    // Get simulation by ID
    @GetMapping("/{id}")
    public ResponseEntity<SmallExpenseSimulation> getById(@PathVariable Long id) {
        SmallExpenseSimulation sim = service.getById(id);
        if (sim == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sim);
    }

    // Create new simulation
    @PostMapping
    public ResponseEntity<SmallExpenseSimulation> create(@RequestBody SmallExpenseSimulation sim) {
        return ResponseEntity.ok(service.save(sim));
    }

    // Update simulation
    @PutMapping("/{id}")
    public ResponseEntity<SmallExpenseSimulation> update(@PathVariable Long id,
                                                        @RequestBody SmallExpenseSimulation sim) {
        return ResponseEntity.ok(service.update(id, sim));
    }

    // Delete simulation
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
