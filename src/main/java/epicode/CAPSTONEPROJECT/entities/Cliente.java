package epicode.CAPSTONEPROJECT.entities;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	private String nome;
	private String telefono;
	private String cognome;
	private String email;

	public Cliente(String nome, String telefono, String email, String cognome) {
		this.nome = nome;
		this.telefono = telefono;
		this.email = email;
		this.cognome = cognome;
	}
}
