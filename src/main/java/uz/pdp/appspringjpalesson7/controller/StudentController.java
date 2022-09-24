package uz.pdp.appspringjpalesson7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appspringjpalesson7.entity.Address;
import uz.pdp.appspringjpalesson7.entity.Group;
import uz.pdp.appspringjpalesson7.entity.Student;
import uz.pdp.appspringjpalesson7.payload.StudentDto;
import uz.pdp.appspringjpalesson7.repository.AddressRepository;
import uz.pdp.appspringjpalesson7.repository.GroupRepository;
import uz.pdp.appspringjpalesson7.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    GroupRepository groupRepository;

    // VAZIRLIK UCHUN
    @GetMapping("/forMinistry")
    public Page<Student> students(@RequestParam int page){
        Pageable pageable = PageRequest.of(page, 10);
        return studentRepository.findAll(pageable);
    }
    // UNIVERSITET uchun
    @GetMapping("/forUniversity/{universityId}")
    public Page<Student> studentsFoUniversity(@PathVariable Integer universityId, @RequestParam int page){
        Pageable pageable = PageRequest.of(page, 10);
        return studentRepository.findAllByGroup_Faculty_UniversityId(universityId, pageable);
    }


    // FACULTET UCHUN
    @GetMapping("/forFaculty/{facultyId}")
    public Page<Student> studentsFoFaculty(@PathVariable Integer facultyId, @RequestParam int page){
        Pageable pageable = PageRequest.of(page, 10);
        return studentRepository.findAllByGroup_Faculty_Id(facultyId, pageable);
    }

    // GURUH UCHUN
    @GetMapping("/forGroup/{groupId}")
    public Page<Student> studentsFoGroup(@PathVariable Integer groupId, @RequestParam int page){
        Pageable pageable = PageRequest.of(page, 10);
        return studentRepository.findByGroup_Id(groupId, pageable);
    }

    // CREAT
    @PostMapping
    public String addStudent(@RequestBody StudentDto studentDto){
        Student student = new Student();
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());

        Optional<Address> optionalAddress = addressRepository.findById(studentDto.getAddressId());
        if (!optionalAddress.isPresent()){
            return "Such address not found";
        }
        Optional<Group> optionalGroup = groupRepository.findById(studentDto.getGroupId());
        if (!optionalGroup.isPresent()){
            return "Such address not found";
        }
        student.setAddress(optionalAddress.get());
        student.setGroup(optionalGroup.get());
        studentRepository.save(student);
        return "Student added";

    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Integer id){
        studentRepository.deleteById(id);
        return "Student deleted";
    }

    //UPDATE
    @PutMapping("/{id}")
    public String editedStudent(@PathVariable Integer id, @RequestBody StudentDto studentDto){
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (!optionalStudent.isPresent()){
            return "Student not found";
        }
        Student student = new Student();
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        Optional<Group> optionalGroup = groupRepository.findById(studentDto.getGroupId());
        if (!optionalGroup.isPresent()){
            return "Group not found";
        }
        student.setGroup(optionalGroup.get());
        Optional<Address> optionalAddress = addressRepository.findById(studentDto.getAddressId());
        if (!optionalAddress.isPresent()){
            return "Address not found";
        }
        student.setAddress(optionalAddress.get());
        studentRepository.save(student);
        return "Student edited";
    }
}
