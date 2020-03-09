package by.it.academy.foodorder.parent.services.interfaces;

public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);

}
