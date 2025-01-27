package project.java.intensive.api.doctor;

public record DoctorListingData(String name, String email, String license, Specialty specialty) {

    public DoctorListingData(Doctor doctor){
        this(doctor.getName(), doctor.getEmail(), doctor.getLicense(), doctor.getSpecialty());
    }
}
