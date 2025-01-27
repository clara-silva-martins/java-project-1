package project.java.intensive.api.doctor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import project.java.intensive.api.adress.AddressData;

public record DoctorRegistrationData(

        @NotBlank
        String name,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telephone,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String license,
        @NotNull
        Specialty specialty,
        @NotNull @Valid
        AddressData address) {
}

