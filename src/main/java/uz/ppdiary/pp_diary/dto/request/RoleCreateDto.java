package uz.ppdiary.pp_diary.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.ppdiary.pp_diary.entity.Role;

@Getter
@AllArgsConstructor
public class RoleCreateDto {
    private String name;
    private String description;
    public RoleCreateDto(Role role) {
        this.name = role.getName();
        this.description = role.getDescription();
    }
}
