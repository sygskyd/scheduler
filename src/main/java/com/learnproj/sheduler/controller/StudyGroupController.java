package com.learnproj.sheduler.controller;

import com.learnproj.sheduler.model.StudyGroup;
import com.learnproj.sheduler.service.PersonService;
import com.learnproj.sheduler.service.StudyGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/studyGroup")
public class StudyGroupController {

    @Autowired
    StudyGroupService studyGroupService;

    @Autowired
    PersonService personService;


    public StudyGroupController(StudyGroupService studyGroupService, PersonService personService) {
        this.studyGroupService = studyGroupService;
        this.personService = personService;
    }

    @PreAuthorize("hasAuthority('all:read')")
    @GetMapping("")
    public String getAllGroups(HttpServletRequest request, Model model) {
        int page = 0; //default page number
        int size = 5; // default page size

        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }

        Page<StudyGroup> studyGroupList = studyGroupService.findAll(page, size);
        model.addAttribute("studyGroupList", studyGroupList);
        return "studyGroup/studyGroups";
    }

    @PreAuthorize("hasAuthority('all:write')")
    @GetMapping("/showFormForStudyGroupAdd")
    public String showAddStudyGroupForm(Model model){
        model.addAttribute("studyGroup",new StudyGroup());
        model.addAttribute("personsList", personService.findAll());         //be care of 's' character
        return "studyGroup/studyGroupForm";
    }

//TODO search function
//    @GetMapping("/searchStudyGroup")
//    public String searchClient(@RequestParam("name") @Valid String name, Model model){
//        List<StudyGroup> studyGroupList=studyGroupService.findClientsByName(name);
//        model.addAttribute("studyGroupList", studyGroupList);
//        return "/studyGroup/studyGroups";
//    }

    @PreAuthorize("hasAuthority('all:write')")
    @PostMapping("/saveStudyGroup")
    public String saveStudyGroup(@ModelAttribute("studyGroup") @Valid StudyGroup studyGroup){
        studyGroupService.save(studyGroup);
        return "redirect:/studyGroup";
    }

    @PreAuthorize("hasAuthority('all:write')")
    @GetMapping("/showFormForStudyGroupUpdate")
    public String getForPostEditStudyGroup(@RequestParam("groupId") @Valid int groupId, Model model){
        model.addAttribute("client",studyGroupService.findById(groupId));
        return "studyGroup/studyGroupForm";
    }

    @PreAuthorize("hasAuthority('all:write')")
    @PostMapping("/showFormForStudyGroupUpdate")
    public String showFormForEditStudyGroup(@RequestParam("groupId") @Valid int groupId, Model model) {
        StudyGroup studyGroup = studyGroupService.findById(groupId);
        model.addAttribute("studyGroup", studyGroup);
        model.addAttribute("personsList", studyGroup.getPersonList());     //be care of 's' character
        return "studyGroup/studyGroupForm";
    }

    @PreAuthorize("hasAuthority('admin:write')")
    @PostMapping("/deleteStudyGroup")
    public String deleteStudyGroup(@RequestParam("groupId") @Valid int groupId) {
        studyGroupService.deleteById(groupId);
        return "redirect:/studyGroup";
    }

}
