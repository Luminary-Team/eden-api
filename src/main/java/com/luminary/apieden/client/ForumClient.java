package com.luminary.apieden.client;

import com.luminary.apieden.model.client.ForumResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(url = "${api.mongo}", name="forumApi")
public interface ForumClient {
    @GetMapping("/forum")
    List<ForumResponse> findAll();
    @GetMapping("/forum/{id}")
    ForumResponse findById(@PathVariable String id);
}
