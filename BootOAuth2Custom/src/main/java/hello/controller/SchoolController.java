package hello.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hello.entity.School;
import hello.service.SchoolService;

@RestController
@RequestMapping("/api/school")
@PreAuthorize("hasAuthority('PARTNER')")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    @GetMapping(value = "/all")
    public List<School> getAllSchools() {
        return schoolService.getAllSchools();
    }

    @GetMapping(value = "/{id}")
    public School getSchool(@PathVariable("id") int id) {
        return schoolService.getSchool(id);
    }
}
