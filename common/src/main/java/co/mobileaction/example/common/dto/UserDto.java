package co.mobileaction.example.common.dto;

import lombok.*;

/**
 * @author Mehmet Akif Sahin
 * @date 03.07.2024
 * @time 10:51
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserDto
{
    private Long id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ToString
    static class Address
    {
        private String street;
        private String suite;
        private String city;
        private String zipcode;
        private Geo geo;

        @Getter
        @NoArgsConstructor
        @AllArgsConstructor
        @Builder
        @ToString
        static class Geo
        {
            private Double lat;
            private Double lng;
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ToString
    static class Company
    {
        private String name;
        private String catchPhrase;
        private String bs;
    }
}
