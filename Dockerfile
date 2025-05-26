# Usamos uma imagem do Maven para compilar o projeto
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Diretório de trabalho dentro do container
WORKDIR /app

# Copia todos os arquivos do seu projeto para dentro do container
COPY . .

# Compila o projeto e gera o .jar, pulando os testes para acelerar
RUN mvn clean package -DskipTests

# Agora começamos a imagem de runtime para rodar a aplicação
FROM eclipse-temurin:17

# Diretório de trabalho dentro do container
WORKDIR /app

# Copia o arquivo .jar gerado na etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta padrão do Spring Boot
EXPOSE 8080

# Define o comando para rodar a aplicação Java, limitando memória
ENTRYPOINT ["java", "-Xms64m", "-Xmx256m", "-jar", "app.jar"]
