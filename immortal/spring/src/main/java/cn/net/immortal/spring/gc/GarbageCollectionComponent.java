package cn.net.immortal.spring.gc;

import com.sun.management.GarbageCollectionNotificationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextStartedEvent;
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
public class GarbageCollectionComponent implements ApplicationListener<ContextStartedEvent> {

    @Autowired
    private List<GarbageCollectionObserver> observers;

    private void gcNotify(GarbageCollectionNotificationInfo info) {
        try {
            observers.forEach(observer -> observer.gcNotify(info));
        } catch (Exception e) {
            //ignore
        }
    }

    @Override
    public void onApplicationEvent(ContextStartedEvent event) {
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
}
