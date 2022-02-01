package com.learnproj.sheduler.controller;


import com.learnproj.sheduler.model.Person;
import com.learnproj.sheduler.model.StudyGroup;
import com.learnproj.sheduler.service.PersonService;
import com.learnproj.sheduler.service.StudyGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private StudyGroupService studyGroupService;

    public PersonController(PersonService personService, StudyGroupService studyGroupService) {
        this.personService = personService;
        this.studyGroupService = studyGroupService;
    }

    @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
    @GetMapping("")
    public String getPersons(HttpServletRequest request, Model theModel){
        int page = 0; //default page number
        int size = 5; // default page size

        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }
        Page<Person> personsList = personService.findAll(page, size);
        theModel.addAttribute("personsList", personsList);
        return "/person/person";
    }
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
    @PostMapping("/deletePerson")
    public String deletePerson(@RequestParam("personId") int personId) {
        personService.deleteById(personId);
        return "redirect:/person";
    }

    @PreAuthorize("hasRole('ADMIN') and hasRole('MANAGER')")
    @GetMapping("/showFormForPersonAdd")
    public String showAddPersonForm (Model theModel){
        Person person = new Person();
        theModel.addAttribute("person", person);
        List<StudyGroup> studyGroupList= studyGroupService.findAll();
        theModel.addAttribute("studyGroupList", studyGroupList);
        return "/person/personForm";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/showFormForPersonUpdate")
    public String showFormForPersonUpdate(@RequestParam("personId") int personId, Model theModel) {
        Person person = personService.findById(personId);
        String personsGroupName = person.getStudyGroup().getGroupName();
        theModel.addAttribute( "person", person);
        theModel.addAttribute( "personsGroupName", personsGroupName);
        List<StudyGroup> studyGroupList= studyGroupService.findAll();
        theModel.addAttribute("studyGroupList", studyGroupList);
        return "/person/personForm";
    }

    @PreAuthorize("hasRole('ADMIN') and hasRole('MANAGER')")
    @PostMapping("/savePerson")
    public String savePerson(@ModelAttribute("person") @Valid Person person, BindingResult result){
        if (result.hasErrors()) {
            return "/person/personForm";
        }
        personService.save(person);
        return "redirect:/person";
    }

}