package com.my.pro.util.converter;

public interface EntityMapping<DTO_REQUEST, ENTITY, DTO_RESPONSE> {

    ENTITY mapRequestToEntity(DTO_REQUEST request);

    DTO_RESPONSE mapEntityToResponse(ENTITY entity);
}