package com.luminary.apieden.model.database;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "cards")
public class Card {

    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "fk_user_id", nullable = false)
    @NotNull(message = "The 'fkUserId' field must be passed")
    private User user;

    @Column(name = "card_number")
    @NotNull(message = "The 'cardNumber' field must be passed")
    private String cardNumber;

    @Column(name = "cvv")
    @Size(message = "The 'cvv' must have not pass the 3 digits limit", min = 3, max = 3)
    @NotNull(message = "The 'cvv' field must be passed")
    private String cvv;

    @Column(name = "cvc")
    @Size(message = "The 'cvc' must have not pass the 3 digits limit", min = 3, max = 3)
    @NotNull(message = "The 'cvc' field must be passed")
    private String cvc;

    @Column(name = "validity")
    @Size(message = "The 'validity' must have not pass the 5 digits limit", min = 5, max = 5)
    @NotNull(message = "The 'validity' field must be passed")
    private String validity;

    public Card(String cardNumber, String cvv, String cvc, String validity) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.cvc = cvc;
        this.validity = validity;
    }

    public Card() {}
}
