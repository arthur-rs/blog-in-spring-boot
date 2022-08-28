package com.api.blog.modules.users.adapters.in.entrypoint.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class HttpException<DetailsType> {
	private String statusMessage;
	private int statusCode;
	private DetailsType errorDetails;
	private String errorCode;
	private Date timestamp;
}
