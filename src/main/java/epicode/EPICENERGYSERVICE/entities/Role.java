package epicode.EPICENERGYSERVICE.entities;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "roles")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Role {
	@Id
	@GeneratedValue
	private UUID id;
	private String tipo;
	//	@OneToMany
	//	private List<User> users;
	//	@ManyToMany(mappedBy = "role")
	//	private List<User> users;
	//	@ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
	//	private List<User> users = new ArrayList<>();

	public Role(String tipo) {
		this.tipo = tipo;
	}

	//	public Role(String tipo, List<User> users) {
	//		this.tipo = tipo;
	//		this.users = users;
	//	}

}
