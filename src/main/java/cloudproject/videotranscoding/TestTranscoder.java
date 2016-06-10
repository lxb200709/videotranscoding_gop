package cloudproject.videotranscoding;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.io.FilenameUtils;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;



public class TestTranscoder {
	
	/*public TestTranscoder(){
		
	}*/
	
	/*
	 * URL file
	 */
	public static String inputVideoFolderURL = null;
	public static String outputVideoFolderURL = null;
	public static String outputDataFolderURL = null;
	public static String vcodec = null;
	public static String ofmt = null;
	public static int oWidth = 0;
	public static int oHeight = 0;
	public static int frameRate = 0;
	public static int biteRate = 0;
	public static int runTimes = 0;
	
	
	public static void main(String[] args) throws IOException, InterruptedException{
		
	//public void Transcoder(String[] args)throws IOException, InterruptedException{	
		//Print out everything in the console into the file
		/*printOutputFile pof = new printOutputFile();  
	  	pof.printOutToFile("cloudTranscoding_console");*/
	    
		/*
		String[] args = {
				"-inputvideo", "/home/yamini/Documents/transcoding_resources/inputvideo",
                "-outputvideo", "/home/yamini/Documents/transcoding_resources/outputvideo",
                "-outputdata", "/home/yamini/Documents/transcoding_resources/outputdata",
                "-oWidth",  "0",
                "-oHeight", "0",
                "-framerate", "0",
                "-biterate", "0",
                "-vcodec", "libx264",
                "-ofmt", null,
                "-runtimes", "2"
                };*/
		
		
	    ParseCmdLine pcl = new ParseCmdLine();
		CmdLineParser parser = new CmdLineParser(pcl);
		try {
	        parser.parseArgument(args);
			
	        inputVideoFolderURL = pcl.getInputvideoFolderURL();
		    System.out.println("Input video directory: " +  inputVideoFolderURL);
	       
		  	outputVideoFolderURL = pcl.getOutputvideoFileURL();
			System.out.println("Output video directory: " + outputVideoFolderURL);
		  	
		  	outputDataFolderURL = pcl.getOutputdataFileURL();
			System.out.println("Output data directory: " + outputDataFolderURL);
		  	
		    oWidth = pcl.getoWidth();
		    oHeight = pcl.getoHeight();
		    System.out.println("The output spatial resolution is: " + oWidth+"x"+oHeight);
			  
			frameRate = pcl.getFramerate();
			System.out.println("The output frame rate is: " + frameRate);

			biteRate = pcl.getBiterate();
			System.out.println("The output bit rate is: " + biteRate);

		    vcodec = pcl.getVcodec();
			System.out.println("The output codec is: " + vcodec);

		    ofmt = pcl.getOfmt();
		    System.out.println("The output formate is: " + ofmt);
		    
		    runTimes = pcl.getRuntimes();
		    System.out.println("The program is gonna rum: " + runTimes + " times.");
  	
		  	
	     } catch (CmdLineException e) {
	        // handling of wrong arguments
	        System.err.println(e.getMessage());
	        parser.printUsage(System.err);
	     }
	     
		  

          
	      File inputVideoFolder = new File(inputVideoFolderURL);
	      
		  File[] listOfFiles = inputVideoFolder.listFiles();
		  
		  for (int i = 0; i < listOfFiles.length; i++) {
			  File inputfile = listOfFiles[i];
			  String inputUrl = inputfile.getAbsolutePath();
			  System.out.println(inputUrl);
			  
			  String name = inputfile.getName().toLowerCase();
			  if (inputfile.isFile()) {
				  if(name.endsWith(".mp4") || name.endsWith(".mpg") || name.endsWith(".avi") ||
					 name.endsWith(".ts") || name.endsWith(".flv") || name.endsWith(".3gp") ||
					 name.endsWith(".mov") || name.endsWith(".mkv") || name.endsWith(".ogg") ||
					 name.endsWith(".dv") || name.endsWith(".webm") || name.endsWith(".3gp2") ||
					 name.endsWith(".m2v") || name.endsWith(".m4v") || name.endsWith(".wmv")) {
                     
					 //find file basic name 
					 String fileName = FilenameUtils.getBaseName(name);
					 String extension = FilenameUtils.getExtension(name);
					 
					 //name output video with original name + cv
					 //String outputFileName = fileName + "_cv";
					 String outputFile = fileName + "." + extension;
					 
					 
                     String datafile = fileName + ".txt"; 
				     
				     if(oWidth != 0 && oHeight !=0){
				         datafile = fileName + "To" + String.valueOf(oWidth)  + "X" + String.valueOf(oHeight) + ".txt";
				         outputFile = fileName + "To" + String.valueOf(oWidth)  + "X" + String.valueOf(oHeight) + "." + extension;

				     }else if(frameRate !=0) {
				    	 datafile = fileName + "To" + String.valueOf(frameRate) + ".txt";
				    	 outputFile = fileName + "To" + String.valueOf(frameRate) +  "." + extension;
				     }else if(biteRate != 0) {
				    	 datafile = fileName + "To" + String.valueOf(biteRate) + ".txt";
				    	 outputFile = fileName + "To" + String.valueOf(biteRate) + "." + extension;
				     }else if(vcodec != null) {
				    	 datafile = fileName + "To" + vcodec + ".txt";
				    	 outputFile = fileName + "To" + vcodec + "." + extension;
				     }else if(ofmt != null){
				    	 datafile = fileName + "To" + ofmt + ".txt";
				    	 outputFile = fileName + "To" + ofmt + "." + ofmt;
				     }
					 
					 
	                 //define output folder
				     File outputVideoFolder = new File(outputVideoFolderURL);
				     File outputvideo = new File(outputVideoFolder, outputFile);		     
				     String outputUrl = outputvideo.getAbsolutePath();
				     
				    // System.out.println(outputUrl);
                     
				     //define output .txt file
				     
				     File dataFolder = new File(outputDataFolderURL);
				     File data = new File(dataFolder, datafile);
				     String outputData = data.getAbsolutePath();
				     
				     
					  for(int j=0; j<runTimes; j++){
						  System.out.println("***Cloud Server is transcoding: " + name + " to " + outputFile);
						  transcodeVideo(inputUrl,outputUrl,outputData, oWidth, oHeight, frameRate, biteRate, vcodec, ofmt);
					  }	   		  
				  }   
			  }
		  } 
		
      }
   

   
	   public static void transcodeVideo(String inputUrl, String outputUrl, String outputData, int setWidth, int setHeight, int setFrameRate, int setVBiteRate, String setVCodec, String setFmt) throws IOException, InterruptedException{
		 
		   TreeMap<Integer, Long> gopTranscodingTimeMap = new TreeMap<Integer, Long>();
		   		   
		   Converter ts = new Converter(inputUrl, outputUrl);
		   ts.setupStreams(setWidth, setHeight, setFrameRate, setVBiteRate, setVCodec, setFmt);
		   gopTranscodingTimeMap = ts.run();
		   
		   PrintWriter pw = new PrintWriter(new FileWriter(outputData, true));
		   pw.printf("%-16s%-25s%-16s%-16s%-16s", "GOP#", "TranscodingTime", "Pts", "InputSize", "outputSize");
		   pw.println("\n");
		   
		   
			//System.out.format("%-16s%-16s%-16s%-25s%-16s%-16s", "Resolution", "GOP#", "TranscodingTime", "Pts", "InputSize", "outputSize");
			//System.out.println("\n");
		   int size = ts.getGopIdList().size();
		   for(int i=0; i<size; i++ ){
				/*System.out.format("%-16s%-16d%-25d%-16d%-16d%-16d", "320X180", ts.getGopIdList().get(i), ts.getGopTranscodingTimeList().get(i), 
						          ts.getGopPts().get(i), ts.getGopInputSize().get(i), ts.getGopOutputSize().get(i));
				System.out.println("\n");*/
				
				pw.printf("%-16d%-25d%-16d%-16d%-16d", ts.getGopIdList().get(i), ts.getGopTranscodingTimeList().get(i), 
						          ts.getGopPts().get(i), ts.getGopInputSize().get(i), ts.getGopOutputSize().get(i));
				pw.println("\n");
			}
		   pw.close();
		  
	   }
	   
		
	   /*public static void transcodeTo320x180(String inputUrl, String outputUrl, String outputData) throws IOException, InterruptedException{
		   final int oWidth = 320;
		   final int oHeight = 180;
		   final int frameRate = 24;
		   final int biteRate = 0;
		   final String vcodec = null;
		   final String ofmt = null;
		   TreeMap<Integer, Long> gopTranscodingTimeMap = new TreeMap<Integer, Long>();
		   
		   Converter ts = new Converter(inputUrl, outputUrl);
		   ts.setupStreams(oWidth, oHeight, frameRate, biteRate, vcodec, ofmt);
		   gopTranscodingTimeMap = ts.run();
		   
		   
		   
		   PrintWriter pw = new PrintWriter(new FileWriter(outputData, true));
		   pw.printf("%-16s%-16s%-25s%-16s%-16s%-16s", "Resolution", "GOP#", "TranscodingTime", "Pts", "InputSize", "outputSize");
		   pw.println("\n");
		   
		   
			//System.out.format("%-16s%-16s%-16s%-25s%-16s%-16s", "Resolution", "GOP#", "TranscodingTime", "Pts", "InputSize", "outputSize");
			//System.out.println("\n");
		   int size = ts.getGopIdList().size();
		   for(int i=0; i<size; i++ ){
				System.out.format("%-16s%-16d%-25d%-16d%-16d%-16d", "320X180", ts.getGopIdList().get(i), ts.getGopTranscodingTimeList().get(i), 
						          ts.getGopPts().get(i), ts.getGopInputSize().get(i), ts.getGopOutputSize().get(i));
				System.out.println("\n");
				
				pw.printf("%-16s%-16d%-25d%-16d%-16d%-16d", "320X180", ts.getGopIdList().get(i), ts.getGopTranscodingTimeList().get(i), 
						          ts.getGopPts().get(i), ts.getGopInputSize().get(i), ts.getGopOutputSize().get(i));
				pw.println("\n");
			}
		   pw.close();
	   }*/

}
