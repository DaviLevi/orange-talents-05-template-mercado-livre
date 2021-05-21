package br.com.zup.ot5.fase3.mercadolivre.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UsuarioID")
	private Long id;

	@NotBlank
	@Email
	@Column(name = "Login", unique = true)
	private String login;
	
	@Size(min = 6)
	@Column(name = "Senha")
	private String senha;
	
	@Column(name = "DataCadastro", nullable = false)
	@CreationTimestamp
	@PastOrPresent
	private LocalDateTime dataCadastro;

	@Deprecated public Usuario() {}
	
	public Usuario(@NotBlank @Email String login, @Size(min = 6, max = 59) String senha) {
		Assert.isTrue(senha.length() != 60, "A senha nao pode vir em formato Bcrypt");
		Assert.notNull(login, "O login nao pode vir nulo");
		
		this.login = login;
		this.senha = new BCryptPasswordEncoder().encode(senha);
	}
}
