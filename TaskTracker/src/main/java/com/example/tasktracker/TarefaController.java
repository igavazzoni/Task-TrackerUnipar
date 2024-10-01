package com.example.tasktracker; 

 

import org.springframework.beans.factory.annotation.Autowired; 

import org.springframework.http.ResponseEntity; 

import org.springframework.web.bind.annotation.*; 

 

import java.sql.Timestamp; 

import java.time.LocalDateTime; 

import java.util.List; 

 

 

@RestController 

@RequestMapping("/api/tarefas") 

public class TarefaController { 

 

    @Autowired 

    private TarefaRepository tarefaRepository; 

 

    @PostMapping("/add") 

    public ResponseEntity<Tarefa> adicionarTarefa(@RequestBody Tarefa tarefa) { 

        tarefa.setDataInicio(Timestamp.valueOf(LocalDateTime.now())); 

        tarefaRepository.save(tarefa); 

        return ResponseEntity.ok(tarefa); 

    } 

 

    @GetMapping("/listar") 

    public List<Tarefa> listarTarefas() { 

        return tarefaRepository.findAll(); 

    } 

 

    @PutMapping("/atualizar/{id}") 

    public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable Integer id, @RequestBody Tarefa novaTarefa) { 

        Tarefa tarefaExistente = tarefaRepository.findById(id).orElseThrow(); 

        tarefaExistente.setDescricao(novaTarefa.getDescricao()); 

        tarefaExistente.setDataLimite(novaTarefa.getDataLimite()); 

        tarefaExistente.setConcluida(novaTarefa.getConcluida()); 

        tarefaRepository.save(tarefaExistente); 

        return ResponseEntity.ok(tarefaExistente); 

    } 

 

    @DeleteMapping("/deletar/{id}") 

    public ResponseEntity<String> deletarTarefa(@PathVariable Integer id) { 

        tarefaRepository.deleteById(id); 

        return ResponseEntity.ok("Tarefa deletada com sucesso"); 

    } 

} 

 
