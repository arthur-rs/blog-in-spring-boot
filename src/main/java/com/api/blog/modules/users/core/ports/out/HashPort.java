package com.api.blog.modules.users.core.ports.out;

public interface HashPort {
	String make(String value);
	Boolean compare(String value, String valueHashed);
}
