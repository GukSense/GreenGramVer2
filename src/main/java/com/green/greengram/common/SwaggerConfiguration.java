package com.green.greengram.common;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable   //  스프링에게 설정 파일이라고 알림
@OpenAPIDefinition(
        info = @Info(
                title = "그린그램"
                , description = "Greengram with React"
                , version = "v1"
        )

)
public class SwaggerConfiguration {
}
