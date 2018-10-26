package com.examplemycompanyppsk.psk.repo;

import com.examplemycompanyppsk.psk.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
    User findByUsername(String name);
}
