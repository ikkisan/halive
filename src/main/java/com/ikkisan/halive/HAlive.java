package com.ikkisan.halive;

import java.io.PrintStream;

import org.kohsuke.args4j.CmdLineException;

import com.ikkisan.halive.core.BasicInput;
import com.ikkisan.halive.core.WebPageException;
import com.ikkisan.halive.core.WebPageScape;

/**
 * Web�y�[�W�̎����`�F�b�N.�ɂԂ��ׂ̈ɍ�����V�уv���O����.
 * <p>�������Q�l�ɂ��邩���Ƃ������R�łƂ肠����Repositry��.</p>
 * <p>�N�����g�����Ă���鎖������.</p>
 * <p>���ӁF���̃N���X���番����悤�ɁAJava�̖����K���j��܂���.����Ӗ��ǂ݂₷���A����Ӗ��ǂ݂Â炢.</p>
 * @author ikkisan
 *
 */
public class HAlive {


    /**
	 * <p>�W�����͂ŗ^����ꂽ����������Web�y�[�W�������`�F�b�N.
	 * ���ʂ��u1�v�iHttp Get�j�u0�v�iHttp Get�s��)�ŏo��.
	 * <p><b>Usage:</b>
	 * <pre>
	 *
	 * cmd>java halive.jar http://�`.com/test/test.html
	 * 1
	 * cmd>java halive.jar -t 1000 -c 302 http://�`.com/test/test2.html
	 * 1
	 * cmd>java halive.jar -t 1000 -c 500 http://�`.com/test/test2.html
	 * 0
	 * </pre>
	 * <p> �R�}���h���C���I�v�V����
	 * <table border="1">
	 * <tr><th>�I�v�V����</th><th>����</th></tr>
	 * <tr><td>-t</td><td>�^�C���A�E�g����(msec)</td></tr>
	 * <tr><td>-c</td><td>WebPage����擾�����Http���X�|���X�R�[�h�̊��Ғl(���l)</td></tr>
	 * </table>
	 * @param args �R�}���h���C���i���s�I�v�V����)
	 * @exception
	 * 	Exception:
	 * 		<ul>
	 * 		<li>�R�}���h���C���ŕs���Ȉ������n���ꂽ</li>
	 * 		<li>Http�ʐM���ɏ�Q������</li>
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
