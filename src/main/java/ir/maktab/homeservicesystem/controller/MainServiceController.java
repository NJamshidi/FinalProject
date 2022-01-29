package ir.maktab.homeservicesystem.controller;

import ir.maktab.homeservicesystem.data.entities.services.MainServiceList;
import ir.maktab.homeservicesystem.dto.service.mainService.MainServiceCreateDto;
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
    public ResponseEntity<MainServiceCreateDto> createMainService(@RequestBody MainServiceCreateDto mainServiceDto) {
        MainServiceCreateDto mainServiceResult = mainServiceService.saveMainService(mainServiceDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(mainServiceResult);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MainServiceCreateDto> updateMainService(@RequestBody MainServiceCreateDto mainServiceDto, @PathVariable int id) {
        mainServiceDto.setId(id);
        MainServiceCreateDto mainServiceUpdateResult = mainServiceService.updateMainService(mainServiceDto);
        return ResponseEntity.ok(mainServiceUpdateResult);
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
