package com.shpp.pzobenko.todolist.repositorys;

import com.shpp.pzobenko.todolist.usersinformation.UserOfToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserOfToDo, Long> {
    Optional<UserOfToDo> findUserOfToDoByUsername(String username);
}
