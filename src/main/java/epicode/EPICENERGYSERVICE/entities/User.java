package epicode.EPICENERGYSERVICE.entities;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "users")
//@JsonIgnoreProperties({ "isAccountNonLocked", "isEnabled", "isCredentialsNonExpired", "authorities" })
public class User implements UserDetails {
	@Id
	@GeneratedValue
	private UUID id;
	private String nome;
	private String cognome;
	private String username;
	private String email;
	private String password;

	//	@ManyToOne
	//	@JsonIgnore
	@Enumerated(EnumType.STRING)
	private TipoRole role;

	//	private boolean isEnabled;
	//	private boolean isCredentialsNonExpired;
	//	private boolean isAccountNonExpired;
	//	private boolean isAccountNonLocked;

	public User(String nome, String cognome, String username, String email, String password) {
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.email = email;
		this.password = password;
		role = TipoRole.USER;
		//		this.isEnabled = true;
		//		this.isAccountNonExpired = true;
		//		this.isCredentialsNonExpired = true;
		//		this.isAccountNonLocked = true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}

	public String getEmail() {

		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		//		return this.isAccountNonExpired;
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		//		return this.isAccountNonLocked;
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		//		return this.isCredentialsNonExpired;
		return false;
	}

	@Override
	public boolean isEnabled() {
		//		return this.isEnabled;
		return false;
	}

}
