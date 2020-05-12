package cn.net.immortal.spring.scope;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RefreshScope implements Scope {

    private Map<String,Object> scope = new HashMap<>();

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        Object object = scope.get(name);
        if(Objects.nonNull(object)){
            return object;
        };
        object = objectFactory.getObject();
        scope.put(name, object);
        return object;
    }

    @Override
    public Object remove(String name) {
        return scope.remove(name);
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {

    }

    @Override
    public Object resolveContextualObject(String key) {
        return scope.remove(key);
    }

    @Override
    public String getConversationId() {
        return null;
    }
}
