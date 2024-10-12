package com.luminary.apieden.model.database;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "carts_itens")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id")
    private long id;

    @Column(name = "fk_carts_id")
    @NotNull(message = "O campo 'cart' precisa ser passado")
    @Schema(name = "cartId", description = "Unique ID of the cart in which a product will be inserted")
    private long cartId;

    @Column(name = "fk_product_id")
    @NotNull(message = "O campo 'productId' precisa ser passado")
    @Schema(name = "product", description = "Unique ID of the product in which will be inserted into a cart")
    private long productId;
}
