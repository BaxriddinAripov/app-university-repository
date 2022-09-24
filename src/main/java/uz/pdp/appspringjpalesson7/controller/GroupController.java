package uz.pdp.appspringjpalesson7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appspringjpalesson7.entity.Faculty;
import uz.pdp.appspringjpalesson7.entity.Group;
import uz.pdp.appspringjpalesson7.payload.GroupDto;
import uz.pdp.appspringjpalesson7.repository.FacultyRepository;
import uz.pdp.appspringjpalesson7.repository.GroupRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/group")
public class    GroupController {
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    FacultyRepository facultyRepository;

    // VAZIRLIK UCHUN
    // READ
    @GetMapping
    public List<Group> getGroups(){
        List<Group> groups = groupRepository.findAll();
        return groups;
    }

    // UNIVERSITET MASUL XODIMI UCHUN
    @GetMapping("/byUniversityId/{universityId}")
    public List<Group> getGroupsByUniversity(@PathVariable Integer universityId){
        List<Group> allByFaculty_universityId = groupRepository.findAllByFaculty_UniversityId(universityId);
        List<Group> groupsByUniversityId = groupRepository.getGroupsByUniversityId(universityId);
        List<Group> groupsByUniversityIdNative = groupRepository.getGroupsByUniversityIdNative(universityId);
        return allByFaculty_universityId;
    }

    @PostMapping
    public String addGroup(@RequestBody GroupDto groupDto){
        Group group = new Group();
        group.setName(groupDto.getName());

        Optional<Faculty> optionalFaculty = facultyRepository.findById(groupDto.getFacultyId());
        if (!optionalFaculty.isPresent()){
            return "Such faculty not found";
        }
        group.setFaculty(optionalFaculty.get());
        groupRepository.save(group);
        return "Group added";
    }
}
