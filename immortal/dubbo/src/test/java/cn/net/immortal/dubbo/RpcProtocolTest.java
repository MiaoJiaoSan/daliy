package cn.net.immortal.dubbo;

import cn.net.immortal.dubbo.demo.SimpleInvoker;
import cn.net.immortal.dubbo.demo.service.RpcService;
import cn.net.immortal.dubbo.demo.service.impl.RpcServiceImpl;
import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.rpc.*;
import cn.net.immortal.dubbo.demo.RmiProtocol;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class RpcProtocolTest {

    public static final URL URL = com.alibaba.dubbo.common.URL.valueOf("rmi://127.0.0.1:9080/" + RpcService.class.getName());

    //网络调用RpcService代理 Invoker ，Invoker调用真实对象
    @Test
    public void provider() throws IOException {
        RpcService rpcService = new RpcServiceImpl();
        SimpleInvoker<RpcService> rpcServiceSimpleInvoker = new SimpleInvoker<>(rpcService, URL, RpcService.class);
        Protocol protocol = new RmiProtocol();
        protocol.export(rpcServiceSimpleInvoker);
        System.out.println("start");
        System.in.read();
    }


    //RpcService代理调用 refer生成的Invoker，invoker调用invoke到网络
    @Test
    public void consumer() throws IOException {
        Protocol protocol = new RmiProtocol();
        Invoker<RpcService> refer = protocol.refer(RpcService.class, URL);

        RpcService rpcService = (RpcService)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class[]{RpcService.class},
                (Object proxy, Method method, Object[] args)->
                        refer.invoke(new RpcInvocation(method,args)).recreate());
        System.out.println(rpcService.sayHello("dubbo"));
    }

    public static final ExtensionLoader<Protocol> PROTOCOL_EXTENSION_LOADER =  ExtensionLoader.getExtensionLoader(Protocol.class);
    public static final ExtensionLoader<ProxyFactory> PROXY_FACTORY_EXTENSION_LOADER =  ExtensionLoader.getExtensionLoader(ProxyFactory.class);

    //网络调用RpcService代理 Invoker ，Invoker调用真实对象
    @Test
    public void dubboProvider() throws IOException {
        RpcService rpcService = new RpcServiceImpl();
        Protocol protocol = PROTOCOL_EXTENSION_LOADER.getAdaptiveExtension();
        ProxyFactory proxyExtension = PROXY_FACTORY_EXTENSION_LOADER.getAdaptiveExtension();
        Invoker<RpcService> invoker = proxyExtension.getInvoker(rpcService, RpcService.class, URL);
        protocol.export(invoker);
        System.out.println("start");
        System.in.read();
    }


    //RpcService代理调用 refer生成的Invoker，invoker调用invoke到网络
    @Test
    public void dubboConsumer() throws IOException {
        Protocol protocol = PROTOCOL_EXTENSION_LOADER.getAdaptiveExtension();
        Invoker<RpcService> refer = protocol.refer(RpcService.class, URL);

        ProxyFactory proxyFactory = PROXY_FACTORY_EXTENSION_LOADER.getAdaptiveExtension();
        RpcService rpcService = proxyFactory.getProxy(refer);
        System.out.println(rpcService.sayHello("dubbo"));
    }

    public static final URL RGISTRY_URL = com.alibaba.dubbo.common.URL.valueOf("registry://www.miaojiaosan.com:2181/com.alibaba.dubbo.registry.RegistryService?registry=zookeeper");
    public static final URL DUBBO_URL = com.alibaba.dubbo.common.URL.valueOf("dubbo://127.0.0.1:9080/" + RpcService.class.getName());

    @Test
    public void dubboRegistryProvider() throws IOException {
        RpcService rpcService = new RpcServiceImpl();
        Protocol protocol = PROTOCOL_EXTENSION_LOADER.getAdaptiveExtension();
        ProxyFactory proxyExtension = PROXY_FACTORY_EXTENSION_LOADER.getAdaptiveExtension();
        com.alibaba.dubbo.common.URL url = RGISTRY_URL.addParameter(Constants.EXPORT_KEY, DUBBO_URL.toFullString());
        Invoker<RpcService> invoker = proxyExtension.getInvoker(rpcService, RpcService.class, url);
        protocol.export(invoker);
        System.out.println("start");
        System.in.read();
    }


    //RpcService代理调用 refer生成的Invoker，invoker调用invoke到网络
    @Test
    public void dubboRegistryConsumer() throws IOException {
        Protocol protocol = PROTOCOL_EXTENSION_LOADER.getAdaptiveExtension();
        com.alibaba.dubbo.common.URL url = RGISTRY_URL.addParameter(Constants.REGISTRY_KEY,"zookeeper");
        Invoker<RpcService> refer = protocol.refer(RpcService.class, url);
        Invoker<RpcService> invoker = new Invoker<RpcService>() {

            private Invoker<RpcService> innerRefer = refer;
            @Override
            public Class<RpcService> getInterface() {
                return innerRefer.getInterface();
            }

            @Override
            public Result invoke(Invocation invocation) throws RpcException {
                return innerRefer.invoke(invocation);
            }

            @Override
            public com.alibaba.dubbo.common.URL getUrl() {
                return innerRefer.getUrl();
            }

            @Override
            public boolean isAvailable() {
                return false;
            }

            @Override
            public void destroy() {

            }
        };

        ProxyFactory proxyFactory = PROXY_FACTORY_EXTENSION_LOADER.getAdaptiveExtension();
        RpcService rpcService = proxyFactory.getProxy(invoker);
        System.out.println(rpcService.sayHello("dubbo"));
    }

    @Test
    public void random(){
        for(int i = 0; i < 14; i++) {
            System.out.println((int)(Math.random()*10));
        }
    }
}
