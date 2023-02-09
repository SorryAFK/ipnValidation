package com.shpp.pzobenko.todolist.repositorys;

import com.shpp.pzobenko.todolist.usersinformation.TheAim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TheAimRepository extends JpaRepository<TheAim, Long> {
    Optional<TheAim> findTheAimByNameOfAim(String nameOfAim);
}
