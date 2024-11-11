package com.luminary.apieden.repository;

import com.luminary.apieden.model.database.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByCpf(String cpf);
    Optional<User> findByEmail(String email);
    Optional<User> findByUserName(String username);
    Optional<User> findByCellphone(String phone);

    @Query(value = "SELECT fav.fk_product_id FROM favorites fav WHERE fav.fk_user_id = :userId", nativeQuery = true)
    List<Long> findFavorites(@Param("userId") Long userId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO favorites (fk_user_id, fk_product_id) VALUES (:userId, :productId)", nativeQuery = true)
    void addProductToUser(@Param("userId") Long userId, @Param("productId") Long productId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM favorites WHERE favorites.fk_user_id = :userId AND favorites.fk_product_id = :productId", nativeQuery = true)
    void removeFavoriteProductFromUser(@Param("userId") Long userId, @Param("productId") Long productId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM favorites WHERE favorites.fk_product_id = :productId", nativeQuery = true)
    void removeFavoriteProduct(@Param("productId") Long productId);

    @Modifying
    @Transactional
    @Query(value = "CALL user_rating_calc(:user_id)", nativeQuery = true)
    void userRating(@Param("user_id") int userId);
}
