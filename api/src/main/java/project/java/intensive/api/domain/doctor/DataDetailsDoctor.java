package project.java.intensive.api.domain.doctor;

import project.java.intensive.api.domain.adress.Address;


public record DataDetailsDoctor(Long id, String name, String email, Specialty specialty,
                                String telephone, Address address) {

    public DataDetailsDoctor(Doctor doctor){
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getSpecialty(), doctor.getTelephone(),
                doctor.getAddress());
    }
}
