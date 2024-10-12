package com.luminary.apieden.model.database;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "cards")
@Schema(name = "CardSchema", description = "Schema of cards")
public class Card {
    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "fk_user_id")
    @NotNull(message = "O campo 'userId' precisa ser passado")
    @Schema(description = "The respective ID of the user owner of this card", example = "6")
    private long userId;

    @Column(name = "card_number")
    @NotBlank(message = "O campo 'cardNumber' precisa ser passado")
    @Size(message = "O campo 'cardNumber' precisa ter 16 caracteres", min = 16, max = 16)
    @Schema(name = "cardNumber", description = "The cardNumber of the card", example = "1111222233334444")
    private String cardNumber;

    @Column(name = "cvv")
    @NotBlank(message = "O campo 'cvv' precisa ser passado")
    @Size(message = "O campo 'cvv' precisa ter 3 caracteres", min = 3, max = 3)
    @Schema(name = "cvv", description = "The cvv of the card", example = "123")
    private String cvv;

    @Column(name = "cvc")
    @Size(message = "O campo 'cvc' precisa ter 3 caracteres", min = 3, max = 3)
    @Schema(hidden = true)
    private String cvc;

    @Column(name = "validity")
    @NotBlank(message = "O campo 'validity' precisa ser passado")
    @Size(message = "O campo 'validity' precisa ter 5 caracteres", min = 5, max = 5)
    @Schema(name = "validity", description = "The validity of the card", example = "01/27")
    private String validity;
}
