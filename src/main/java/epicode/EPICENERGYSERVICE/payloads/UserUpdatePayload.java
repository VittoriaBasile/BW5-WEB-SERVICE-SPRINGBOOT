package epicode.EPICENERGYSERVICE.payloads;

import epicode.EPICENERGYSERVICE.entities.TipoRole;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdatePayload {

	@NotNull(message = "Username obbligatorio")
	@Size(min = 3, max = 30, message = "Nome min 3 caratteri, massimo 30")
	String username;

	@NotNull(message = "Password obbligatoria")
	String password;
	@NotNull(message = "Nome obbligatorio")
	String nome;
	@NotNull(message = "Cognome obbligatorio")
	String cognome;

	@NotNull(message = "Ruolo obbligatorio")
	TipoRole role;
}
