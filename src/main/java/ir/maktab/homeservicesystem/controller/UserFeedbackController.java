package ir.maktab.homeservicesystem.controller;

import ir.maktab.homeservicesystem.dto.userFeedback.UserFeedbackCreateEntity;
import ir.maktab.homeservicesystem.dto.userFeedback.UserFeedbackCreateResult;
import ir.maktab.homeservicesystem.service.UserFeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/userFeedbacks")
public class UserFeedbackController {

    private final UserFeedbackService userFeedbackService;

    @PostMapping
    public ResponseEntity<UserFeedbackCreateResult> addReview(@RequestBody UserFeedbackCreateEntity createEntity) {
        UserFeedbackCreateResult UserFeedbackResult = userFeedbackService.save(createEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserFeedbackResult);
    }
}
