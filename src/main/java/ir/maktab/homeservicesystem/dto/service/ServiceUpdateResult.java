package ir.maktab.homeservicesystem.dto.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceUpdateResult {
    private int id;
    private boolean successFull;
}