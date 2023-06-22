package pe.todotic.mitienda_s5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.todotic.mitienda_s5.model.Usuario;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    List<Usuario> findByNombreCompletoContaining(String nc);

    Optional<Usuario> findByEmail(String e);

    boolean existsByEmail(String e);
}
