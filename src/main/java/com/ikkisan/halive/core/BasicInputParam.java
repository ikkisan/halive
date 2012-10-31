package com.ikkisan.halive.core;

public class BasicInputParam implements BasicInput{

	/** �w���X�`�F�b�N�Ώۂ�URL **/
	private String url;

	/** �^�C���A�E�g����(msec) **/
	private Integer timeout;

	/** ���탌�X�|���X�R�[�h **/
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
