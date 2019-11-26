package br.pucminas.api.constants;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Validation;
import javax.validation.Validator;

public final class Validador {

	private static final Validator exec = Validation.buildDefaultValidatorFactory().getValidator();
	
	public static List<String> validate(Object value) {
		return exec.validate(value).stream().map(v -> v.getMessage()).collect(Collectors.toList());
	}
	
}
