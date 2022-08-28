package com.api.blog.modules.users.adapters.out.hash.bcrypt.impl;

import com.api.blog.modules.users.core.ports.out.HashPort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Qualifier(HashBcryptImpl.ADAPTER_TOKEN)
public class HashBcryptImpl implements HashPort {
	public static final String ADAPTER_TOKEN = "BcryptHashAdapter";
	@Override
	public String make(String value) {
		return new BCryptPasswordEncoder().encode(value);
	}

	@Override
	public Boolean compare(String value, String valueHashed) {
		return new BCryptPasswordEncoder().matches(value, valueHashed);
	}
}
