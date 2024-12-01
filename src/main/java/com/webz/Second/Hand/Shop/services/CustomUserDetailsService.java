package com.webz.Second.Hand.Shop.services;

import com.webz.Second.Hand.Shop.model.PublisherEntity;
import com.webz.Second.Hand.Shop.repositories.PublisherRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final PublisherRepository publisherRepository;

    public CustomUserDetailsService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PublisherEntity publisher = publisherRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return new User(publisher.getUsername(), publisher.getPassword(), Collections.emptyList());
    }
}
