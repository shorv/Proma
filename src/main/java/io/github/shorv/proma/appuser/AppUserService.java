package io.github.shorv.proma.appuser;

import io.github.shorv.proma.appuser.exception.UserNotFoundException;
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

    public AppUserDTO getUserDTO(Long id) {
        return convertToDto(appUserRepository.findById(id)
                .orElseThrow(UserNotFoundException::new));
    }

    public AppUserDTO updateUser(Long id, AppUserDTO appUserDTO) {
        AppUser userToUpdate = appUserRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        userToUpdate.setEmail(appUserDTO.getEmail());
        userToUpdate.setUsername(appUserDTO.getUsername());
        userToUpdate.setFirstName(appUserDTO.getFirstName());
        userToUpdate.setLastName(appUserDTO.getLastName());
        userToUpdate.setOrganizations(appUserDTO.getOrganizations());
        userToUpdate.setPassword(appUserDTO.getPassword());

        appUserRepository.save(userToUpdate);

        return convertToDto(userToUpdate);
    }

    public void deleteAppUser(Long id) {
        appUserRepository.deleteById(id);
    }

    private AppUserDTO convertToDto(AppUser appUser) {
        return modelMapper.map(appUser, AppUserDTO.class);
    }

    private AppUser convertToEntity(AppUserDTO appUserDTO) throws ParseException {
        return modelMapper.map(appUserDTO, AppUser.class);
    }
}
