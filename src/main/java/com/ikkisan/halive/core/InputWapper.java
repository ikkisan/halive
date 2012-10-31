package com.ikkisan.halive.core;

public class InputWapper implements BasicInput{

	private BasicInput target;

	public InputWapper(BasicInput target) {
		if (target == null) {
			throw new IllegalArgumentException("not null");
		}
		this.target = target;
	}


	@Override
	public String getUrl() {
		if (target.getUrl() == null || target.getUrl().length() == 0)
			throw new IllegalArgumentException("Do not work.Reason:URL is Null");
		if (target.getUrl().indexOf("http://") == -1 && target.getUrl().indexOf("https://") == -1)
			return "http://"+target.getUrl();
		return target.getUrl();
	}

	@Override
	public Integer getTimeout() {
		if (target.getTimeout() == null)
			return BasicInput.DEFAULT_TIMEOUT;
		return target.getTimeout();
	}

	@Override
	public Integer getAliveCode() {
		if (target.getAliveCode() == null)
			return BasicInput.DEFAULT_ALIVE_CODE;
		return target.getAliveCode();
	}


}
