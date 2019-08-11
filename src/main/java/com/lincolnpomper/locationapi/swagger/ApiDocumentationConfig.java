package com.lincolnpomper.locationapi.swagger;

import io.swagger.annotations.Contact;
import io.swagger.annotations.ExternalDocs;
import io.swagger.annotations.Info;
import io.swagger.annotations.License;
import io.swagger.annotations.SwaggerDefinition;

@SwaggerDefinition(
        info = @Info(
                description = "Localizador de veículos por ponto de interesse.",
                version = "V12.0.12",
                title = "Localiza Veículos API",
                contact = @Contact(
                   name = "Lincoln Pompermaier",
                   email = "lincoln@pompermaier.com",
                   url = "http://www.lincoln-pompermaier.com"
                ),
                license = @License(
                   name = "Apache 2.0", 
                   url = "http://www.apache.org/licenses/LICENSE-2.0"
                )
        ),
        consumes = {"application/json", "application/xml"},
        produces = {"application/json", "application/xml"},
        schemes = {SwaggerDefinition.Scheme.HTTP, SwaggerDefinition.Scheme.HTTPS},
        externalDocs = @ExternalDocs(value = "Localiza Veículos Docs", url = "http://www.lincoln-pompermaier.com")
)
public interface ApiDocumentationConfig {

}