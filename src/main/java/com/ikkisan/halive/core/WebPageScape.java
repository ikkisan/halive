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
 * Webページ聴診器...(笑)...
 * <p>Webページの取得を実施し、Http Responseのステータスコードを確認する.
 * @author ikkisan
 *
 */
public class WebPageScape {

	private InputWapper param;

	public WebPageScape(BasicInput targetInfo) {
		//デフォルトパラメータを設定
		param = new InputWapper(targetInfo);
	}

	/**
	 * 取得したWebページ内のHttpレスポンスコードが{@link BasicInput#getAliveCode()}と
	 * 同値か確認、結果を返す.
	 * @return {@link HeartSound}
	 * @throws WebPageException Webページ取得中にIOErrorが発生
	 */
	public HeartSound inspect() throws WebPageException{

		//urlがない場合、対象が見つからないと判定.
		if (param.getUrl().length() ==0)
			return HeartSound.ABNORMAL;

		if ((getHttpResponseCode() == param.getAliveCode())) {
			return HeartSound.NORMAL;
		}
		return HeartSound.ABNORMAL;
	}

	/**
	 * Webページを取得し、レスポンスコードを返す.
	 * @return
	 * <p>Http Responseコード(Ex:200,404,500)
	 * <p>-1は、
	 * @throws WebPageException WebPageException Webページ取得中にIOErrorが発生
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
				throw new WebPageException("ステータスコードが存在しません.");
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
