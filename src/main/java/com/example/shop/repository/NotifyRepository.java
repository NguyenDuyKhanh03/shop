package com.example.shop.repository;

import com.example.shop.models.Notify;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotifyRepository extends JpaRepository<Notify,Long> {

    List<Notify> findNotifiesByUsernameOrderByCreatedAtDesc(String username);

    List<Notify> findNotifiesByTypeOrderByCreatedAtDesc(Notify.NotificationType type);
}
