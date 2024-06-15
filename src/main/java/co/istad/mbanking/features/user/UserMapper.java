package co.istad.mbanking.features.user;

import co.istad.mbanking.domain.User;
import co.istad.mbanking.features.auth.dto.RegisterRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(RegisterRequest registerRequest);

}
