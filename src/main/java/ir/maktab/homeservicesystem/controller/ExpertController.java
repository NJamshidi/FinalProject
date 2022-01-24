package ir.maktab.homeservicesystem.controller;

import ir.maktab.homeservicesystem.dto.ExpertDto;
import ir.maktab.homeservicesystem.dto.mapper.UserChangePasswordParam;
import ir.maktab.homeservicesystem.dto.mapper.UserChangePasswordResult;
import ir.maktab.homeservicesystem.service.ExpertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/experts")
public class ExpertController {

    private final ExpertService expertService;

    @PostMapping
    public ResponseEntity<ExpertDto> save(@ModelAttribute ExpertDto expertDto) throws IOException {
        ExpertDto response = expertService.save(expertDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<UserChangePasswordResult> changePassword(@RequestBody UserChangePasswordParam changePasswordParam, @PathVariable int id) {
        changePasswordParam.setUserId(id);
        UserChangePasswordResult userChangePasswordResult = expertService.changePassword(changePasswordParam);
        return ResponseEntity.ok(userChangePasswordResult);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpertDto> update(@PathVariable int id, @RequestBody ExpertDto expertDto) {
        expertDto.setId(id);
        ExpertDto expertUpdate = null;
        expertUpdate = expertService.updateExpert(expertDto);
        return ResponseEntity.ok(expertUpdate);
    }
}
