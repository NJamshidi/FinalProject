package ir.maktab.homeservicesystem.dto.user.expert;

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
    private List<ExpertCreateDto> experts;

    public void addExpertDto(ExpertCreateDto expertDto) {
        if (experts == null) {
            experts = new ArrayList<>();
        }
        experts.add(expertDto);
    }
}