package io.github.shorv.proma.appuser;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final ModelMapper modelMapper;

    public List<AppUserDTO> getUsers() {
        return appUserRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public void createUser(AppUserDTO appUserDTO) {
        appUserRepository.save(convertToEntity(appUserDTO));
    }

    private AppUserDTO convertToDto(AppUser appUser) {
        return modelMapper.map(appUser, AppUserDTO.class);
    }

    private AppUser convertToEntity(AppUserDTO appUserDTO) throws ParseException {
        return modelMapper.map(appUserDTO, AppUser.class);
    }
}
