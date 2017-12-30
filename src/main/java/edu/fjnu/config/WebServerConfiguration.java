package edu.fjnu.config;

import org.apache.catalina.valves.AccessLogValve;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

/**
 * Created by xiaozemaliya on 2017/4/2.
 */
@SpringBootConfiguration
public class WebServerConfiguration {

    @Bean
    public EmbeddedServletContainerFactory createEmbeddedServletContainerFactory(){
        TomcatEmbeddedServletContainerFactory factory=new TomcatEmbeddedServletContainerFactory();

        factory.setPort(11122);
        factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND,"/jsps/unauthorized.jsp"));

        factory.addInitializers((servletContext) -> {
            System.out.println("=========servletContext startup=========");
        });

        factory.addContextValves(getLogAccessLogValve());

        factory.addConnectorCustomizers((connector) ->{

            Http11NioProtocol protocol=(Http11NioProtocol)connector.getProtocolHandler();
            protocol.setMaxConnections(2000);//设置最大连接数
            protocol.setMaxThreads(500);//设置最大连接数

        } );

        return factory;
    }

    private AccessLogValve getLogAccessLogValve(){

        AccessLogValve log=new AccessLogValve();

        log.setDirectory("E:/IDEA/DocumentSystem/tomcat-log");
        log.setEnabled(true);
        log.setPattern("%h %l %u %t \"%r\" %s %b");
        log.setPrefix("ssm-springboot-log");
        log.setSuffix(".txt");

        return log;

    }

}
