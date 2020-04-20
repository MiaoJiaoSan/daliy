package cn.net.immortal.spring.gc;

import com.sun.management.GarbageCollectionNotificationInfo;

public interface GarbageCollectionObserver {

    void gcNotify(GarbageCollectionNotificationInfo info);
}
