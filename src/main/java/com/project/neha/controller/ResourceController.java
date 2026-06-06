
package com.project.neha.controller;

import com.project.neha.dto.ResourceRequest;
import com.project.neha.dto.ResourceResponse;
import com.project.neha.enums.ResourceCategory;
import com.project.neha.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/resources")
@RequiredArgsConstructor
public class ResourceController {

    private final ResourceService resourceService;

    @PostMapping
    public ResourceResponse createResource(@RequestBody ResourceRequest request) {
        return resourceService.createResource(request);
    }

    @GetMapping
    public List<ResourceResponse> getAllResources() {
        return resourceService.getAllResources();
    }

    @GetMapping("/{id}")
    public ResourceResponse getResourceById(@PathVariable UUID id) {
        return resourceService.getResourceById(id);
    }

    @GetMapping("/category/{category}")
    public List<ResourceResponse> getResourcesByCategory(
            @PathVariable ResourceCategory category
    ) {
        return resourceService.getResourcesByCategory(category);
    }

    @GetMapping("/company/{companyId}")
    public List<ResourceResponse> getResourcesByCompany(@PathVariable UUID companyId) {
        return resourceService.getResourcesByCompany(companyId);
    }

    @PutMapping("/{id}")
    public ResourceResponse updateResource(
            @PathVariable UUID id,
            @RequestBody ResourceRequest request
    ) {
        return resourceService.updateResource(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteResource(@PathVariable UUID id) {
        resourceService.deleteResource(id);
        return "Resource deleted successfully";
    }
}