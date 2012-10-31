package com.ikkisan.halive.core;

public interface BasicInput {

	// 30sec
	public static final Integer DEFAULT_TIMEOUT = 1000 * 30;

	public static final Integer DEFAULT_ALIVE_CODE = 200;



	public String getUrl();
	public Integer getTimeout();
	public Integer getAliveCode();



}
