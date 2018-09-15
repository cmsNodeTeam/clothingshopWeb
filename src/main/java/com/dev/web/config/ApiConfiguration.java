package com.dev.web.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import com.dev.web.interceptor.GlobalInterceptor;
import com.dev.web.interceptor.WebInterceptor;
import com.dev.web.util.GlobalStatus;
import com.dev.web.util.JsonUtils;
import com.dev.web.util.RightsCode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class ApiConfiguration extends WebMvcConfigurationSupport {
	
	@Resource
    private void configureThymeleafStaticVars(ThymeleafViewResolver viewResolver) {
        if(viewResolver != null) {
            Map<String, Object> vars = new HashMap<>();
            vars.put("globals", new GlobalStatus());
            vars.put("rights", new RightsCode());
            viewResolver.setStaticVariables(vars);
        }
    }
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
		List<String> pathPatterns = new ArrayList<>();
		pathPatterns.add("/login");
		pathPatterns.add("/api/user/login");
		pathPatterns.add("/api/user/change_language");
		pathPatterns.add("/static/**");
		registry.addInterceptor(new GlobalInterceptor()).addPathPatterns("/*/**")
			.excludePathPatterns(pathPatterns);
		registry.addInterceptor(new WebInterceptor()).addPathPatterns("/web/*/**");
    }
	
	@Bean
	public LocaleResolver localeResolver() {
		CookieLocaleResolver cookieLocale = new CookieLocaleResolver();
		// 默认语言
		cookieLocale.setDefaultLocale(Locale.CHINA);
		cookieLocale.setCookieName("language");
		cookieLocale.setCookieMaxAge(7 * 24 * 3600);
		return cookieLocale;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
	}

	@Override
	protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters
			.stream()
			.filter((c) -> c instanceof AbstractJackson2HttpMessageConverter)
			.forEach((c) -> {
				ObjectMapper mapper = JsonUtils.getMapper();
				((AbstractJackson2HttpMessageConverter)c).setObjectMapper(mapper);
		});
	}
	
}
