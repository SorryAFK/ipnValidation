package com.shpp.pzobenko.validation.application.repository;

import com.shpp.pzobenko.validation.application.ukrainianpeople.CitizenOfUkraine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitizenOfUkraineRepo extends JpaRepository<CitizenOfUkraine,Long> {
    boolean existsByIpn(Long ipn);
}
