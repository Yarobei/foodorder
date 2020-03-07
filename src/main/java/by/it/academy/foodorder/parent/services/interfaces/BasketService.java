package by.it.academy.foodorder.parent.services.interfaces;

import by.it.academy.foodorder.parent.model.Basket;
import by.it.academy.foodorder.parent.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BasketService {

    List<Basket> getBasket();

    Optional<Basket> getBasketByUserId(Long id);

    Optional<Basket> getBasketByUser(User user);

}
