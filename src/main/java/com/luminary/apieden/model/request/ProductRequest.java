package com.luminary.apieden.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {
        @NotNull(message = "O campo 'usageTimeId' precisa ser passado")
        @Schema(name = "usageTimeId", description = "ID of the corresponding usage time", example = "1")
        private long usageTimeId;
        @NotNull(message = "O campo 'conditionTypeId' precisa ser passado")
        @Schema(name = "conditionType", description = "ID of the corresponding condition type", example = "1")
        private long conditionTypeId;
        @NotBlank(message = "O campo 'userEmail' precisa ser passado")
        @Size(message = "O campo 'userEmail' não pode passar de 90 caracteres", max = 90)
        @Schema(name = "userEmail", description = "Email of the user", example = "pedro.gusmao@mail.com")
        private String userEmail;
        @NotNull(message = "O campo 'title' precisa ser passado")
        @Size(message = "O campo 'title' não pode passar de 45 caracteres", max = 45)
        @Schema(name = "title", description = "Title of the product", example = "PC Gamer")
        private String title;
        @NotBlank(message = "O campo 'description' precisa ser passado")
        @Size(message = "O campo 'usageTime' não pode passar de 90 caracteres", max = 90)
        @Schema(name = "description", description = "Description of the product", example = "PC Gamer")
        private String description;
        @NotNull(message = "O campo 'price' precisa ser passado")
        @Min(message = "O campo 'price' precisa ter seu valor acima de 0", value = 0)
        private String price;
        @NotBlank(message = "O campo 'senderZipCode' não pode ser vazio")
        @Size(message = "O campo 'senderZipCode' precisa ter exatamente 8 caracteres", min = 8, max = 8)
        private String senderZipCode;
}
