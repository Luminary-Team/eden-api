package com.luminary.apieden.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingRequest {
    private long userAppraiserId;

    private long userAppraisedId;

    private float rating;
}
