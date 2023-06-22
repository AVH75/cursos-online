package pe.todotic.mitienda_s5.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.todotic.mitienda_s5.model.Curso;

import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {

    List<Curso> findByTitulo(String t);

    List<Curso> findByTituloContaining(String t);

    Page<Curso> findByTituloContaining(String t, Pageable p);

    List<Curso> findFirst8ByOrderByFechaCreacionDesc();

}
