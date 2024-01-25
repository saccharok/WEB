package ru.adept.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "preserves_plant")
public class PreservesAndPlant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "id_plant", nullable = false)
    @JsonIgnore
    Plant plant;

    @ManyToOne
    @JoinColumn(name = "id_preserve", nullable = false)
    Preserve preserve;
}
