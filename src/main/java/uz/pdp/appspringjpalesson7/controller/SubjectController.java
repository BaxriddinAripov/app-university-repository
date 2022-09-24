package uz.pdp.appspringjpalesson7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appspringjpalesson7.entity.Subject;
import uz.pdp.appspringjpalesson7.repository.SubjectRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/subject")
public class SubjectController {

    @Autowired
    SubjectRepository subjectRepository;

    // CREAT SUBJECT
    @RequestMapping(method = RequestMethod.POST)
    public String addSubject(@RequestBody Subject subject){
        boolean existsByName = subjectRepository.existsByName(subject.getName());
        if (existsByName){
            return "This subject already exist";
        }
        subjectRepository.save(subject);
        return "Subject added";
    }

    @GetMapping
    public List<Subject> getSubjects(){
        List<Subject> subjectList = subjectRepository.findAll();
        return subjectList;
    }
}
