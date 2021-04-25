package com.prozoro.app.demo.controller;


import com.prozoro.app.demo.service.ClassifiersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;


@RestController
@RequestMapping(value = "/v1/classifiers")
public class ClassifiersController {

    @Autowired
    private ClassifiersService service;

    @RolesAllowed("ROLE_ADMIN")
    @GetMapping("/update")
    public ResponseEntity updateSection() {
        service.updateClassifiersData();
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @RolesAllowed({"ROLE_ADMIN", "ROLE_VIEWER"})
    @GetMapping("/section")
    public ResponseEntity getAllSection() {
        return ResponseEntity.ok(service.findAllSectionDto());
    }

    @RolesAllowed({"ROLE_ADMIN", "ROLE_VIEWER"})
    @GetMapping("/section/{sectionId}")
    public ResponseEntity getSection(@PathVariable("sectionId") String sectionId) {
        return ResponseEntity.ok(service.findSectionDto(sectionId));
    }
}
