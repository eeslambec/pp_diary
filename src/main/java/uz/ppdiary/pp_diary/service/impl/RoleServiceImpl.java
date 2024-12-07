package uz.ppdiary.pp_diary.service.impl;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.ppdiary.pp_diary.dto.request.RoleCreateDto;
import uz.ppdiary.pp_diary.entity.Role;
import uz.ppdiary.pp_diary.exceptions.NotFoundException;
import uz.ppdiary.pp_diary.repository.RoleRepository;
import uz.ppdiary.pp_diary.service.RoleService;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public RoleCreateDto save(@NotNull RoleCreateDto roleCreateDto) {
        Role role = Role.builder()
                .name(roleCreateDto.getName())
                .description(roleCreateDto.getDescription())
                .build();
        return new RoleCreateDto(roleRepository.save(role));
    }

    @Override
    public Role getById(Long id) {
        return roleRepository.findById(id).orElseThrow(() -> new NotFoundException("Role"));
    }

    @Override
    public Role getByName(String name) {
        return roleRepository.findByName(name).orElseThrow(() -> new NotFoundException("Role"));
    }

    @Override
    public Page<Role> getAllRoles(Pageable pageable) {
        return roleRepository.findAll(pageable);
    }
}
