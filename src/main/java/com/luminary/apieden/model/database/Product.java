package com.luminary.apieden.model.database;

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
    private long id;

    @ManyToOne
    @JoinColumn(name = "fk_usage_time_id", nullable = false)
    @NotNull(message = "The 'usageTime' field must be passed")
    private UsageTime usageTime;

    @ManyToOne
    @JoinColumn(name = "fk_condition_type_id", nullable = false)
    @NotNull(message = "The 'conditionType' field must be passed")
    private ConditionTypes conditionType;

    @ManyToOne
    @JoinColumn(name = "fk_user_id", nullable = false)
    @NotNull(message = "The 'user' field must be passed")
    private User user;

    @Column(name = "title", nullable = false, unique = true)
    @NotBlank(message = "The 'title' field mustn't be blank")
    @Size(message = "The 'title' can't pass 45 digits", max = 45)
    private String title;

    @Column(name = "description", nullable = false)
    @NotBlank(message = "The 'description' field mustn't be blank")
    @Size(message = "The 'description' can't pass 90 digits", max = 90)
    private String description;

    @Column(name = "price", nullable = false)
    @NotNull(message = "The 'price' field must be passed")
    @Min(message = "Minimum price cannot be that lower", value = 1)
    private float price;

    @Column(name = "max_price", nullable = false)
    @Min(message = "Minimum maxPrice cannot be that lower", value = 0)
    private float maxPrice;

    @Column(name = "sender_zip_code", nullable = false)
    @NotBlank(message = "The 'senderZipCode' mustn't be blank")
    @Size(message = "The 'senderZipCode' must have 8 digits", min = 8, max = 8)
    private String senderZipCode;

    @Column(name = "rating")
    @NotNull(message = "The 'rating' field must be passed")
    private float rating;

    @Column(name = "stock")
    @NotNull(message = "The 'stock' field must be passed")
    @Min(message = "Stock cannot be negative", value = 0)
    private int stock;
}
