package base;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
 
public class PrimaryGenerater {
 
    private static final String SERIAL_NUMBER = "XXXXX"; // 流水号格式
    private static PrimaryGenerater primaryGenerater = null;
 
    private static String SNO = "000000";
    
    private PrimaryGenerater() {
    }
 
    /**
     * 取得PrimaryGenerater的单例实现
     * 
     * @return
     */
    public static PrimaryGenerater getInstance() {
        if (primaryGenerater == null) {
            synchronized (PrimaryGenerater.class) {
                if (primaryGenerater == null) {
                    primaryGenerater = new PrimaryGenerater();
                }
            }
        }
        return primaryGenerater;
    }
 
    /**
     * 生成下一个编号
     */
    public synchronized String generaterNextNumber() {
        String jno = null;
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            int count = SERIAL_NUMBER.length();
            DecimalFormat df = new DecimalFormat("0000");
            this.SNO = df.format(1 + Integer.parseInt(SNO));
            if(this.SNO.length() > count)
            {
            	this.SNO =df.format(1);
            }
            
            jno = "JNO"+formatter.format(date)
                    + this.SNO;
            
        return jno;
    }
    
    public static void main(String[] args)
    {
    	PrimaryGenerater key = PrimaryGenerater.getInstance();
    	int i=0;
    	while(++i<10000)
    	{
    		
    		System.out.println(key.generaterNextNumber());
    	}
    	
    }
}