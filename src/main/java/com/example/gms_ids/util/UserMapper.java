package com.example.gms_ids.util;
import com.example.gms_ids.dto.request.UserRequest;
import com.example.gms_ids.table.User;
import lombok.RequiredArgsConstructor;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface UserMapper {

    User toEntity(UserRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastUpdatedBy", ignore = true)
    @Mapping(target = "lastUpdatedDate", ignore = true)
    void updateEntity(@MappingTarget User entity, UserRequest request);}

