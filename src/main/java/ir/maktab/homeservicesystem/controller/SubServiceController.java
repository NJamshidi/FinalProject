package ir.maktab.homeservicesystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import ir.maktab.homeservicesystem.dto.ExpertDto;
import ir.maktab.homeservicesystem.dto.SubServiceDto;
import ir.maktab.homeservicesystem.dto.mapper.AddExpertToSubServiceResult;
import ir.maktab.homeservicesystem.dto.mapper.ServiceCreateResult;
import ir.maktab.homeservicesystem.dto.mapper.SubServiceCreateParam;
import ir.maktab.homeservicesystem.service.SubServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subServices")
public class SubServiceController {

    private final SubServiceService subServiceService;

    @Operation(summary = "Create new subService")
    @PostMapping
    public ResponseEntity<ServiceCreateResult> createSubService(@RequestBody SubServiceCreateParam createParam) {
        ServiceCreateResult serviceCreateResult = subServiceService.saveSubService(createParam);
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceCreateResult);
    }

    @PutMapping("/{subServiceId}/experts/{expertId}")
    public ResponseEntity<AddExpertToSubServiceResult> addExpertToSubServ(
            @PathVariable int subServiceId, @PathVariable int expertId) {
        AddExpertToSubServiceResult addExpertToSubServiceResult = subServiceService.addExpert(expertId, subServiceId);
        return ResponseEntity.ok(AddExpertToSubServiceResult);
    }

    @DeleteMapping("/{subServiceId}/experts/{expertId}")
    public ResponseEntity<SubServiceDto> removeExpertFromSubServiceResult(
            @PathVariable int subServiceId, @PathVariable int expertId) {
        SubServiceDto removeExpertFromSubServiceResult = subServiceService.removeExpert(expertId, subServiceId);
        return ResponseEntity.ok(removeExpertFromSubServiceResult);
    }

}
