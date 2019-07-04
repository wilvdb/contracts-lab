Labs around Consumer Driven Contracts 


# Petclinic-gen

Swagger provides a Maven plugin for code generation based on contracts (OpenAPI 3 supported).

Below, a Swagger codegen plugin configuration based on Open API contract description to generate model an controller interface for Spring Boot application.
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

# Petclinic-contract

Use of Spring Cloud Contract to check API implementation with its Open API description.

# Donuts-factory

Sample use of Spring Cloud Contract based on Groovy DSL for describing contracts:
```
package contracts

org.springframework.cloud.contract.spec.Contract.make {
    description("""
        Donuts API
""")
    label("Get all donuts")
    request {
        method GET()
        url '/donuts'
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status OK()
        body(
                  sugar: $(regex('[0-9]+')) ,
                  flour: $(regex('[0-9]+')) ,
                  butter: $(regex('[0-9]+'))
              )
        headers {
            contentType(applicationJson())
        }
    }
}

```

This example implements a verifier, to check if controller implementation is compliant with the contract.
And a stub runner, to start WireMock setup by the contract directly. 

# Simpson-client-pact

Before using this project, start a pact broker.

## Start Pact Broker

Go to module docker and execute:
```docker-compose up```

It starts a pact broker backed by a PostgreSQL database on: ``http://llocalhost:8085``

## Install pact

Pact definition is located into ```ContractTest``` test class.
Contract installation is performed by : ``mvn install``

# Apicurio and Microcks

## Clone github repo

```
git clone https://github.com/Apicurio/apicurio-studio.git
cd apicurio-studio/distro/docker-compose
```

## Build keycloak image (custom build)

```
./setup.sh <YOUR_IP>
docker-compose -f docker-compose.keycloak.yml build
```

## Start images

```
docker-compose -f docker-compose.keycloak.yml -f docker-compose.microcks.yml -f docker-compose.apicurio.yml up
```

## Keycloack setup

At the first step, users must be added to real Apicuio and Microcks:
* Connect to `http://<YOUR_IP>:8090`
* Default credentials are: `admin / admi_password`

## Login

* Apicurio : ``http://<YOUR_IP>:8093``
* Microcks : ``http://<YOUR_IP>:8900``
