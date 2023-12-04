package com.example.firstrestapi.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import java.util.List;

@RepositoryRestResource
public interface UserDetailsRestRepository  extends PagingAndSortingRepository<UserDetails, Long> {
    List<UserDetails> findByRole(String role);

}
