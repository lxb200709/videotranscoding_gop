package cloudproject.videotranscoding;

import org.kohsuke.args4j.Option;

public class ParseCmdLine {

	@Option(name = "-inputvideo", usage = "input video folder url")
	private String inputvideoFolderURL = null;

	@Option(name = "-outputvideo", usage = "output video folder url")
	private String outputvideoFileURL = null;

	@Option(name = "-outputdata", usage = "output data folder url")
	private String outputdataFileURL = null;

	@Option(name = "-oWidth", usage = "output video width")
	private Integer oWidth = 0;

	@Option(name = "-oHeight", usage = "output video height")
	private Integer oHeight = 0;

	@Option(name = "-framerate", usage = "output video framerate")
	private Integer framerate = 0;

	@Option(name = "-biterate", usage = "output video biterate")
	private Integer biterate = 0;
	
	@Option(name = "-runtimes", usage = "the times the program will run")
	private Integer runtimes = 0;

	@Option(name = "-vcodec", usage = "output video codec")
	private String vcodec = null;

	@Option(name = "-ofmt", usage = "output video format")
	private String ofmt = null;

	public String getInputvideoFolderURL() {
		return inputvideoFolderURL;
	}

	public String getOutputvideoFileURL() {
		return outputvideoFileURL;
	}

	public String getOutputdataFileURL() {
		return outputdataFileURL;
	}

	public Integer getoHeight() {
		return oHeight;
	}
	
	

	public Integer getRuntimes() {
		return runtimes;
	}

	public Integer getFramerate() {
		return framerate;
	}

	public Integer getBiterate() {
		return biterate;
	}

	public String getVcodec() {
		return vcodec;
	}

	public String getOfmt() {
		return ofmt;
	}

	public Integer getoWidth() {
		return oWidth;
	}

	/**
	 * If you want to get the args-Array from the command line use the signature
	 * <tt>run(String[] args)</tt>. But then there must not be a run() because
	 * that is executed prior to this.
	 * 
	 * @param args
	 *            The arguments as specified on the command line
	 */
	public void run(String[] args) {
		System.out.println("SampleStarter.run(String[])");
		System.out.println("- args.length: " + args.length);
		for (String arg : args)
			System.out.println("  - " + arg);
		// System.out.println(this);
	}

}
