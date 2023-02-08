// package com.board.board_java.config;

// import org.springframework.context.annotation.Configuration;
// import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
// import org.springframework.web.method.support.HandlerMethodArgumentResolver;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// import java.util.List;

// @Configuration
// public class WebConfig implements WebMvcConfigurer {
//     @Override
//     public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//         PageableHandlerMethodArgumentResolver pageableArgumentResolver = new PageableHandlerMethodArgumentResolver();

//         pageableArgumentResolver.setOneIndexedParameters(true);

//         argumentResolvers.add(pageableArgumentResolver);

//     }
// }
