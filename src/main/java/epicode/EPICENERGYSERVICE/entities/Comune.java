package epicode.EPICENERGYSERVICE.entities;

import java.util.List;
import java.util.UUID;

import com.opencsv.bean.CsvBindByName;

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
	@CsvBindByName(column = "Codice Provincia")
	private int codiceProvincia;
	//private int codiceProvincia(storico)(1);
	@CsvBindByName(column = "Progressivo del Comune")
	private int progressivoDelComune;
	@CsvBindByName(column = "Denominazione in Italiano")
	private String denominazioneInItaliano;
	@ManyToOne
	private Provincia provincia;
	@OneToMany(mappedBy = "comune")
	private List<Indirizzo> indirizzi;

	public Comune(int codiceProvincia, int progressivoDelComune, String denominazioneInItaliano, Provincia provincia) {
		super();
		this.codiceProvincia = codiceProvincia;
		this.progressivoDelComune = progressivoDelComune;
		this.denominazioneInItaliano = denominazioneInItaliano;
		this.provincia = provincia;
	}

}
