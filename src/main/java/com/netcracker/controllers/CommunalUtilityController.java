package com.netcracker.controllers;

import com.netcracker.exception.DaoAccessException;
import com.netcracker.models.CalculationMethod;
import com.netcracker.models.CommunalUtility;
import com.netcracker.services.CommunalUtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequestMapping("/communal-utilities")
@RestController
public class CommunalUtilityController {
    private final CommunalUtilityService communalUtilityService;
    @Autowired
    private Validator validator;

    CommunalUtilityController(CommunalUtilityService communalUtilityService) {
        this.communalUtilityService = communalUtilityService;
    }

    @GetMapping
    public List<CommunalUtility> getAllCommunalUtilities(@RequestParam(value = "status", required = false) CommunalUtility.Status status)
            throws DaoAccessException {
        return communalUtilityService.getAllCommunalUtilities(status);
    }

    @GetMapping(value = "/comm-util")
    public List<CommunalUtility> getAllCommunalUtilitiesWithOutCalcMeth()
            throws DaoAccessException {
        return communalUtilityService.getAllCommunalUtilitiesWithOutCalcMethod();
    }

    @GetMapping("/{id}")
    public CommunalUtility getCommunalUtilityById(@PathVariable BigInteger id)
            throws DaoAccessException {
        return communalUtilityService.getCommunalUtilityById(id);
    }

    @PutMapping
    public ResponseEntity<List<String>> update(@RequestBody @Valid CommunalUtility communalUtility) throws DaoAccessException {
        Set<ConstraintViolation<CalculationMethod>> violations = validator.validate(communalUtility.getCalculationMethod());
        if (!violations.isEmpty()) {
            List<String> messages = violations
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toList());

            return ResponseEntity.badRequest().body(messages);
        }
        communalUtilityService.updateCommunalUtility(communalUtility);

        return ResponseEntity.ok().build();
    }

    @PostMapping
    public void create(@RequestBody @Valid CommunalUtility communalUtility) throws DaoAccessException, IOException, MessagingException {
        communalUtilityService.createCommunalUtility(communalUtility);
    }
}
