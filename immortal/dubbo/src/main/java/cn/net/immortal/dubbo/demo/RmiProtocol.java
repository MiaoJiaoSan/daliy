package cn.net.immortal.dubbo.demo;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.Version;
import com.alibaba.dubbo.rpc.*;
import com.alibaba.dubbo.rpc.protocol.rmi.RmiRemoteInvocation;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.remoting.rmi.RmiServiceExporter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.rmi.RemoteException;

public class RmiProtocol implements Protocol {


    public static final int PORT = 9080;

    //不能使用配置端口时的默认端口
    @Override
    public int getDefaultPort() {
        return PORT;
    }

    @Override
    public <T> Exporter<T> export(Invoker<T> invoker) throws RpcException {
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        URL url = invoker.getUrl();
//        rmiServiceExporter.setRegistryHost(url.getHost());
        rmiServiceExporter.setRegistryPort(url.getPort(PORT));
        rmiServiceExporter.setServiceName(url.getPath());
        Class<T> anInterface = invoker.getInterface();
        rmiServiceExporter.setServiceInterface(anInterface);
        //这个地方拿不到目标类，所以将Invoker代理为service，Invoker内包含了目标类，Invoker.invoke调用目标方法
        //为什么不强转为实际invoker，需要支持多协议
        T service = (T)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class[]{invoker.getInterface()},
                (Object proxy, Method method, Object[] args)->{
                    if (!method.getName().equals("sayHello")) {
                        return method.invoke(new Object());
                    }
                    return invoker.invoke(new RpcInvocation(method,args)).recreate();
                });
        rmiServiceExporter.setService(service);
        try {
            rmiServiceExporter.afterPropertiesSet();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <T> Invoker<T> refer(Class<T> type, URL url) throws RpcException {
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        if(url.getParameter(Constants.DUBBO_VERSION_KEY,Version.getProtocolVersion()).equals(Version.getProtocolVersion())){
            rmiProxyFactoryBean.setRemoteInvocationFactory(RmiRemoteInvocation::new);
        }
        rmiProxyFactoryBean.setServiceUrl(url.toIdentityString());
        rmiProxyFactoryBean.setServiceInterface(type);
        rmiProxyFactoryBean.setCacheStub(true);
        rmiProxyFactoryBean.setLookupStubOnStartup(true);
        rmiProxyFactoryBean.setRefreshStubOnConnectFailure(true);
        rmiProxyFactoryBean.afterPropertiesSet();
        return new SimpleInvoker<T>((T)rmiProxyFactoryBean.getObject(), url, type);
    }

    @Override
    public void destroy() {

    }


}
