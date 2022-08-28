package com.api.blog.modules.users.adapters.out.hash.bcrypt.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.api.blog.modules.users.core.ports.out.HashPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component(HashBcryptImpl.ADAPTER_TOKEN)
public class HashBcryptImpl implements HashPort {
	public static final String ADAPTER_TOKEN = "BcryptHashAdapter";
	@Override
	public String make(String value) {
		return BCrypt.withDefaults().hashToString(12, value.toCharArray());
	}

	@Override
	public Boolean compare(String value, String valueHashed) {
		var result = BCrypt
				.verifyer()
				.verify(value.toCharArray(), valueHashed.toCharArray());
		return result.verified;
	}
}
