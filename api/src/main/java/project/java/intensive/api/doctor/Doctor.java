package project.java.intensive.api.doctor;

import jakarta.persistence.*;
import lombok.*;
import project.java.intensive.api.adress.Address;

@Table (name = "doctor")
@Entity (name = "Doctor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String telephone;
    private String license;

    @Enumerated(EnumType.STRING)
    private Specialty specialty;

    @Embedded
    private Address address;

    public Doctor(DoctorRegistrationData data) {
        this.name = data.name();
        this.email = data.email();
        this.telephone = data.telephone();
        this.license = data.license();
        this.specialty = data.specialty();
        this.address = new Address(data.address());
    }
}

