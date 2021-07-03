package br.com.alura.forum.repositories;

import br.com.alura.forum.models.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

}
