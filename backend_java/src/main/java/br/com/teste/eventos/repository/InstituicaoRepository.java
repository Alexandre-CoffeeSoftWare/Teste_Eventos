package br.com.teste.eventos.repository;

import br.com.teste.eventos.entity.InstituicaoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstituicaoRepository extends CrudRepository<InstituicaoEntity, Long> {
}
