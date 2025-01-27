package project.java.intensive.api.adress;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.catalina.filters.AddDefaultCharsetFilter;

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

    public Address(){

    }

    public Address(AddressData data) {
        this.street = data.street();
        this.neighborhood = data.neighborhood();
        this.postalcode = data.postalcode();
        this.city = data.city();
        this.state = data.state();
        this.number = data.number();
        this.additionalinfo = data.additionalinfo();
    }
}

