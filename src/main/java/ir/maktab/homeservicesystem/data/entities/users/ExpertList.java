package ir.maktab.homeservicesystem.data.entities.users;

import ir.maktab.homeservicesystem.dto.ExpertDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExpertList {
    private List<ExpertDto> experts;

    public void addExpertDto(ExpertDto expertDto) {
        if (experts == null) {
            experts = new ArrayList<>();
        }
        experts.add(expertDto);
    }
}