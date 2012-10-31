package com.ikkisan.halive;

import java.io.PrintStream;

import org.kohsuke.args4j.CmdLineException;

import com.ikkisan.halive.core.BasicInput;
import com.ikkisan.halive.core.WebPageException;
import com.ikkisan.halive.core.WebPageScape;

/**
 * Webページの死活チェック.暇つぶしの為に作った遊びプログラム.
 * <p>いつしか参考にするかもという理由でとりあえずRepositryに.</p>
 * <p>誰かが拡張してくれる事を期待.</p>
 * <p>注意：このクラスから分かるように、Javaの命名規則破りまくり.ある意味読みやすく、ある意味読みづらい.</p>
 * @author ikkisan
 *
 */
public class HAlive {


    /**
	 * <p>標準入力で与えられた引数を元にWebページを死活チェック.
	 * 結果を「1」（Http Get可）「0」（Http Get不可)で出力.
	 * <p><b>Usage:</b>
	 * <pre>
	 *
	 * cmd>java halive.jar http://～.com/test/test.html
	 * 1
	 * cmd>java halive.jar -t 1000 -c 302 http://～.com/test/test2.html
	 * 1
	 * cmd>java halive.jar -t 1000 -c 500 http://～.com/test/test2.html
	 * 0
	 * </pre>
	 * <p> コマンドラインオプション
	 * <table border="1">
	 * <tr><th>オプション</th><th>説明</th></tr>
	 * <tr><td>-t</td><td>タイムアウト時間(msec)</td></tr>
	 * <tr><td>-c</td><td>WebPageから取得されるHttpレスポンスコードの期待値(数値)</td></tr>
	 * </table>
	 * @param args コマンドライン（実行オプション)
	 * @exception
	 * 	Exception:
	 * 		<ul>
	 * 		<li>コマンドラインで不正な引数が渡された</li>
	 * 		<li>Http通信中に障害が発生</li>
	 * 		</ul>
	 */
	public static void main(String[] args) throws Exception{


		HAlive halive = new HAlive();
		halive.inspectOut(convert(args),System.out);
	}


	public HAlive() {}

	public WebPageScape.HeartSound inspect(BasicInput info) throws WebPageException{
		WebPageScape scape = new WebPageScape(info);
		return scape.inspect();
	}

	public void inspectOut(BasicInput info , PrintStream stream) throws WebPageException {
		WebPageScape.HeartSound sound = inspect(info);
		if (sound == WebPageScape.HeartSound.NORMAL) {
			outPrint(stream,"1");
		} else {
			outPrint(stream,"0");
		}
	}

	private void outPrint(PrintStream outStream , String code) {
		outStream.println(code);
	}


	private static BasicInput convert(String[] args) {
		try {
			return InputCommandLine.CmdInputConverter.convert(args);
		} catch (CmdLineException e) {
			throw new IllegalArgumentException(e);
		}
	}
}
