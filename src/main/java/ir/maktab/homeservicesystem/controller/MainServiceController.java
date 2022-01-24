package ir.maktab.homeservicesystem.controller;

import ir.maktab.homeservicesystem.data.entities.services.MainServiceList;
import ir.maktab.homeservicesystem.dto.MainServiceDto;
import ir.maktab.homeservicesystem.service.MainServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mainCategories")
public class MainServiceController {

    private final MainServiceService mainServiceService;

    @PostMapping
    public ResponseEntity<MainServiceDto> createMainService(@RequestBody MainServiceDto mainServiceDto) {
        MainServiceDto mainServiceResult = mainServiceService.saveMainService(mainServiceDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(mainServiceResult);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MainServiceDto> updateMainService(@RequestBody MainServiceDto mainServiceDto, @PathVariable int id) {
        mainServiceDto.setId(id);
        MainServiceDto mainServiceUpdateResult = mainServiceService.updateMainService(mainServiceDto);
        return ResponseEntity.ok(mainServiceUpdateResult);
    }

    @GetMapping
    public ResponseEntity<MainServiceList> loadAll() {
        MainServiceList mainServiceList = mainServiceService.loadAll();
        return ResponseEntity.ok(mainServiceList);
    }

    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public ResponseEntity<MainServiceDto> loadById(@PathVariable int id) {
        MainServiceDto mainServiceDto = mainServiceService.loadByIdReturnDto(id);
        return ResponseEntity.ok(mainServiceDto);
    }
}
