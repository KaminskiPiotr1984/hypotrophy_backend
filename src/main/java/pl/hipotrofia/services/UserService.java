package pl.hipotrofia.services;

import org.springframework.stereotype.Service;
import pl.hipotrofia.converters.UserDtoConverter;
import pl.hipotrofia.dto.UserDto;
import pl.hipotrofia.entities.User;
import pl.hipotrofia.repositories.UserRepository;
import pl.hipotrofia.validators.UserValidator;

import java.time.LocalDate;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserValidator userValidator;
    private final UserDtoConverter userDtoConverter;
    private final RoleService roleService;

    public UserService(UserRepository userRepository,
                       UserValidator userValidator,
                       UserDtoConverter userDtoConverter,
                       RoleService roleService) {
        this.userRepository = userRepository;
        this.userValidator = userValidator;
        this.userDtoConverter = userDtoConverter;
        this.roleService = roleService;
    }

    public boolean registerUser(UserDto userDto) {

        if (userValidator.isTheUserValid(userDto)) {
            User user = userDtoConverter.convertFromDto(userDto);
            user.setCreated(LocalDate.now());
            user.setRole(roleService.getRole(3L));
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

}
