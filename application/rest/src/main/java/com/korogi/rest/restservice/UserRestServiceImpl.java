package com.korogi.rest.restservice;

import static com.korogi.core.domain.User.newUser;
import static java.lang.Boolean.FALSE;
import static lombok.AccessLevel.PUBLIC;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;
import com.korogi.api.UserRestService;
import com.korogi.core.domain.User;
import com.korogi.core.persistence.user.UserRepository;
import com.korogi.dto.UserDTO;
import com.korogi.rest.exception.ResourceNotFoundException;
import com.korogi.rest.mapper.EntityToDTOResourceMapper;
import com.korogi.rest.security.UserPrincipal;
import com.korogi.rest.util.EncryptorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Daan Peelman
 */
@RestController
@RequestMapping(value = "/users", produces = { APPLICATION_JSON_VALUE })
@Transactional(readOnly = true)

@RequiredArgsConstructor(access = PUBLIC)
public class UserRestServiceImpl implements UserRestService {
    private final UserRepository userRepository;
    private final EntityToDTOResourceMapper entityToDTOResourceMapper;
    private final EncryptorUtil encryptorUtil;

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    @ResponseStatus(CREATED)
    @Transactional
    @Override
    public void saveUser(
            @RequestBody UserDTO userDTO
    ) {
        UserPrincipal oidcUser = getOidcUser();

        byte[] salt = createSalt();

        // TODO roles
        User user = newUser()
                .providerId(oidcUser.getName())
                .email(encryptorUtil.encrypt(salt, oidcUser.getEmail()))
                .username(userDTO.getUsername())
                .biography(userDTO.getBiography())
                .salt(salt)
                .activationCode(UUID.randomUUID().toString())
                .activated(FALSE)
                .build();

        userRepository.save(user);
    }

    private byte[] createSalt() {
        Random random = new SecureRandom();
        byte[] bytes = new byte[16];
        random.nextBytes(bytes);

        return bytes;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "current")
    @ResponseStatus(OK)
    @Override
    public @ResponseBody EntityModel<UserDTO> getCurrentUser() {
        User user = userRepository.findByProviderId(getOidcUser().getName()).orElseThrow(ResourceNotFoundException::new);

        return entityToDTOResourceMapper.toDTOResource(user);
    }

    private UserPrincipal getOidcUser() {
        return (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
