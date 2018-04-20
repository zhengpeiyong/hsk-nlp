/**
 * Word分词器
 */
package com.ansj.vec.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.apdplat.word.WordSegmenter;
import org.apdplat.word.segmentation.SegmentationAlgorithm;
import org.apdplat.word.segmentation.Word;


/**
 * @author heave
 *
 */
public class WordSegment {

	/**
	 * word分词器
	 */
	public WordSegment() {
		// TODO Auto-generated constructor stub
	}

	public Map<String, String> segMore(String text) {
	    Map<String, String> map = new HashMap<String, String>();
	    for(SegmentationAlgorithm segmentationAlgorithm : SegmentationAlgorithm.values()){
	        map.put(segmentationAlgorithm.getDes(), seg(text, segmentationAlgorithm));
	    }
	    return map;
	}
	private static String seg(String text, SegmentationAlgorithm segmentationAlgorithm) {
	    StringBuilder result = new StringBuilder();
	    for(Word word : WordSegmenter.segWithStopWords(text, segmentationAlgorithm)){
	        result.append(word.getText()).append(" ");
	    
	    }
	    return result.toString();
	}
	
    public static void main(String[] args) {
         	
    	String path = "C:\\Users\\heave\\Desktop\\";
    	StringBuffer sb = new StringBuffer();
    	
		try {
			//读取语料源文件
	    	Stream<String> doc = Files.lines(Paths.get(path+"ICD-10病种词库.txt"), StandardCharsets.UTF_8);
			doc.forEach(str->sb.append(str)/*.append("\n")*/);

	    	//采用正向最大匹配算法进行分词处理
	    	String words = WordSegment.seg(sb.toString(),SegmentationAlgorithm.MaximumMatching);
	    	
	    	//将分词后的结果输出为训练词库
	    	Files.write(Paths.get(path+"词库.txt"), words.getBytes());
	    	      	    	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
		}
        
    }
    
}
