package com.fhr.readwritedemo.swagger;

import org.springframework.context.annotation.Bean;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * swagger配置类
 * @author fhr
 * @since 2017/07/29
 */
@EnableSwagger2
public class ApplicationSwaggerConfig {
	@Bean
	public Docket addUserDocket() {
		// 摘要
		Docket docket = new Docket(DocumentationType.SWAGGER_2);
		// api描述信息
		ApiInfo apiInfo = new ApiInfo("读写分离demo", "Api测试读写分离", "V1.0", "", "rarandemo@gmail.com", "", "");
		docket.apiInfo(apiInfo);
		return docket;
	}

}
