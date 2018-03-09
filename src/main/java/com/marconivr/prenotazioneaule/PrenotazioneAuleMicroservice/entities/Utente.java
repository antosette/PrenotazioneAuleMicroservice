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

/**
 * Pojo Object utenti
 * @author Antonio
 */
@Entity
@Table(name = "utenti")
@AllArgsConstructor
@NoArgsConstructor
public class Utente {
    @Id
    @Column(name = "ID")
    @NotNull @NotBlank @NotEmpty
    @Getter @Setter
    private String id;
    @Column(name = "USERNAME")
    @NotNull @NotBlank @NotEmpty
    @Getter @Setter
    private String username;
    @NotBlank @NotNull @NotEmpty
    @Column(name = "PASSWORD")
    @Getter @Setter
    private String password;
    @Column(name = "RUOLO")
    @Getter @Setter
    private String ruolo;
}
