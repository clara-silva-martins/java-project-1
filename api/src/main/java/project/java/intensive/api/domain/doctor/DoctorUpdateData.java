package project.java.intensive.api.domain.doctor;

import jakarta.validation.constraints.NotNull;
import project.java.intensive.api.domain.adress.AddressData;

public record DoctorUpdateData(
        @NotNull
        Long id,
        String name,
        String telephone,
        AddressData address) {
}
