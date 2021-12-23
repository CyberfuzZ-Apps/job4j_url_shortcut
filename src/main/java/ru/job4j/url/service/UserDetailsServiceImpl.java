package ru.job4j.url.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.job4j.url.model.SiteModel;
import ru.job4j.url.repository.SiteRepository;

import java.util.Collections;

/**
 * Класс UserDetailsServiceImpl
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final SiteRepository siteRepository;

    public UserDetailsServiceImpl(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SiteModel siteModel = siteRepository.findByLogin(username).orElse(null);
        if (siteModel == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(siteModel.getLogin(), siteModel.getPassword(), Collections.emptyList());
    }
}
