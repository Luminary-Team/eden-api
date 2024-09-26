package com.luminary.apieden.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {
        private long usageTimeId;
        private long conditionTypeId;
        private String email;
        private String title;
        private String description;
        private String price;
        private String senderZipCode;
        private float rating;
        private int stock;
}
