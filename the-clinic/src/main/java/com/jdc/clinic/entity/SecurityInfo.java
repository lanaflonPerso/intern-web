package com.jdc.clinic.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class SecurityInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@CreatedBy
	private String createUser;

	@LastModifiedBy
	private String updateUser;

	@CreatedDate
	private LocalDateTime createTime;

	@LastModifiedDate
	private LocalDateTime updateTime;

	@Column(name = "del_flag", columnDefinition = "boolean default false")
	private boolean delete;

}