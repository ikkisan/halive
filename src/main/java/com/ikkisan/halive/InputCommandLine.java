package com.ikkisan.halive;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import com.ikkisan.halive.core.BasicInput;


class InputCommandLine implements BasicInput {

	@Argument(usage="url")
	private String url;

	@Option(name="-h", aliases="--help", usage="print usage message and exit")
    private boolean usageFlag;

	@Option(name="-t",aliases="--timeout",usage="connection timeout")
	private Integer timeout;

	@Option(name="-c",aliases="--alivecode",usage="alive http response code")
	private Integer aliveCode;

	private InputCommandLine() {}

	public static class CmdInputConverter {
		public static BasicInput convert(String[] args) throws CmdLineException{
			BasicInput result = new InputCommandLine();
			CmdLineParser parser = new CmdLineParser(result);
			parser.parseArgument(args);
			return result;
		}
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
