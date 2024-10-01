package com.example.tasktracker; 

 

import org.springframework.data.jpa.repository.JpaRepository; 

import org.springframework.stereotype.Repository; 

import java.util.List; 

@Repository 

public interface HabitoHistoricoRepository extends JpaRepository<HabitoHistorico, Long> { 

    List<HabitoHistorico> findByHabitoId(Long habitoId); 

 

} 

 

 