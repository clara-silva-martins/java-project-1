package project.java.intensive.api.domain.doctor;

public record DoctorListingData(Long id, String name, String email, String license, Specialty specialty) {

    public DoctorListingData(Doctor doctor){
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getLicense(), doctor.getSpecialty());
    }
}
