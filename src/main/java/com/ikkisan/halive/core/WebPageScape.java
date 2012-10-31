package com.ikkisan.halive.core;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

/**
 * Web�y�[�W���f��...(��)...
 * <p>Web�y�[�W�̎擾�����{���AHttp Response�̃X�e�[�^�X�R�[�h���m�F����.
 * @author ikkisan
 *
 */
public class WebPageScape {

	private InputWapper param;

	public WebPageScape(BasicInput targetInfo) {
		//�f�t�H���g�p�����[�^��ݒ�
		param = new InputWapper(targetInfo);
	}

	/**
	 * �擾����Web�y�[�W����Http���X�|���X�R�[�h��{@link BasicInput#getAliveCode()}��
	 * ���l���m�F�A���ʂ�Ԃ�.
	 * @return {@link HeartSound}
	 * @throws WebPageException Web�y�[�W�擾����IOError������
	 */
	public HeartSound inspect() throws WebPageException{

		//url���Ȃ��ꍇ�A�Ώۂ�������Ȃ��Ɣ���.
		if (param.getUrl().length() ==0)
			return HeartSound.ABNORMAL;

		if ((getHttpResponseCode() == param.getAliveCode())) {
			return HeartSound.NORMAL;
		}
		return HeartSound.ABNORMAL;
	}

	/**
	 * Web�y�[�W���擾���A���X�|���X�R�[�h��Ԃ�.
	 * @return
	 * <p>Http Response�R�[�h(Ex:200,404,500)
	 * <p>-1�́A
	 * @throws WebPageException WebPageException Web�y�[�W�擾����IOError������
	 */
	private int getHttpResponseCode() throws WebPageException{
		try {
			HttpParams basicParam = new BasicHttpParams();

			HttpConnectionParams.setConnectionTimeout(basicParam, param.getTimeout());

			HttpClient client = new DefaultHttpClient(basicParam);

			HttpGet hget = new HttpGet(param.getUrl());
			HttpResponse response = client.execute(hget);
			StatusLine stLine = response.getStatusLine();

			if (stLine == null) {
				throw new WebPageException("�X�e�[�^�X�R�[�h�����݂��܂���.");
			}
			return stLine.getStatusCode();
		} catch (IOException ioe) {
			if(ioe instanceof ConnectTimeoutException) {
				return ExceptResponseNumber.RequestTimeOut.getCode();
			}
			throw new WebPageException(ioe);
		}
	}

	private enum ExceptResponseNumber {
		RequestTimeOut(-1);
		private int code;
		private ExceptResponseNumber(int code) {
			this.code = code;
		}
		public int getCode() { return code; }
	}

	public enum HeartSound{
		NORMAL,
		ABNORMAL
	}


}
