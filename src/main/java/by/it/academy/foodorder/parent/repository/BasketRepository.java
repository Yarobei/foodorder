package by.it.academy.foodorder.parent.repository;

import by.it.academy.foodorder.parent.model.Basket;
import by.it.academy.foodorder.parent.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface BasketRepository extends CrudRepository<Basket, Long> {

    Optional<Basket> findBasketByUserUserId(Long id);

    Optional<Basket> findBasketByUser(User user);
}
