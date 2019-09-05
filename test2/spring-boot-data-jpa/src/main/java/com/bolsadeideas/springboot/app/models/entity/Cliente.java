package com.bolsadeideas.springboot.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity //indica que es una clase con metodos set y get mapeado a un:
@Table(name = "clientes") //indicar el nombre de la tabla 
public class Cliente implements Serializable { //serializable recomend

	@Id //indica que es la clave primaria 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	
	
	//Validadiones
	
	@NotEmpty
    @Size(min = 3, max = 8)
	@Pattern(regexp ="[a-zA-Z]+", message="Caracter incorrecto, por favor ingrese un caracter valido")
	private String nombre;
	
	
	@NotEmpty
	 @Size(min = 3, max = 8)
	 @Pattern(regexp ="[a-zA-Z]+", message="Caracter incorrecto, por favor ingrese un caracter valido")
	private String apellido;
	@NotEmpty
	@Email
	private String email;

	
	@NotNull
	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	private Date createAt;
	
	
	private String foto;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	
	
	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = 1L;

}
