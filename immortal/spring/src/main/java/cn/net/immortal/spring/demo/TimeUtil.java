package cn.net.immortal.spring.demo;

/**
 * @date: 2020/4/24
 */
public class TimeUtil {

    public static final String [] unit = new String[]{
            "毫秒", "秒","分钟","小时","天"
    };
    public static void main(String [] args){
        System.out.println(calc(System.currentTimeMillis()));
    }
    public static String calc(Long time){
        Long[] timeArray = new Long[]{
                time%1000,
                (time /= 1000)%60,
                (time /= 60)%60,
                (time /= 60)%24,
                time /24
        };
        StringBuilder builder = new StringBuilder();
        for(int i=0; i != timeArray.length; i++){
            if(timeArray[i]!=0){
                builder.insert(0,unit[i]).insert(0,timeArray[i]);
            }
        }
        return builder.toString();
    }
}
