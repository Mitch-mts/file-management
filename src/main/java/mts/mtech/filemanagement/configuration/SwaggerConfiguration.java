 package mts.mtech.filemanagement.configuration;

/**
 * @author Mitch
 */

 import io.swagger.v3.oas.models.OpenAPI;
 import io.swagger.v3.oas.models.info.Contact;
 import io.swagger.v3.oas.models.info.Info;
 import io.swagger.v3.oas.models.info.License;
 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;
 import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

 @Configuration
 public class SwaggerConfiguration {

     @Bean
     public OpenAPI customOpenAPI(){
         return new OpenAPI().info(apiInfo());
     }

     private Info apiInfo() {
         return new Info()
                 .title("File Management API")
                 .description("API for managing system files")
                 .version("2.0")
                 .contact(apiContact())
                 .license(apiLicense());
     }

     private License apiLicense(){
         return new License()
                 .name("MIT License")
                 .url("#");
     }

     private Contact apiContact(){
         return new Contact()
                 .name("MTech Innovations")
                 .email("bigmitchsystems@gmail.com")
                 .url("#");
     }
 }







