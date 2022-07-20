package com.bolsadeideas.springboot.dataJPA.app.models.entity;

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

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "clientes") // Indicamos el nombre de la tabla de la BDD
public class Cliente implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotEmpty//(message = "El nombre no puede estar vacio")
	private String nombre;
	
	@NotEmpty(message = "El apellido no puede estar vacio")
	private String apellido;
	
	@NotEmpty(message = "El email no puede estar vacio")
	@Email(message = "escribe el email en el formato correcto")
	private String email;

	@NotNull
	@Column(name = "create_at") // Nombre del campo de la BDD en el caso de que sea diferente
	@Temporal(TemporalType.DATE) // Indicamos que solo queremos guardar la fecha (sin hora)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date createAt;
	

/*	@PrePersist //Metodo para que se añada la fecha automaticamente cada vez que hagamos un nuevo registro
	public void prePersist() {
		createAt = new Date();
	}
*/
	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
