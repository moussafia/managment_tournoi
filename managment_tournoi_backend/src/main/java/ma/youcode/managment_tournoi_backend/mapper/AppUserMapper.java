package ma.youcode.managment_tournoi_backend.mapper;

import ma.youcode.managment_tournoi_backend.dto.appUserFileDto.AppUserRequest;
import ma.youcode.managment_tournoi_backend.entity.AppUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AppUserMapper {
    AppUserMapper INSTANCE =
            Mappers.getMapper(AppUserMapper.class);
    @Mapping(target =  "isDeleted", ignore = true)
    @Mapping(target =  "id", ignore = true)
    AppUser AppUserFileDtoToAppUser(AppUserRequest appUserRequest);
}
