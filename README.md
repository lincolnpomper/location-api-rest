

# Localizador de veículos

  **Esta é uma aplicação para avaliar conhecimentos nas seguintes tecnologias:**

 - Spring
 - JPA
 - REST
 - Maven
 - Swagger
 - Leitura de arquivos CSV
 - JAVA 8 (API de Datas, Stream, Interfaces)
 - Javascript com Ajax e
 - Bootstrap.

---

## Descrição

Essa aplicação determina o tempo percorrido ou em repouso de veículos passando por pontos de interesse (POI). Para simplificar o cálculo do resultado, não estão sendo calculadas as trajetórias usando a valocidade dos veículos e a Terra foi considerada como uma esfera.

---
**Para rodar o projeto:**
*Deve ser utilizado Java 8 ou maior para compilar o projeto.*
1. Clone esse repositório
2. Compile:
$ mvn package
3. Execute:
$ java -jar target/myapplication-0.0.1-SNAPSHOT.jar
4. Acesse a url:
http://localhost:8080

---

**Para consultas das APIs REST, está disponibilizado o documento Swagger em:**

http://localhost:8080/swagger-ui.html

Para usá-lo, acesse a url e siga os passos:

1. Clique para expandir um controller, por exemplo **vehicle-controller**.
2. Escolha **JSON** em **Response Content Type**.
3. Clique no botão **Try it out!** para fazer a requesição.

---