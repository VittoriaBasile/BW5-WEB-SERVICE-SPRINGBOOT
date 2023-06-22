package epicode.EPICENERGYSERVICE.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "clienti")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Cliente {
	@Id
	@GeneratedValue
	private UUID id;
	private String nome;
	private int partitaIva;
	@OneToOne
	@JsonIgnore
	private Indirizzo indirizzoLegale;
	@OneToOne
	@JsonIgnore
	private Indirizzo indirizzoOperativo;
	private String email;
	private String telefono;
	private String pec;
	private String emailContatto;
	private String nomeContatto;
	private String cognomeContatto;
	private String telefonoContatto;
	private LocalDate dataInserimento;
	private LocalDate dataUltimoContatto;
	@Enumerated(EnumType.STRING)
	private RagioneSociale ragioneSociale;
	private double fatturatoAnnuo;

	@OneToMany(mappedBy = "cliente")
	@JsonIgnore
	private List<Fattura> fatture = new ArrayList<>();

	public Cliente(String nome, int partitaIva, Indirizzo indirizzoLegale, Indirizzo indirizzoOperativo, String email,
			String telefono, String pec, String emailContatto, String nomeContatto, String cognomeContatto,
			String telefonoContatto, LocalDate dataInserimento, LocalDate dataUltimoContatto, RagioneSociale ragioneSociale) {

		this.nome = nome;
		this.partitaIva = partitaIva;
		this.indirizzoLegale = indirizzoLegale;
		this.indirizzoOperativo = indirizzoOperativo;
		this.email = email;
		this.telefono = telefono;
		this.pec = pec;
		this.emailContatto = emailContatto;
		this.nomeContatto = nomeContatto;
		this.cognomeContatto = cognomeContatto;
		this.telefonoContatto = telefonoContatto;
		this.dataInserimento = dataInserimento;
		this.dataUltimoContatto = dataUltimoContatto;
		this.ragioneSociale = ragioneSociale;
		//this.fatturatoAnnuo = fatturatoAnnuo(fatture); //=> ,double fatturatoAnnuo
		//this.fatture = new ArrayList<>(); //=> , List<Fattura> fatture
	}

	@PostConstruct
	public double fatturatoAnnuo(List<Fattura> fatture) {
		double tot = 0.00;
		for (Fattura fattura : fatture) {
			tot += fattura.getImporto().doubleValue();
		}
		return tot;

	}
}
