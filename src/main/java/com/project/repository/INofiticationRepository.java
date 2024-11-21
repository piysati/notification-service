package com.project.repository;

import com.project.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface INofiticationRepository extends JpaRepository<Notification, Short> {

	List<Notification> findAllByUserId(Short userId);

}