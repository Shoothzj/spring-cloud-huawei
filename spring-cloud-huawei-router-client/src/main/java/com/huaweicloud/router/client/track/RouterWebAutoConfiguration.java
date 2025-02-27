/*

  * Copyright (C) 2020-2022 Huawei Technologies Co., Ltd. All rights reserved.

  * Licensed under the Apache License, Version 2.0 (the "License");
  * you may not use this file except in compliance with the License.
  * You may obtain a copy of the License at
  *
  *     http://www.apache.org/licenses/LICENSE-2.0
  *
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
  */
package com.huaweicloud.router.client.track;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import feign.RequestInterceptor;

/**
 * 将服务端收到的HTTP请求头设置到线程上下文中， 供Client发送请求的时候使用。
 **/
@Configuration
public class RouterWebAutoConfiguration {
  @Configuration
  @ConditionalOnClass(WebMvcConfigurer.class)
  static class WebMvcConfigurerEnable {
    @Bean
    public WebMvcConfigurer canaryWebMvcConfigurer() {
      return new WebMvcConfigurer() {
        @Override
        public void addInterceptors(InterceptorRegistry registry) {
          registry.addInterceptor(new RouterHandlerInterceptor()).addPathPatterns("/**");
        }
      };
    }
  }

  @Bean
  public RequestInterceptor requestInterceptor() {
    return new RouterRequestInterceptor();
  }

  @Bean
  public ClientHttpRequestInterceptor restTemplateInterceptor(@Autowired(required = false) @LoadBalanced
      List<RestTemplate> restTemplates) {
    RouterRestTemplateInterceptor interceptor = new RouterRestTemplateInterceptor();
    if (restTemplates != null) {
      restTemplates.forEach(restTemplate -> restTemplate.getInterceptors().add(interceptor));
    }
    return interceptor;
  }
}
