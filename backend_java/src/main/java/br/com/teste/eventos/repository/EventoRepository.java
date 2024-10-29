package br.com.teste.eventos.repository;

import br.com.teste.eventos.entity.EventoEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface EventoRepository extends CrudRepository<EventoEntity, Long> {


    @Modifying
    @Query("UPDATE EventoEntity e SET e.ativo = :ativo WHERE e.id = :id")
    void updateAtivo(@Param(value = "id") Long id, @Param(value = "ativo") Boolean ativo);

    @Modifying
    @Query("UPDATE EventoEntity e SET e.ativo = false WHERE e.dataFinal < :date")
    void updateEventosVencidos(@Param("date") LocalDate date);
}
