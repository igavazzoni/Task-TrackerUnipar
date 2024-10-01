package com.example.tasktracker; 

 

import jakarta.persistence.*; 

import lombok.Getter; 

import lombok.Setter; 

 

import java.util.List; 

 

@Getter 

@Setter 

@Entity 

public class Habito { 

 

    @Id 

    @GeneratedValue(strategy = GenerationType.IDENTITY) 

    private Long id; 

 

    private String descricao; 

 

    @ManyToOne 

    @JoinColumn(name = "usuario_id") 

    private Usuario usuario; 

 

    @OneToMany(mappedBy = "habito") 

    private List<HabitoHistorico> historico; 

 

 

 

 

} 

 

 