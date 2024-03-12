package com.dsfgentarefas.controller;

import com.dsfgentarefas.dto.EventDTO;
import com.dsfgentarefas.entity.EventEntity;
import com.dsfgentarefas.exceptions.EventoNaoEncontradoException;
import com.dsfgentarefas.repository.EventRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilderDslKt.withRel;

@RequestMapping
@RestController
public class EventController {

    @Autowired
     EventRepository repository;

    @GetMapping
    public ResponseEntity<?> getAllEvents(){
        List<EventEntity> listaEvents = repository.findAll();
        if (listaEvents.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            for (EventEntity produto : listaEvents){
                long id = produto.getId();
                produto.add(linkTo(methodOn(EventController.class).mostrarPorId(id)).withRel("Buscar evento por Id"));
            }
        }
        return new ResponseEntity<List<EventEntity>>(listaEvents, HttpStatus.OK);
        //return new ResponseEntity<>(repository.findAll(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> mostrarPorId(@PathVariable Long id) {
        Optional<EventEntity> eventoExist = repository.findById(id);
        if(eventoExist.isPresent()){
            return new ResponseEntity<>(eventoExist,HttpStatus.OK);
        } else {
            throw new EventoNaoEncontradoException("Evento não encontrado");
        }
    }
    @PostMapping
    public ResponseEntity<Object> cadastrarEvent(@RequestBody EventDTO eventDTO){
        EventEntity eventEntity = new EventEntity();
        BeanUtils.copyProperties(eventDTO,eventEntity);
        repository.save(eventEntity);
        return new ResponseEntity<>(eventEntity,HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleterPorId(@PathVariable Long id) {
        Optional<EventEntity> eventoExist = repository.findById(id);
        if(eventoExist.isPresent()){
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new EventoNaoEncontradoException("Evento não encontrado");
        }
    }
}
