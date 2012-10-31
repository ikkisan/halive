package com.ikkisan.halive.core;

public class BasicInputParam implements BasicInput{

	/** ヘルスチェック対象のURL **/
	private String url;

	/** タイムアウト時間(msec) **/
	private Integer timeout;

	/** 正常レスポンスコード **/
	private Integer aliveCode;


	private BasicInputParam() {}



	public void setUrl(String url) {
		this.url = url;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	public void setAliveCode(Integer aliveCode) {
		this.aliveCode = aliveCode;
	}

	@Override
	public String getUrl() {
		return url;
	}

	@Override
	public Integer getTimeout() {
		return timeout;
	}

	@Override
	public Integer getAliveCode() {
		return aliveCode;
	}




}
