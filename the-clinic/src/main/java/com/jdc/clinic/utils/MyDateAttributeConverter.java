package com.jdc.clinic.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class MyDateAttributeConverter implements AttributeConverter<LocalDate, String> {

	@Override
	public String convertToDatabaseColumn(LocalDate entityDate) {
		return entityDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}

	@Override
	public LocalDate convertToEntityAttribute(String databaseDate) {
		return LocalDate.parse(databaseDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
}