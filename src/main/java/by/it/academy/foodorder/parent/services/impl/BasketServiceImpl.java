package by.it.academy.foodorder.parent.services.impl;

import by.it.academy.foodorder.parent.model.Basket;
import by.it.academy.foodorder.parent.model.User;
import by.it.academy.foodorder.parent.repository.BasketRepository;
import by.it.academy.foodorder.parent.services.interfaces.BasketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BasketServiceImpl implements BasketService {

    @Autowired
    private BasketRepository basketRepository;

    @Override
    @Transactional
    public Basket getBasketByUsername(String name) {
        return basketRepository.findBasketByUserUsername(name);
    }

    @Override
    @Transactional
    public Basket getBasketByUserId(Long id) {
        return basketRepository.findBasketByUserUserId(id);
    }

    @Override
    @Transactional
    public Basket getBasketByUser(User user) {
        return basketRepository.findBasketByUser(user);
    }

    @Override
    @Transactional
    public void saveBasket(Basket basket) {
        basketRepository.save(basket);
    }
}
