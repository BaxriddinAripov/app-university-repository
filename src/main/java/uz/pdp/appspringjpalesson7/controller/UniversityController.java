package uz.pdp.appspringjpalesson7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appspringjpalesson7.entity.Address;
import uz.pdp.appspringjpalesson7.entity.University;
import uz.pdp.appspringjpalesson7.payload.UniversityDto;
import uz.pdp.appspringjpalesson7.repository.AddressRepository;
import uz.pdp.appspringjpalesson7.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class UniversityController {

    @Autowired
    UniversityRepository universityRepository;
    @Autowired
    AddressRepository addressRepository;

    // READ ALL UNIVERSITY
    @RequestMapping(value = "/university", method = RequestMethod.GET)
    public List<University> getUniversities() {
        List<University> universityList = universityRepository.findAll();
        return universityList;
    }

    // CREAT UNIVERSITY
    @RequestMapping(value = "/university", method = RequestMethod.POST)
    public String addUniversity(@RequestBody UniversityDto universityDto) {
        // YANGI ADDRESS OCHIB OLDIK
        Address address = new Address();
        address.setCity(universityDto.getCity());
        address.setDistrict(universityDto.getDistrict());
        address.setStreet(universityDto.getStreet());
        // YASAB OLGAN OBJECTIMIZNI DB GA SAQLAB OLDIK VA BIZGA SAQLANGAN ADDRESS BERDI
        Address savedAddress = addressRepository.save(address);
        //YANGI UNIVERSITET YASAB OLDIK
        University university = new University();
        university.setName(universityDto.getName());
        university.setAddress(savedAddress);
        universityRepository.save(university);
        return "University added";
    }

    // READ UNIVERSITY BY ID
    @RequestMapping(value = "/university/{id}", method = RequestMethod.GET)
    public University getUniversity(@PathVariable Integer id) {
        Optional<University> optionalUniversity = universityRepository.findById(id);
        if (optionalUniversity.isPresent()) {
            University universityById = optionalUniversity.get();
            return universityById;
        }
        return new University();
    }

    // DELETE UNIVERSITY BY ID
    @RequestMapping(value = "/university/{id}", method = RequestMethod.DELETE)
    public String deletedUniversityById(@PathVariable Integer id) {
        universityRepository.deleteById(id);
        return "Deleted University";
    }

    // UPDATE UNIVERSITY BY ID
    @RequestMapping(value = "/university/{id}", method = RequestMethod.PUT)
    private String editedUniversity(@PathVariable Integer id, @RequestBody UniversityDto universityDto) {
        Optional<University> optionalUniversity = universityRepository.findById(id);
        if (optionalUniversity.isPresent()) {
            University university = optionalUniversity.get();
            university.setName(universityDto.getName());
            Address address = university.getAddress();
            address.setCity(universityDto.getCity());
            address.setDistrict(universityDto.getDistrict());
            address.setStreet(universityDto.getStreet());
            addressRepository.save(address);
            universityRepository.save(university);
            return "University edited";
        }
        return "University not found";
    }
}
