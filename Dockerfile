# Use Eclipse Temurin JDK 17 as base image
FROM eclipse-temurin:17-jdk
EXPOSE 8080
ADD target/springboot-k8s-demo.jar springboot-k8s-demo.jar
ENTRYPOINT ["java","-jar","springboot-k8s-demo.jar"]