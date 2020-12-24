package com.netcracker.controllers;

import com.netcracker.exception.DaoAccessException;
import com.netcracker.models.CommunalUtility;
import com.netcracker.services.CommunalUtilityService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.List;

@RequestMapping("/communalutilities")
@RestController
public class CommunalUtilityController {
    private final CommunalUtilityService communalUtilityService;

    CommunalUtilityController(CommunalUtilityService communalUtilityService) {
        this.communalUtilityService = communalUtilityService;
    }

    @GetMapping
    public List<CommunalUtility> getAllCommunalUtilities(@RequestParam(value = "status", required = false) CommunalUtility.Status status)
            throws DaoAccessException {
        return communalUtilityService.getAllCommunalUtilities(status);
    }

    @GetMapping("/{id}")
    public CommunalUtility getCommunalUtilityById(@PathVariable BigInteger id)
            throws DaoAccessException {
        return communalUtilityService.getCommunalUtilityById(id);
    }

    @PutMapping
    public CommunalUtility update(@RequestBody @Valid CommunalUtility communalUtility) throws DaoAccessException {
        return communalUtilityService.updateCommunalUtility(communalUtility);
    }

    @PostMapping
    public CommunalUtility create(@RequestBody @Valid CommunalUtility communalUtility) throws DaoAccessException {
        return communalUtilityService.createCommunalUtility(communalUtility);
    }
}
