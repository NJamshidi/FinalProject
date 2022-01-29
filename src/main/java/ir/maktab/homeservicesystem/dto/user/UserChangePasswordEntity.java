package ir.maktab.homeservicesystem.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserChangePasswordEntity {
    private int userId;
    private String currentPassword;
    private String newPassword;
    private String newPasswordConfirm;
}