package com.dsfgentarefas.repository;

import com.dsfgentarefas.dto.EventDTO;
import com.dsfgentarefas.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface EventRepository extends JpaRepository<EventEntity, Long> {





}
