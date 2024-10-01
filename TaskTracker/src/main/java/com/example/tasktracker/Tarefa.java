package com.example.tasktracker; 

 

import jakarta.persistence.*; 

import lombok.Getter; 

import lombok.Setter; 

 

import java.sql.Timestamp; 

 

@Getter 

@Setter 

@Entity 

public class Tarefa { 

 

    @Id 

    @GeneratedValue(strategy = GenerationType.IDENTITY) 

    private Long id; 

 

    private String descricao; 

    private Timestamp dataInicio; 

    private Timestamp dataLimite; 

    private Boolean concluida; 

 

    @ManyToOne 

    @JoinColumn(name = "usuario_id") 

    private Usuario usuario; 

 

} 

 

 