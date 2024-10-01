package com.example.tasktracker; 

 

import jakarta.persistence.*; 

import lombok.Getter; 

import lombok.Setter; 

 

import java.util.Date; 

 

@Setter 

@Getter 

@Entity 

public class HabitoHistorico { 

    @Id 

    @GeneratedValue(strategy = GenerationType.IDENTITY) 

    private Long id; 

 

    private Date data; 

 

    @ManyToOne 

    @JoinColumn(name = "habito_id") 

    private Habito habito; 

 

} 

 

 