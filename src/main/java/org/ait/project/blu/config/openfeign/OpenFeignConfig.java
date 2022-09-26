package org.ait.project.blu.config.openfeign;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.ait.project.blu.config.exception.OpenFeignClientHandler;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OpenFeignConfig {

  @Bean
  public OpenFeignClientHandler feignErrorDecoder() {
    return new OpenFeignClientHandler();
  }

  @Bean
  public Encoder multipartFormEncoder() {
    return new SpringFormEncoder(
            new SpringEncoder(
                    () -> new HttpMessageConverters(
                            new RestTemplate().getMessageConverters())));
  }

}
