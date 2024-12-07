package uz.ppdiary.pp_diary.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.ppdiary.pp_diary.dto.request.RoleCreateDto;
import uz.ppdiary.pp_diary.entity.Role;

import java.util.List;

@Service
public interface RoleService {
    RoleCreateDto save(RoleCreateDto roleCreateDto);
    Role getById(Long id);
    Role getByName(String name);
    Page<Role> getAllRoles(Pageable pageable);

}
