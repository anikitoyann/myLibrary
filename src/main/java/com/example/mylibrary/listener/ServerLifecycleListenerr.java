package com.example.mylibrary.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Date;

@WebListener
public class ServerLifecycleListenerr implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Server started at"+new Date());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Server destroyed at"+new Date());
    }
}
