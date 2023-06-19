package epicode.EPICENERGYSERVICE.entities;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "comuni")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Comune {
	@Id
	@GeneratedValue
	private UUID id;
	private int codiceProvincia;
	private int progressivoComune;
	private String nome;
	@ManyToOne
	private Provincia provincia;
	@OneToMany(mappedBy = "comune")
	private List<Indirizzo> indirizzi;

	public Comune(String nome, Provincia provincia) {
		super();
		this.nome = nome;
		this.provincia = provincia;
	}

}
