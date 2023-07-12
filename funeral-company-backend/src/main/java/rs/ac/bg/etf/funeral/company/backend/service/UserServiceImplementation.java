package rs.ac.bg.etf.funeral.company.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.etf.funeral.company.backend.entity.User;
import rs.ac.bg.etf.funeral.company.backend.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserByUsernameAndPassword(String username, String password) {
        return userRepository.findUserByUsernameAndPassword(username, password);
    }

    @Override
    public User updateUser(User user) {
        System.out.println("user = " + user);
        User userDB = userRepository.findById(user.getUserID()).get();

        if(!user.getPassword().equals("")) userDB.setPassword(user.getPassword());
        if(!user.getUsername().equals("")) userDB.setUsername(user.getUsername());
        if(!user.getFirstname().equals("")) userDB.setFirstname(user.getFirstname());
        if(!user.getLastname().equals("")) userDB.setLastname(user.getLastname());

        return userRepository.save(userDB);
    }

    @Override
    public String removeUser(Long userID) {
        userRepository.deleteById(userID);
        return "Successfully deleted user!";
    }
}
