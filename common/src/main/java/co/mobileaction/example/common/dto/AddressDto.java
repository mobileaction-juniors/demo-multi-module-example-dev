package co.mobileaction.example.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter

public class AddressDto
{
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private GeoDto geo;
}
