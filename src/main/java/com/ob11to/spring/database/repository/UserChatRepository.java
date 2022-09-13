package com.ob11to.spring.database.repository;

import com.ob11to.spring.database.entity.UserChat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserChatRepository extends JpaRepository<UserChat, Long> {
}
