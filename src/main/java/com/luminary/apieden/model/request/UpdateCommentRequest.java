package com.luminary.apieden.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateCommentRequest {
    @NotBlank(message = "The 'comment' field mustn't be blank")
    @Size(message = "The 'comment' must have not pass the 90 digits limit", max = 90)
    private String comment;
}
