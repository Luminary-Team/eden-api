package com.luminary.apieden.model.database;

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
public class Card {

    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "fk_user_id")
    @NotNull(message = "O campo 'userId' precisa ser passado")
    private long userId;

    @Column(name = "card_number")
    @NotBlank(message = "O campo 'cardNumber' precisa ser passado")
    @Size(message = "O campo 'cardNumber' precisa ter 16 caracteres", min = 16, max = 16)
    private String cardNumber;

    @Column(name = "cvv")
    @NotBlank(message = "O campo 'cvv' precisa ser passado")
    @Size(message = "O campo 'cvv' precisa ter 3 caracteres", min = 3, max = 3)
    private String cvv;

    @Column(name = "cvc")
    @NotBlank(message = "O campo 'cvc' precisa ser passado")
    @Size(message = "O campo 'cvc' precisa ter 3 caracteres", min = 3, max = 3)
    private String cvc;

    @Column(name = "validity")
    @NotBlank(message = "O campo 'validity' precisa ser passado")
    @Size(message = "O campo 'validity' precisa ter 5 caracteres", min = 5, max = 5)
    private String validity;
}
