package com.store.litflix.service.impl;

import com.store.litflix.dto.user.UserRegistrationRequestDto;
import com.store.litflix.dto.user.UserResponseDto;
import com.store.litflix.exception.RegistrationException;
import com.store.litflix.mapper.UserMapper;
import com.store.litflix.model.Role;
import com.store.litflix.model.RoleName;
import com.store.litflix.model.User;
import com.store.litflix.repository.roles.RoleRepository;
import com.store.litflix.repository.user.UserRepository;
import com.store.litflix.service.UserService;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public UserResponseDto registerUser(UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new RegistrationException(
                    "User with email " + requestDto.getEmail() + " already exists");
        }

        User user = userMapper.toEntity(requestDto);
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));

        Role userRole = roleRepository.findByRoleName(RoleName.ROLE_USER);
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        user.setRoles(roles);

        User savedUser = userRepository.save(user);

        return userMapper.toUserResponse(savedUser);
    }
}
