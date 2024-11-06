package com.luminary.apieden.client;

import com.luminary.apieden.model.client.CreateUserRequest;
import com.luminary.apieden.model.client.CreateUserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "${api.neo4j}", name = "neo4jClient")
public interface Neo4jClient {
    @PostMapping("/user")
    CreateUserResponse createUser(@RequestBody CreateUserRequest request);
}
