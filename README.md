Labs around Consumer Driven Contracts with Apicurio and Microcks.

# Code Generation

Swagger provides a Maven plugin for code generation based on contracts (OpenAPI 3 supported).

Below, a Swagger codegen plugin configuration based on Open API contract for ``petclinic-gen`` sample.
```
<plugin>
    <groupId>io.swagger.codegen.v3</groupId>
    <artifactId>swagger-codegen-maven-plugin</artifactId>
    <version>3.0.8</version>
    <executions>
        <execution>
            <goals>
                <goal>generate</goal>
            </goals>
            <configuration>
                <inputSpec>${project.basedir}/src/main/resources/api.yml</inputSpec>
                <language>spring</language>
                <apiPackage>${default.package}.controller</apiPackage>
                <modelPackage>${default.package}.model.dto</modelPackage>
                <invokerPackage>${default.package}.handler</invokerPackage>
                <generateSupportingFiles>false</generateSupportingFiles>
                <generateApiTests>false</generateApiTests>
                <configOptions>
                    <java8>true</java8>
                    <interfaceOnly>true</interfaceOnly>
                </configOptions>
            </configuration>
        </execution>
    </executions>
</plugin>
```
* ``inputSpec`` specifies API contract location.
* ``language`` specifies the generation target.
* ``apiPackage`` specifies the package where to generate API representation.
* ``generateSupportingFiles`` for generating  basic implementation.
* ``generateApiTests`` for integration test generation.
* ``interfaceOnly`` for generating only contract interface