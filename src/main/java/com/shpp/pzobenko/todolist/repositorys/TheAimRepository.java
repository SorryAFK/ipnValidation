package com.shpp.pzobenko.todolist.repositorys;

import com.shpp.pzobenko.todolist.usersinformation.TheAim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheAimRepository extends JpaRepository<TheAim, String> {
}
