package pe.todotic.mitienda_s5.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcurso")
    private Integer id;

    @NotBlank(message = "El título es obligatorio.")
    private String titulo;

    @Size(min = 3, max = 500, message = "La descripción debe tener entre 3 a 500 caracteres.")
    private String descripcion;

    @NotNull(message = "El precio es obligatorio.")
    @DecimalMin(value = "1", message = "El precio debe ser 1 como mínimo.")
    @DecimalMax(value = "1000", message = "El precio debe ser 1,000 como máximo.")
    private BigDecimal precio;

    private String rutaImagen;

    @NotNull(message = "La fecha de inicio es obligatorio.")
    @FutureOrPresent(message = "La fecha de inicio debe estar en el presente o futuro.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fechaInicio;

    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_act")
    private LocalDateTime fechaActualizacion;

    @Transient
    private MultipartFile imagen;

    @PrePersist
    private void asignarFechaCreacion() {
        fechaCreacion = LocalDateTime.now();
    }

    @PreUpdate
    private void asignarFechaActualizacion() {
        fechaActualizacion = LocalDateTime.now();
    }
}
