package com.api.blog.modules.users.adapters.out.usersrepository.jpa.entities;

import com.api.blog.modules.users.core.models.enums.UserStatusEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_USERS")
@Getter
@Setter
@NoArgsConstructor
public class UserJpaEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private UUID id;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "email", nullable = false)
	private String email;
	@Column(name = "password", nullable = false)
	private String password;
	@Column(name ="status", nullable = false)
	private UserStatusEnum status;
}
