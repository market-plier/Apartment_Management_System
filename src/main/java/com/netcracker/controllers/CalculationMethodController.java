package com.netcracker.controllers;

import com.netcracker.exception.DaoAccessException;
import com.netcracker.models.CalculationMethod;
import com.netcracker.services.CommunalUtilityService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.List;

@RequestMapping("/calculation-method")
@RestController
public class CalculationMethodController {
    private final CommunalUtilityService communalUtilityService;

    CalculationMethodController(CommunalUtilityService communalUtilityService) {
        this.communalUtilityService = communalUtilityService;
    }

    @GetMapping
    public List<CalculationMethod> getAllCalculationMethods()
            throws DaoAccessException {
        return communalUtilityService.getAllCalculationMethods();
    }

    @PutMapping
    public void updateCalculationMethod(@RequestBody @Valid CalculationMethod calculationMethod) throws DaoAccessException {
        communalUtilityService.updateCalculationMethod(calculationMethod);
    }

    @PostMapping
    public void createCalculationMethod(@RequestBody @Valid CalculationMethod calculationMethod) throws DaoAccessException {
        communalUtilityService.createCalculationMethod(calculationMethod);
    }

    @DeleteMapping("{id}")
    public void deleteCalculationMethod(@PathVariable BigInteger id) {
        communalUtilityService.deleteCalculationMethod(id);
    }
}
