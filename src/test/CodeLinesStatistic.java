/** 
 
 * codelinesstatistic 2008-6-20 TangRen 
 
 * Copyright www.tonydev.cn 2008 
 
 */  
  
package test; 
  
  
  
import java.io.BufferedReader;  
  
import java.io.File;  
  
import java.io.FileReader;  
  
import java.io.IOException;  
  
  
  
/** 
 
 * @author Tang Ren 
 
 * @email: <a href="mailto:tangren1206@163.com">tangren1206@163.com</a> 
 
 *  
 
 * 代码行数统计器 
 
 */  
  
public class CodeLinesStatistic {  
  
  
  
    private static long sums = 0;  
  
  
  
    private static String suffixs[];  
  
  
  
    private static String target;  
  
  
  
    /** buffer flush 的最大值 * */  
  
    private static int FLUSH_FLAG = 1024 * 64;  
  
  
  
    private static StringBuffer statistics = new StringBuffer();  
  
  
  
    /** 
 
     * @param args 
 
     */  
  
    public static void main(String... args) throws IOException {  
  
        // 这里模拟命令行下的参数进行测试   
        args = new String[] { "A:/workspace/IBS", // 这里是项目的根目录   
  
                "java", "xml", "properties","jsp","js" }; // 这里是统计文件的后缀名   
  
  
  
        long startTimes = System.currentTimeMillis();  
  
        if (args.length > 1)  
  
            suffixs = new String[args.length - 1];  
  
        else {  
  
            System.out  
  
                    .println("As that : targetLocation , fileSuffix , fileSuffix . . .");  
  
            return;  
  
        }  
  
        for (int i = 0; i < args.length; i++) {  
  
            if (i == 0) {  
  
                target = args[i];  
  
            } else {  
  
                suffixs[i - 1] = args[i];  
  
            }  
  
        }  
  
        File targetFile = new File(target);  
  
        if (targetFile.exists()) {  
  
            statistic(targetFile);  
  
            System.out.println(statistics.toString());  
  
            System.out.println("All completement. U write [" + sums  
  
                    + "] lines code. did great job!");  
  
        } else {  
  
            System.out.println("File or Dir not exist : " + target);  
  
        }  
  
        System.out.println("Total times "  
  
                + (System.currentTimeMillis() - startTimes) + " ms");  
  
    }  
  
  
  
    /** 
 
     * 深度优先,统计文件行数 
 
     *  
 
     * @param file 
 
     * @throws IOException 
 
     */  
  
    private static void statistic(File file) throws IOException {  
  
        if (file.isDirectory()) {  
  
            File[] files = file.listFiles();  
  
            for (int i = 0; i < files.length; i++) {  
  
                statistic(files[i]);  
  
            }  
  
        }  
  
        if (file.isFile()) {  
  
            if (isMatchSuffixs(file)) {  
  
                sums += countFileTextLines(file);  
  
            }  
  
        }  
  
    }  
  
  
  
    /** 
 
     * 检查文件是否是制定后缀的文件 
 
     *  
 
     * @param file 
 
     * @return 
 
     */  
  
    private static boolean isMatchSuffixs(File file) {  
  
        String fileName = file.getName();  
  
        if (fileName.indexOf(".") != -1) {  
  
            String extName = fileName.substring(fileName.indexOf(".") + 1);  
  
            for (int i = 0; i < suffixs.length; i++) {  
  
                if (suffixs[i].equals(extName)) {  
  
                    return true;  
  
                }  
  
            }  
  
        }  
  
        return false;  
  
    }  
  
  
  
    /** 
 
     * 统计文件行数 
 
     *  
 
     * @param file 
 
     * @return 
 
     * @throws IOException 
 
     */  
  
    private static long countFileTextLines(File file) throws IOException {  
  
        long result = 0;  
  
        if (statistics.length() > FLUSH_FLAG) {  
  
            System.out.print(statistics.toString());  
  
            statistics = new StringBuffer();  
  
        }  
  
        statistics.append("Counting in ").append(file.getAbsolutePath());  
  
        BufferedReader br = new BufferedReader(new FileReader(file));  
  
        while (br.readLine() != null)  
  
            result++;  
  
        br.close();  
  
        statistics.append("   -  ").append(result).append("\n");  
  
        return result;  
  
    }  
  
}  