package epicode.EPICENERGYSERVICE.payloads;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClienteCreatePayload {

	@NotNull(message = "Nome cliente obbligatorio")
	@Size(min = 3, max = 30, message = "Nome min 3 caratteri, massimo 30")
	private String nome;

	@NotNull(message = "P.IVA obbligatoria")
	private String partitaIva;

	//@NotNull(message = "Sede legale obbligatoria")
	private UUID indirizzoLegale;

	//@NotNull(message = "Sede legale obbligatoria")
	private UUID indirizzoOperativo;

	@NotNull(message = "Email obbligatoria")
	@Email(message = "Non hai inserito un indirizzo email valido")
	private String email;

	@Size(max = 20, message = "Il numero di telefono può contenere al massimo 20 caratteri")
	private String telefono;

	@NotNull(message = "PEC obbligatoria")
	@Email(message = "Non hai inserito un indirizzo PEC valido")
	private String pec;

	@Size(max = 30, message = "L'email del contatto può contenere al massimo 30 caratteri")
	@Email(message = "Non hai inserito un indirizzo email valido per il contatto")
	private String emailContatto;

	@NotNull(message = "Il nome del contatto è obbligatorio")
	@Size(min = 3, max = 30, message = "Nome min 3 caratteri, massimo 30")
	private String nomeContatto;

	@Size(min = 3, max = 30, message = "Nome min 3 caratteri, massimo 30")
	private String cognomeContatto;

	@Size(max = 20, message = "Il numero di telefono può contenere al massimo 20 caratteri")
	private String telefonoContatto;

	@NotNull(message = "La data di inserimento è obbligatoria")
	private LocalDate dataInserimento;

	private LocalDate dataUltimoContatto;

	@Size(max = 15, message = "La ragione sociale può contenere al massimo 15 caratteri")
	private String ragioneSociale;

	public ClienteCreatePayload(@NotNull(message = "Nome cliente obbligatorio") String nome,
			@NotNull(message = "La partita IVA è obbligatoria") String partitaIva, UUID indirizzoLegale, // @NotNull(message = "La sede legale è obbligatoria") 
			UUID indirizzoOperativo, //@NotNull(message = "La sede legale è obbligatoria") 
			@NotNull(message = "L'email è obbligatoria") String email, String telefono,
			@NotNull(message = "La PEC è obbligatoria") String pec, String emailContatto,
			@NotNull(message = "Nome contatto obbligatorio") String nomeContatto, String cognomeContatto,
			String telefonoContatto, @NotNull(message = "La data di inserimento è obbligatoria") LocalDate dataInserimento,
			LocalDate dataUltimoContatto, String ragioneSociale) {
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
	}
}
