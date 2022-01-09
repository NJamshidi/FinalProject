package ir.maktab.homeservicesystem.data.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminDto {
    private int id;
    private String userName;
    private String passWord;

    @Override
    public String toString() {
        return "AdminDto{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }
}
