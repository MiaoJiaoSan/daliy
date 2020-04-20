package cn.net.immortal.dubbo.demo;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.rpc.*;

import java.lang.reflect.Method;

public class SimpleInvoker<T> implements Invoker<T>{

    private T target;
    private URL url;
    private Class<T> cls;

    public SimpleInvoker(T target, URL url, Class<T> cls) {
        this.target = target;
        this.url = url;
        this.cls = cls;
    }

    @Override
    public Class<T> getInterface() {
        return this.cls;
    }

    @Override
    public Result invoke(Invocation invocation) throws RpcException {
        Object obj = null;
        try {
            Method method = cls.getMethod(invocation.getMethodName(),invocation.getParameterTypes());
            obj = method.invoke(target, invocation.getArguments());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RpcResult(obj);
    }

    @Override
    public URL getUrl() {
        return this.url;
    }

    @Override
    public boolean isAvailable() {
        return false;
    }

    @Override
    public void destroy() {

    }


}
