package com.luminary.apieden.service;

import com.luminary.apieden.mapper.ProductMapper;
import com.luminary.apieden.model.database.ConditionType;
import com.luminary.apieden.model.database.Product;
import com.luminary.apieden.model.database.UsageTime;
import com.luminary.apieden.model.database.User;
import com.luminary.apieden.model.exception.HttpError;
import com.luminary.apieden.model.request.ProductRequest;
import com.luminary.apieden.repository.ConditionTypeRepository;
import com.luminary.apieden.repository.ProductRepository;
import com.luminary.apieden.repository.UsageTimeRepository;
import com.luminary.apieden.repository.UserRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;
    private final ConditionTypeRepository conditionTypeRepository;
    private final UsageTimeRepository usageTimeRepository;
    private final UserRepository userRepository;
    private final ProductMapper productMapper;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public List<Product> findProductByUserId(String userId) {
        return productRepository.findByUserId(Long.parseLong(userId));
    }

    public List<Product> findProductByTitleLike(String title) {
        return productRepository.findByTitleLike(title);

    }

    public Product findProductById(String productId) {
        log.info("Fetching product by id: {}", productId);
        return productRepository.findById(Long.valueOf(productId))
                .orElseThrow(() -> new HttpError(HttpStatus.BAD_REQUEST, "Produto não encontrado"));
    }

    public Product register(ProductRequest productRequest) {
        log.info("Registering product");
        User user = userRepository.findByEmail(productRequest.getUserEmail())
                .orElseThrow(() -> new HttpError(HttpStatus.BAD_REQUEST, "E-mail não encontrado"));
        usageTimeRepository.findById(productRequest.getUsageTimeId())
                .orElseThrow(() -> new HttpError(HttpStatus.BAD_REQUEST, "'Tempo de uso' não encontrado"));
        conditionTypeRepository.findById(productRequest.getConditionTypeId())
                .orElseThrow(() -> new HttpError(HttpStatus.BAD_REQUEST, "'Tipo de condição' não encontrado"));
        Product product = productMapper.toProduct(productRequest);
        product.setUserId(user.getId());
        productRepository.save(product);
        log.info("Product registered with success: {}", product);
        return product;
    }

    public void partialUpdate(String id, Map<String, Object> request) throws HttpError {
        log.info("Entering in partialUpdate method.");
        Product product = productRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new HttpError(HttpStatus.BAD_REQUEST, "Produto não encontrado"));
        boolean verifyVariable = false;
        try {
            if (request.containsKey("usageTimeId")) {
                Long usageTimeId = Long.valueOf((Integer) request.get("usageTimeId"));
                log.info("[PRODUCT] usageTime {} being updated to {}", product.getUsageTimeId(), usageTimeId);
                usageTimeRepository.findById(usageTimeId)
                        .orElseThrow(() -> new HttpError(HttpStatus.BAD_REQUEST, "'Tempo de uso' não encontrado"));
                product.setUsageTimeId(usageTimeId);
                verifyVariable = true;
                log.info("[PRODUCT] usageTime updated");
            }
            if (request.containsKey("conditionTypeId")) {
                Long conditionTypeId = Long.valueOf((Integer) request.get("conditionTypeId"));
                log.info("[PRODUCT] conditionType {} being updated to {}", product.getConditionTypeId(), conditionTypeId);
                conditionTypeRepository.findById(conditionTypeId)
                        .orElseThrow(() -> new HttpError(HttpStatus.BAD_REQUEST, "'Tipo de condição' não encontrado"));
                product.setConditionTypeId(conditionTypeId);
                verifyVariable = true;
                log.info("[PRODUCT] conditionType updated");
            }
            if (request.containsKey("title")) {
                log.info("[PRODUCT] Title {} being updated to {}", product.getTitle(), (String) request.get("title"));
                product.setTitle((String) request.get("title"));
                verifyVariable = true;
                log.info("[PRODUCT] Title updated");
            }
            if (request.containsKey("description")) {
                log.info("[PRODUCT] Description {} being updated to {}", product.getDescription(), (String) request.get("description"));
                product.setDescription((String) request.get("description"));
                verifyVariable = true;
                log.info("[PRODUCT] Description updated");
            }
            if (request.containsKey("price")) {
                log.info("[PRODUCT] Price being updated.");
                product.setPrice((Double) request.get("price"));
                verifyVariable = true;
                log.info("[PRODUCT] price updated");
            }
            if (request.containsKey("maxPrice")) {
                log.info("[PRODUCT] Max price {} being updated to {}", product.getMaxPrice(), (Float) request.get("maxPrice"));
                product.setMaxPrice((Double) request.get("maxPrice"));
                verifyVariable = true;
                log.info("[PRODUCT] Max price updated");
            }
            if (request.containsKey("senderZipCode")) {
                log.info("[PRODUCT] Sender zip code {} being updated to {}", product.getSenderZipCode(), (String) request.get("senderZipCode"));
                product.setSenderZipCode((String) request.get("senderZipCode"));
                verifyVariable = true;
                log.info("[PRODUCT] Sender zip code updated");
            }
            if (request.containsKey("rating")) {
                log.info("[PRODUCT] Rating {} being updated to {}", product.getSenderZipCode(), (String) request.get("senderZipCode"));
                product.setRating((Double) request.get("rating"));
                verifyVariable = true;
                log.info("[PRODUCT] Rating updated");
            }
        } catch (ClassCastException clc) {
            throw new HttpError(HttpStatus.BAD_REQUEST, "Problema ao receber número, por favor, insira o '.' ao número(ex. 2.0)");
        }
        if (!verifyVariable) {
            log.warn("[PRODUCT] None valid field passed.");
            throw new HttpError(HttpStatus.BAD_REQUEST, "Nenhum campo válido de atualização foi passado.");
        }

        log.info("[PRODUCT] Starting attributes validation.");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        if (!violations.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder("Erros de validação:");
            for (ConstraintViolation<Product> violation : violations) {
                errorMessage.append(" /  ").append(violation.getMessage());
            }
            throw new HttpError(HttpStatus.BAD_REQUEST, errorMessage.toString());
        }

        log.info("[PRODUCT] Attributes validated");
        log.info("[PRODUCT] Saving product in database.");
        productRepository.save(product);
        log.info("[PRODUCT] Product saved in database.");
    }

    public void deleteById(String id) throws HttpError {
        log.info("Deleting product {}", id);
        productRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new HttpError(HttpStatus.BAD_REQUEST, "Produto não encontrado"));
        log.info("[PRODUCT] Deleting product");
        productRepository.deleteById(Long.valueOf(id));
        log.info("[PRODUCT] Product deleted");
    }
}
