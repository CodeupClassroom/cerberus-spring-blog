package docrob.springblog.services;

import docrob.springblog.models.PostUserDetails;
import docrob.springblog.models.User;
import docrob.springblog.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PostUserDetailsService implements UserDetailsService {

    public final UserRepository userDao;

    public PostUserDetailsService(UserRepository userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User details not found for username" + username);
        } else {
            return new PostUserDetails(user.getId(), user.getUsername(), user.getEmail(), user.getPassword());
        }
    }
}
