package project.java.intensive.api.domain.doctor;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import project.java.intensive.api.domain.adress.Address;

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

    private Boolean active;

    public Doctor(DoctorRegistrationData data) {
        this.active = true;
        this.name = data.name();
        this.email = data.email();
        this.telephone = data.telephone();
        this.license = data.license();
        this.specialty = data.specialty();
        this.address = new Address(data.address());
    }

    public void updateInfo(@Valid DoctorUpdateData data) {
        if (data.name() != null){
            this.name = data.name();
        }
        if (data.telephone() != null) {
            this.telephone = data.telephone();
        }
        if (data.address() !=null){
            this.address.updateInfo(data.address());
        }
    }

    public void delete() {
        this.active = false;
    }
}

