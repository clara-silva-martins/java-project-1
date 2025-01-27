package project.java.intensive.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import project.java.intensive.api.doctor.Doctor;
import project.java.intensive.api.doctor.DoctorListingData;
import project.java.intensive.api.doctor.DoctorRegistrationData;
import project.java.intensive.api.doctor.DoctorRepository;

import java.util.List;

@RestController
@RequestMapping("doctor")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    @PostMapping
    @Transactional
    public void doctorRegister(@RequestBody @Valid DoctorRegistrationData data){
        repository.save(new Doctor(data));
    }

    @GetMapping
    public List<DoctorListingData> listing() {
       return repository.findAll().stream().map(DoctorListingData::new).toList();
    }

}
