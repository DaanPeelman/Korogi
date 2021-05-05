package com.korogi.api;

import com.korogi.dto.UserDTO;
import org.springframework.hateoas.EntityModel;

/**
 * @author Daan Peelman
 */
public interface UserRestService {
    void saveUser(UserDTO userDTO);

    EntityModel<UserDTO> getCurrentUser();
}
