# VideoTranscoding_GOP
The code splits video files into GOPs and transcodes (i.e. converting spatial resolution) each GOP separately with [FFmpeg](https://ffmpeg.org) wrapped by [Xuggler](http://www.xuggle.com/xuggler/). This code can be used for generating some metadata of the transcoding process. These data can be used for simulation and evaluation purposes. 

Users of this code are requested to cite our paper: 

**Xiangbo Li, Mohsen Amini Salehi, Magdi Bayoumi, Rajkumar Buyya,   [CVSS: A Cost-Efficient and QoS-Aware Video Streaming Using Cloud Services](http://hpcclab.org/paperPdf/ccgrid16/CloudTranscodingconf.pdf), in Proceedings of 16th ACM/IEEE International Conference on Cluster Cloud and Grid Computing (CCGrid ’16), Columbia, May 2016.**

For each GOP, the code will generate the following information:
- input size,
- output size, 
- transcoding execution time, 
- presentation (display) time,
- and it outputs the whole transcoded video as well.

We have made available the entire source code, the runnable jar, and the library jar (to be imported in your project). The runnable jar can be downloaded from here.

Below is a short explanation on how to work with the code:

# Input
Video files with different codecs (e.g. H.264, mpeg4, mpeg2, vp8 etc.), different containers (e.g. mp4, mkv, avi, mov etc.), different resolutions (e.g. 1080p, 720p, 480p, 320p etc), different bit rate and frame rate.

# Video Segmentation and Transcoding
Split each video file into GOP level and transcode each GOP independently. In this code, we use FFmpeg wrapper Xuggler to perform transcoding operations.

# Output
1. Transcoded video streams.
2. GOP processing metadata (e.g inputsize, outputsize, transcoding time, presenting time etc)


# videotranscoding_gop_lib.jar
This jar file is a library that can be imported to your project. To use that, you need to do the following to transcode the resolution of original video to 320x180:

```java
import cloudproject.videotranscoding

String inputUrl = "path/to/your/video/file"
String outputUrl = "path/to/your/output/folder"
String dataUrl = "path/to/store/metadata"

```
You can use: transcodeTo640x480, transcodeTo720x480, transcodeTo720x576 in the similar way

You can refer to the methods listed in `Converter.java` to see the list of all types of transcoding that can be performed.

# videoTranscoding_gop_runnable.jar
This jar file can be run stand alone, you can download it from [here](https://drive.google.com/file/d/0B-fMIDSRxJ_4azB6cDFMaUZJQ2s/view?usp=sharing). The following example transcodes (changes the spatial resolution) to 320x180. The sample video file is provided in the `resources` folder. 

```java
e.g. Java -jar videoTranscoding_gop_runnable.jar "./resources/inputvideo" "./resources/outputvideo" "./resources/outputdata"

```

Please note that this jar file is just for testing purposes. If you have specific transcoding demands, you can import the library jar file (mentioned above) and you will have access to all transcoding functionalities implemented in `Converter.java`. You can also download and import the whole source code. Then change the parameters in lines 95 to 100 of `TestTranscoder.java` based on your specific transcoding requirement.


## Licence

Source code can be found on [github](https://github.com/lxb200709/videotranscoding_gop).

Developed by [Xiangbo Li](https://www.linkedin.com/in/xiangbo-li-2893582a)
