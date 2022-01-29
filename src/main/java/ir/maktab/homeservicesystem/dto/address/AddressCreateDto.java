package ir.maktab.homeservicesystem.dto.address;

import ir.maktab.homeservicesystem.data.entities.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressCreateDto {

    private int id;
    private String province;
    private String city;
    private String street;
    private String alley;
    private String plaque;

    public AddressCreateDto toDto(Address address) {
        return AddressCreateDto.builder()
                .id(address.getId())
                .province(address.getProvince())
                .city(address.getCity())
                .street(address.getStreet())
                .alley(address.getAlley())
                .plaque(address.getPlaque())
                .build();
    }
}