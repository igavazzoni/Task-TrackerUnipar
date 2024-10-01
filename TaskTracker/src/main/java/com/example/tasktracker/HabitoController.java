package com.example.tasktracker; 

 

import org.springframework.beans.factory.annotation.Autowired; 

import org.springframework.http.ResponseEntity; 

import org.springframework.web.bind.annotation.*; 

 

import java.util.Date; 

import java.util.List; 

 

@RestController 

@RequestMapping("/api/habitos") 

public class HabitoController { 

 

    @Autowired 

    private HabitoRepository habitoRepository; 

 

    @Autowired 

    private HabitoHistoricoRepository habitoHistoricoRepository; 

 

    @PostMapping("/add") 

    public ResponseEntity<Habito> adicionarHabito(@RequestBody Habito habito) { 

        habitoRepository.save(habito); 

        return ResponseEntity.ok(habito); 

    } 

 

    @GetMapping("/listar/{usuarioId}") 

    public List<HabitoHistorico> listarHabitos(@PathVariable Long usuarioId) { 

        return habitoRepository.findByUsuarioId(usuarioId); 

    } 

 

    @PostMapping("/concluir/{habitoId}") 

    public ResponseEntity<String> marcarHabito(@PathVariable Long habitoId) { 

        Habito habito = habitoRepository.findById(habitoId).orElseThrow(); 

        HabitoHistorico habitoHistorico = new HabitoHistorico(); 

        habitoHistorico.setData(new Date()); 

        habitoHistorico.setHabito(habito); 

        habitoHistoricoRepository.save(habitoHistorico); 

        return ResponseEntity.ok("Hábito concluído e registrado no histórico"); 

    } 

 

    @DeleteMapping("/deletar/{id}") 

    public ResponseEntity<String> deletarHabito(@PathVariable Long id) { 

        habitoRepository.deleteById(id); 

        return ResponseEntity.ok("Hábito deletado com sucesso"); 

    } 

} 

 

 