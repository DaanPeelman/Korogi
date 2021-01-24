package com.korogi.rest.mapper.user;

import java.util.Collections;
import java.util.List;
import com.korogi.core.domain.User;
import com.korogi.dto.UserDTO;
import com.korogi.rest.mapper.BaseResourceMapper;
import org.mapstruct.Mapper;
import org.springframework.hateoas.Link;

/**
 * @author Daan Peelman
 */
@Mapper
abstract class UserResourceMapper extends BaseResourceMapper<UserDTO, User> {

    public UserResourceMapper() {
        super(User.class);
    }

    @Override
    protected List<Link> createLinks(User user) {
        return Collections.emptyList();
    }
}
