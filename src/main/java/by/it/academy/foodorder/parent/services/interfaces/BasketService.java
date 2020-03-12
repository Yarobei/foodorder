package by.it.academy.foodorder.parent.services.interfaces;

import by.it.academy.foodorder.parent.model.Basket;
import by.it.academy.foodorder.parent.model.User;
import org.springframework.stereotype.Service;


@Service
public interface BasketService {

    Basket getBasketByUsername(String name);

    Basket getBasketByUserId(Long id);

    Basket getBasketByUser(User user);

    void saveBasket(Basket basket);

}
