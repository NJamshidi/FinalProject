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
public class AddressUpdateEntity {

    private int id;
    private String province;
    private String city;
    private String street;
    private String alley;
    private String plaque;

    public Address toEntity() {
        return Address.builder()
                .id(this.id)
                .province(this.province)
                .city(this.city)
                .street(this.street)
                .alley(this.alley)
                .plaque(this.plaque)
                .build();
    }
}