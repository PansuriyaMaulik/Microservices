package com.maulik.user.service.repositories;

import com.maulik.user.service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, String>{
}
