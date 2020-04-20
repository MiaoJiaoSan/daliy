package cn.net.immortal.spring.gc;

import com.sun.management.GarbageCollectionNotificationInfo;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @date: 2020/4/20
 */
@Component
public class GarbageCollectionObserverImpl implements GarbageCollectionObserver {
    @Override
    public void gcNotify(GarbageCollectionNotificationInfo info) {
        String gctype = info.getGcAction();
        if ("end of minor GC".equals(gctype)) {
            gctype = "Young Gen GC";
        } else if ("end of major GC".equals(gctype)) {
            gctype = "Old Gen GC";
        }
        System.out.println(gctype);
    }

    @PostConstruct
    public void afterSetProperties(){
        new Thread(() -> {
            long start = System.currentTimeMillis();
            for (;;)
            {
                long now = System.currentTimeMillis();

                if (now - start > 5000)
                    break;

                byte[] bytes = new byte[10485760];
            }
        }).start();

    }
}
