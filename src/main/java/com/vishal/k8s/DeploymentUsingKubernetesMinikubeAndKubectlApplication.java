package com.vishal.k8s;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DeploymentUsingKubernetesMinikubeAndKubectlApplication {

	@GetMapping("/message")
	public String displayMessage(){
		return "Congratulations you have successfully deployed your application to Kubernetes !!";
	}
	public static void main(String[] args) {
		SpringApplication.run(DeploymentUsingKubernetesMinikubeAndKubectlApplication.class, args);
	}

}
