package project.java.intensive.api.domain.adress;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class Address {
    private String street;
    private String neighborhood;
    private String postalcode;
    private String city;
    private String state;
    private String number;
    private String additionalinfo;


    public Address(AddressData data) {
        this.street = data.street();
        this.neighborhood = data.neighborhood();
        this.postalcode = data.postalcode();
        this.city = data.city();
        this.state = data.state();
        this.number = data.number();
        this.additionalinfo = data.additionalinfo();
    }

    public void updateInfo(AddressData data) {
        if (data.street() != null){
            this.street = data.street();
        }
        if (data.neighborhood() != null) {
            this.neighborhood = data.neighborhood();
        }
        if (data.postalcode() !=null){
            this.postalcode = (data.postalcode());
        }
        if (data.city() !=null){
            this.city = (data.city());
        }
        if (data.state() !=null){
            this.state = (data.state());
        }
        if (data.number() !=null){
            this.number = (data.number());
        }
        if (data.additionalinfo() !=null){
            this.additionalinfo = (data.additionalinfo());
        }
        
    }
}

