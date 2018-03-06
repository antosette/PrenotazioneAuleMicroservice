package com.marconivr.prenotazioneaule.PrenotazioneAuleMicroservice.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Pojo Object utenti
 * @author Antonio
 */
@Entity
@Table(name = "prenotazioni")
@AllArgsConstructor
@NoArgsConstructor
public class Prenotazione {
    @Id
    @Column(name = "ID")
    @NotNull @NotBlank @NotEmpty
    @Getter @Setter
    private String id;
    @Column(name = "DATA_PRE")
    @NotNull @NotBlank @NotEmpty
    @Getter @Setter
    private Date dataPre;
    @Column(name = "FK_UTENTE")
    @NotBlank @NotNull @NotEmpty
    @Getter @Setter
    private String fkUtente;
    @Column(name = "FK_AULA")
    @NotBlank @NotNull @NotEmpty
    @Getter @Setter
    private String fkAula;
    @Column(name = "DATA")
    @NotNull @NotBlank @NotEmpty
    @Getter @Setter
    private Date data;
    @Column(name = "ORA")
    @NotNull @NotBlank @NotEmpty
    @Getter @Setter
    private String ora;

}
