//package com.restaurant.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.faces.config.*;
//
//@Configuration
//public class WebFlowConfig extends AbstractFacesFlowConfiguration {
//
//    @Bean
//    public FlowExecutor flowExecutor() {
//        return getFlowExecutorBuilder(flowRegistry())
//                .addFlowExecutionListener(new FlowFacesContextLifecycleListener())
//                .build();
//    }
//
//    @Bean
//    public FlowDefinitionRegistry flowRegistry() {
//        return getFlowDefinitionRegistryBuilder()
//                .setBasePath("/WEB-INF")
//                .addFlowLocationPattern("**/*-flow.xml").build();
//
//}
//}
