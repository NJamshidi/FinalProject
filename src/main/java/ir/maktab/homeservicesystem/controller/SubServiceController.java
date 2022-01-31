package ir.maktab.homeservicesystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import ir.maktab.homeservicesystem.dto.service.subService.AddExpertToSubService;
import ir.maktab.homeservicesystem.dto.service.ServiceCreateResult;
import ir.maktab.homeservicesystem.dto.service.subService.RemoveExpertFromSubService;
import ir.maktab.homeservicesystem.dto.service.subService.SubServiceCreateEntity;
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
    public ResponseEntity<ServiceCreateResult> createSubService(@RequestBody SubServiceCreateEntity subServiceCreateEntity) {
        ServiceCreateResult serviceCreateResult = subServiceService.saveSubService(subServiceCreateEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceCreateResult);
    }

    @PutMapping("/{subServiceId}/experts/{expertId}")
    public ResponseEntity<AddExpertToSubService> addExpertToSubServ(
            @PathVariable int subServiceId, @PathVariable int expertId) {
        AddExpertToSubService addExpertToSubServiceResult = subServiceService.addExpert(expertId, subServiceId);
        return ResponseEntity.ok(addExpertToSubServiceResult);
    }

    @DeleteMapping("/{subServiceId}/experts/{expertId}")
    public ResponseEntity<RemoveExpertFromSubService> removeExpertFromSubService(
            @PathVariable int subServiceId, @PathVariable int expertId) {
        RemoveExpertFromSubService removeExpertFromSubService = subServiceService.removeExpert(expertId, subServiceId);
        return ResponseEntity.ok(removeExpertFromSubService);
    }

}
