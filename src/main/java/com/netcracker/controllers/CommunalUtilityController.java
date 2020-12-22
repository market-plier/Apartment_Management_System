package com.netcracker.controllers;

import com.netcracker.models.CommunalUtility;
import com.netcracker.services.CommunalUtilityService;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
public class CommunalUtilityController {
    private final CommunalUtilityService communalUtilityService;

    CommunalUtilityController(CommunalUtilityService communalUtilityService) {
        this.communalUtilityService = communalUtilityService;
    }

    @GetMapping("/communalutilities")
    public List<CommunalUtility> getAllCommunalUtilities(@RequestParam(value = "status", required = false) CommunalUtility.Status status) {
        return communalUtilityService.getAllCommunalUtilities(status);
    }

    @GetMapping("/communalutilities/{id}")
    public CommunalUtility getCommunalUtilityById(@PathVariable BigInteger id) {
        return communalUtilityService.getCommunalUtilityById(id);
    }

    @PutMapping("/communalutilities")
    public void update(@RequestBody CommunalUtility communalUtility) {
        communalUtilityService.updateCommunalUtility(communalUtility);
    }

    @PostMapping("/communalutilities")
    public void create(@RequestBody CommunalUtility communalUtility) {
        communalUtilityService.createCommunalUtility(communalUtility);
    }

}
