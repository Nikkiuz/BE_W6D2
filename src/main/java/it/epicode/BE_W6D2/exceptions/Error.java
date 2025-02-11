package it.epicode.BE_W6D2.exceptions;

import lombok.Data;

@Data
public class Error {
	private String message;
	private String details;
	private String status;
}
