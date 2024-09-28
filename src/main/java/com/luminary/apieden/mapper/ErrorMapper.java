package com.luminary.apieden.mapper;

import com.luminary.apieden.model.exception.HttpError;
import com.luminary.apieden.model.response.ErrorResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ErrorMapper {
    ErrorResponse toErrorResponse(HttpError httpError);
}