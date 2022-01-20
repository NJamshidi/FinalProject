package ir.maktab.homeservicesystem.dto;


//@Data
//@Builder
public class AdminDto {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String password;

    @Override
    public String toString() {
        return "AdminDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
