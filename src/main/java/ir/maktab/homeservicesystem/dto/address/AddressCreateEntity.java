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
public class AddressCreateEntity {

    private String province;
    private String city;
    private String street;
    private String alley;
    private String plaque;

    public Address toEntity() {
        return Address.builder()
                .province(this.province)
                .city(this.city)
                .street(this.street)
                .alley(this.alley)
                .plaque(this.plaque)
                .build();
    }
}