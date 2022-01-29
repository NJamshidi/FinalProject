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
public class ExpertCreateEntity {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private MultipartFile profileImageFile;

    public Expert toEntity() throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(profileImageFile.getOriginalFilename()));
        if (fileName.contains("..")) {
            System.out.println("your file is not valid!");
        }
        return Expert.builder()
                .firstName(this.firstName)
                .lastName(this.lastName)
                .email(this.email)
                .password(this.password)
                .profileImage(Base64.getEncoder().encodeToString(profileImageFile.getBytes()))
                .build();
    }
}