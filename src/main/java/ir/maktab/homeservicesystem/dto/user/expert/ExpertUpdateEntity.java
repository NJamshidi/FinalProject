package ir.maktab.homeservicesystem.dto.user.expert;

import ir.maktab.homeservicesystem.data.entities.users.Expert;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExpertUpdateEntity {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String currentPassword;
    private MultipartFile profileImgFile;

    public Expert toEntity() throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(profileImgFile.getOriginalFilename()));
        if (fileName.contains("..")) {
            System.out.println("not a valid file");
        }

        return Expert.builder()
                .id(this.id)
                .firstName(this.firstName)
                .lastName(this.lastName)
                .email(this.email)
                .password(this.currentPassword)
                .profileImage(Base64.getEncoder().encodeToString(profileImgFile.getBytes()))
                .build();
    }
}