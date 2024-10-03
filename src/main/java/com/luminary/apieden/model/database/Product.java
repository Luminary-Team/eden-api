package com.luminary.apieden.model.database;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "products")
public class Product {
    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(hidden = true)
    private long id;

    @ManyToOne
    @JoinColumn(name = "fk_usage_time_id", nullable = false)
    @NotNull(message = "O campo 'usageTime' precisa ser passado")
    @Schema(name = "usageTime", description = "ID of the corresponding usage time", example = "1")
    private UsageTime usageTime;

    @ManyToOne
    @JoinColumn(name = "fk_condition_type_id", nullable = false)
    @NotNull(message = "O campo 'conditionType' precisa ser passado")
    @Schema(name = "conditionType", description = "ID of the corresponding condition type", example = "1")
    private ConditionTypes conditionType;

    @ManyToOne
    @JoinColumn(name = "fk_user_id", nullable = false)
    @NotNull(message = "O campo 'user' precisa ser passado")
    @Schema(name = "user", description = "The information about the user responsible for the creation of the user", implementation = User.class)
    private User user;

    @Column(name = "title", nullable = false, unique = true)
    @NotBlank(message = "O campo 'title' não pode ser vazio")
    @Size(message = "O campo 'title' não pode passar de 45 caracteres", max = 45)
    @Schema(name = "title", description = "Title of the product", example = "PC Gamer")
    private String title;

    @Column(name = "description", nullable = false)
    @NotBlank(message = "O campo 'description' precisa ser passado")
    @Size(message = "O campo 'usageTime' não pode passar de 45 caracteres", max = 90)
    @Schema(name = "description", description = "Description of the product", example = "")
    private String description;

    @Column(name = "price", nullable = false)
    @NotNull(message = "O campo 'price' precisa ser passado")
    @Min(message = "O campo 'price' precisa ter seu valor acima de 0", value = 0)
    private float price;

    @Column(name = "max_price", nullable = false)
    @Min(message = "O campo 'maxPrice' não pode ser menor que 0", value = 0)
    private float maxPrice;

    @Column(name = "sender_zip_code", nullable = false)
    @NotBlank(message = "O campo 'senderZipCode' não pode ser vazio")
    @Size(message = "O campo 'senderZipCode' precisa ter exatamente 8 caracteres", min = 8, max = 8)
    private String senderZipCode;

    @Column(name = "rating")
    @NotNull(message = "O campo 'rating' precisa ser passado")
    @Min(message = "O campo 'rating' não pode ser abaixo de 0", value = 0)
    private float rating;
}
