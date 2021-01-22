package com.netcracker.controllers;

import com.netcracker.exception.DaoAccessException;
import com.netcracker.models.CommunalUtility;
import com.netcracker.services.CommunalUtilityService;
import lombok.extern.log4j.Log4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.List;

@Log4j
@RequestMapping("/communal-utilities")
@RestController
public class CommunalUtilityController {
    private final CommunalUtilityService communalUtilityService;

    CommunalUtilityController(CommunalUtilityService communalUtilityService) {
        this.communalUtilityService = communalUtilityService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_OWNER')")
    public List<CommunalUtility> getAllCommunalUtilities(@RequestParam(value = "status", required = false)
                                                                 CommunalUtility.Status status)
            throws DaoAccessException {
        log.error("hm");
        return communalUtilityService.getAllCommunalUtilities(status);
    }

    @GetMapping(value = "/comm-util")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_OWNER')")
    public List<CommunalUtility> getAllCommunalUtilitiesWithOutCalcMeth()
            throws DaoAccessException {
        return communalUtilityService.getAllCommunalUtilities();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_OWNER')")
    public CommunalUtility getCommunalUtilityById(@PathVariable BigInteger id)
            throws DaoAccessException {
        return communalUtilityService.getCommunalUtilityById(id);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public ResponseEntity<List<String>> update(@RequestBody @Valid CommunalUtility communalUtility) throws DaoAccessException {
        communalUtilityService.updateCommunalUtility(communalUtility);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public void create(@RequestBody @Valid CommunalUtility communalUtility) throws DaoAccessException {
        communalUtilityService.createCommunalUtility(communalUtility);
    }
}
