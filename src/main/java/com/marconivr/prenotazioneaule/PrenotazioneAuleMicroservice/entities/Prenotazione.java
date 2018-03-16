package com.marconivr.prenotazioneaule.PrenotazioneAuleMicroservice.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Pojo Object utenti
 * @author Antonio
 */
@Entity
@Table(name = "prenotazioni")
@AllArgsConstructor @NoArgsConstructor
public class Prenotazione {
    @Id
    @Column(name = "ID")
    @Getter @Setter
    @GeneratedValue(strategy = GenerationType.AUTO) //autogenarazione
    private int id;
    @Column(name = "DATA_PRE")
    @NotNull
    @Getter @Setter
    @DateTimeFormat(pattern="dd/MM/yyyy")
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
    @NotNull
    @Getter @Setter
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date data;
    @Column(name = "ORA")
    @Getter @Setter
    private int ora;

   /* @PrePersist
    void getTimeOperation() { //permette di valorizzare automaticamente la dataPre.
        this.dataPre = new Date();
        this.data = new Date();
    }*/


}
