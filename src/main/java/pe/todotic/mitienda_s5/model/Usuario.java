package pe.todotic.mitienda_s5.model;

import lombok.Getter;
import lombok.Setter;
import pe.todotic.mitienda_s5.model.annotation.UsuarioPasswordEqualsConstraint;
import pe.todotic.mitienda_s5.model.group.RegistrarUsuarioGroup;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@UsuarioPasswordEqualsConstraint(groups = {RegistrarUsuarioGroup.class})
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idusuario")
    private Integer id;

    private String nombres;
    private String apellidos;

    @Column(name = "nom_completo")
    private String nombreCompleto;

    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_act")
    private LocalDateTime fechaActualizacion;

    @OneToMany(mappedBy = "usuario")
    private List<Inscripcion> inscripciones;

    public enum Rol {
        ADMIN,
        ESTUDIANTE
    }

    @PrePersist
    private void asignarFechaCreacion() {
        fechaCreacion = LocalDateTime.now();
        nombreCompleto = nombres + " " + apellidos;
    }

    @PreUpdate
    private void asignarFechaActualizacion() {
        fechaActualizacion = LocalDateTime.now();
        nombreCompleto = nombres + " " + apellidos;
    }
}
