package com.luminary.apieden.mapper;

import com.luminary.apieden.model.database.Rating;
import com.luminary.apieden.model.request.RatingRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RatingMapper {
    Rating toRating(RatingRequest request);
}
