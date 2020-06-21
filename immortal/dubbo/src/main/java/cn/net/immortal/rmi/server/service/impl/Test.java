package cn.net.immortal.rmi.server.service.impl;
import com.sun.management.GarbageCollectionNotificationInfo;
import com.sun.management.GcInfo;

import javax.management.Notification;
import javax.management.NotificationBroadcaster;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

/**
 * @date: 2020/4/15
 */
public class Test  {

    public static void main(String[] args) {
        NotificationListener nl = (notification, name) -> {
            GarbageCollectionNotificationInfo info =
                    GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());
            String gctype = info.getGcAction();
            if ("end of minor GC".equals(gctype)) {
                gctype = "Young Gen GC";
            } else if ("end of major GC".equals(gctype)) {
                gctype = "Old Gen GC";
            }
            System.out.println(gctype);
        };

        List<GarbageCollectorMXBean> list = ManagementFactory.getGarbageCollectorMXBeans();

        for (GarbageCollectorMXBean bean : list)
            ((NotificationBroadcaster) bean).addNotificationListener(nl, null, bean.getName());


        long start = System.currentTimeMillis();

        for (;;)
        {
            long now = System.currentTimeMillis();

            if (now - start > 5000)
                break;

            byte[] bytes = new byte[1048576];
        }
    }
}
