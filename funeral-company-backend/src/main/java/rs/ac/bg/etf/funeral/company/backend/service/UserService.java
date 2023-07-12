package rs.ac.bg.etf.funeral.company.backend.service;

import rs.ac.bg.etf.funeral.company.backend.entity.User;

public interface UserService {

    public User saveUser(User user);
    public User getUserByUsernameAndPassword(String username, String password);
    public User updateUser(User user);
    public String removeUser(Long userID);
}
