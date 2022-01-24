package ir.maktab.homeservicesystem.dto.mapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubServiceCreateParam {
    private String name;
    private Integer mainCategoryId;
}
