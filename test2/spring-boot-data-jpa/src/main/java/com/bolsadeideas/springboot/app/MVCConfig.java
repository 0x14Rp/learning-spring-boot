package com.bolsadeideas.springboot.app;




import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;







//Crear configuracion para asignar uploads como un recurso fuera del proyecto spring 
@Configuration
public class MVCConfig implements WebMvcConfigurer {



	

	/*
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.addResourceHandlers(registry);
		
		registry.addResourceHandler("/uploads/**")
		.addResourceLocations("file:/C:/Temp/uploads/");
		
	
		
	}

	
	
	*/
	
}


