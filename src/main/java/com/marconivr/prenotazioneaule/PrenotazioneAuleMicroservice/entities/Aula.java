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
@Table(name = "aule")
@AllArgsConstructor
@NoArgsConstructor
public class Aula {
    @Id
    @Column(name = "ID")
    @NotNull @NotBlank @NotEmpty
    @Getter @Setter
    private String id;
    @Column(name = "DESCRIZIONE")
    @NotNull @NotBlank @NotEmpty
    @Getter @Setter
    private String descrizione;
    @Column(name = "CODICE")
    @NotBlank @NotNull @NotEmpty
    @Getter @Setter
    private String codice;
    @Column(name = "PIANO")
    @NotBlank @NotNull @NotEmpty
    @Getter @Setter
    private String piano;
    @Column(name = "NOTE")
    @Getter @Setter
    private String note;
}
