package ir.maktab.homeservicesystem.controller;

import ir.maktab.homeservicesystem.dto.user.UserChangePasswordEntity;
import ir.maktab.homeservicesystem.dto.user.expert.ExpertCreateDto;
import ir.maktab.homeservicesystem.dto.user.UserChangePasswordResult;
import ir.maktab.homeservicesystem.dto.user.expert.ExpertCreateEntity;
import ir.maktab.homeservicesystem.dto.user.expert.ExpertUpdateEntity;
import ir.maktab.homeservicesystem.dto.user.expert.ExpertUpdateResult;
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
    public ResponseEntity<ExpertCreateDto> save(@ModelAttribute ExpertCreateEntity expertCreateEntity) throws IOException {
        ExpertCreateDto response = expertService.saveExpert(expertCreateEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<UserChangePasswordResult> changePassword(@RequestBody UserChangePasswordEntity changePasswordEntity, @PathVariable int id) {
        changePasswordEntity.setUserId(id);
        UserChangePasswordResult userChangePasswordResult = expertService.changePassword(changePasswordEntity);
        return ResponseEntity.ok(userChangePasswordResult);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpertUpdateResult> update(@PathVariable int id, @RequestBody ExpertUpdateEntity expertUpdateEntity) {
        expertUpdateEntity.setId(id);
        ExpertUpdateResult expertUpdate = null;
        try {
            expertUpdate = expertService.updateExpert(expertUpdateEntity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(expertUpdate);
    }
}
