package com.jdc.clinic.dto.member;

import com.jdc.clinic.entity.FamilyMember;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FMByAge extends FamilyMember {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int age = 0;
}
