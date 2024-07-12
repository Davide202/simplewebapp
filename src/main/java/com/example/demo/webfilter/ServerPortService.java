package com.example.demo.webfilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.context.ServletWebServerInitializedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.RequestHandledEvent;

@Service
public class ServerPortService {

    private  final Logger log = LoggerFactory.getLogger(this.getClass());

    private int port;

    public int getPort() {
        return port;
    }

    @EventListener
    public void onApplicationEvent(final ServletWebServerInitializedEvent event) {
        port = event.getWebServer().getPort();
        log.info("THE PORT IS {}",port);
    }

    @EventListener
    public void onRequest(final RequestHandledEvent event) {
        log.info(event.toString());
    }
}