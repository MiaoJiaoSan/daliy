package cn.net.immortal.spring.gc;

import com.sun.management.GarbageCollectionNotificationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.management.NotificationBroadcaster;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

/**
 * @date: 2020/4/20
 */
@Component
@ComponentScan
public class GarbageCollectionComponent {

    @Autowired
    private List<GarbageCollectionObserver> observers;

    @PostConstruct
    public void afterSetProperties() {
        NotificationListener nl = (notification, name) -> {
            GarbageCollectionNotificationInfo info =
                    GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());
            gcNotify(info);
        };
        ManagementFactory.getGarbageCollectorMXBeans().forEach(bean ->
            ((NotificationBroadcaster) bean).addNotificationListener(nl,
                    notification ->
                            GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION.equals(notification.getType()),
                    bean.getName())
        );
    }

    private void gcNotify(GarbageCollectionNotificationInfo info) {
        observers.forEach(observer -> observer.gcNotify(info));
    }
}
