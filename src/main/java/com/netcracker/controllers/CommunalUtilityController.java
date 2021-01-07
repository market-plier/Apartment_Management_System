package com.netcracker.controllers;

import com.netcracker.exception.DaoAccessException;
import com.netcracker.models.CommunalUtility;
import com.netcracker.services.CommunalUtilityService;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

@RequestMapping("/communal-utilities")
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
    public void update(@RequestBody @Valid CommunalUtility communalUtility) throws DaoAccessException {
        communalUtilityService.updateCommunalUtility(communalUtility);
    }

    @PostMapping
    public void create(@RequestBody @Valid CommunalUtility communalUtility) throws DaoAccessException, IOException, MessagingException {
        communalUtilityService.createCommunalUtility(communalUtility);
    }
}
