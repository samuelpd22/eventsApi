package com.dsfgentarefas.entity;

import com.dsfgentarefas.dto.EventDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Entity
@Table (name="tbevents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventEntity extends RepresentationModel<EventEntity> {
    @Id
    @GeneratedValue( strategy =  GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String data;

    private String obs;



}
