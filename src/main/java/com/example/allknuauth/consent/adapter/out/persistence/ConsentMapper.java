package com.example.allknuauth.consent.adapter.out.persistence;

import com.example.allknuauth.consent.domain.Consent;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ConsentMapper {
    ConsentMapper INSTANCE = Mappers.getMapper(ConsentMapper.class);
    Consent toDomain(ConsentEntity consent);
}
