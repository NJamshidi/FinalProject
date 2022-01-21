package ir.maktab.homeservicesystem.dto.mapper;

import ir.maktab.homeservicesystem.data.entities.users.Admin;
import ir.maktab.homeservicesystem.data.entities.users.Customer;
import ir.maktab.homeservicesystem.dto.AdminDto;
import ir.maktab.homeservicesystem.dto.CustomerDto;
import org.springframework.stereotype.Component;
@Component
public class AdminMapper {
        public AdminDto toDto(Admin admin) {
            return AdminDto.builder()
                    .id(admin.getId())
                    .build();
        }
        public Admin toEntity(AdminDto adminDto){
            return (Admin) Admin.builder()
                    .id(adminDto.getId())
                    .build();

    }
}
