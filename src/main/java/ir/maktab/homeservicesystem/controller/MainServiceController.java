package ir.maktab.homeservicesystem.controller;

import ir.maktab.homeservicesystem.data.entities.services.MainServiceList;
import ir.maktab.homeservicesystem.dto.service.ServiceCreateResult;
import ir.maktab.homeservicesystem.dto.service.ServiceUpdateResult;
import ir.maktab.homeservicesystem.dto.service.mainService.MainServiceCreateDto;
import ir.maktab.homeservicesystem.dto.service.mainService.MainServiceCreateEntity;
import ir.maktab.homeservicesystem.dto.service.mainService.MainServiceUpdateEntity;
import ir.maktab.homeservicesystem.service.MainServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mainServices")
public class MainServiceController {

    private final MainServiceService mainServiceService;

    @PostMapping
    public ResponseEntity<ServiceCreateResult> createMainService(@RequestBody MainServiceCreateEntity mainServiceCreateEntity) {
        ServiceCreateResult serviceResult = mainServiceService.saveMainService(mainServiceCreateEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceResult);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceUpdateResult> updateMainService(@RequestBody MainServiceUpdateEntity mainServiceUpdateEntity, @PathVariable int id) {
        mainServiceUpdateEntity.setId(id);
        ServiceUpdateResult updateResult = mainServiceService.updateMainService(mainServiceUpdateEntity);
        return ResponseEntity.ok(updateResult);
    }

    @GetMapping
    public ResponseEntity<MainServiceList> loadAll() {
        MainServiceList mainServiceList = mainServiceService.findAllMainServices();
        return ResponseEntity.ok(mainServiceList);
    }

    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public ResponseEntity<MainServiceCreateDto> loadById(@PathVariable int id) {
        MainServiceCreateDto mainServiceDto = mainServiceService.findMainServiceByIdReturnDto(id);
        return ResponseEntity.ok(mainServiceDto);
    }
}
