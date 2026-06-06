package com.project.neha.service;

import com.project.neha.dto.ResourceRequest;
import com.project.neha.dto.ResourceResponse;
import com.project.neha.entity.Company;
import com.project.neha.entity.Resource;
import com.project.neha.entity.User;
import com.project.neha.enums.ResourceCategory;
import com.project.neha.repository.CompanyRepository;
import com.project.neha.repository.ResourceRepository;
import com.project.neha.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ResourceService {

    private final ResourceRepository resourceRepository;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    private ResourceResponse mapToResponse(Resource resource) {
        return new ResourceResponse(
                resource.getId(),
                resource.getUser().getId(),
                resource.getUser().getName(),
                resource.getCompany() == null ? null : resource.getCompany().getId(),
                resource.getCompany() == null ? null : resource.getCompany().getName(),
                resource.getTitle(),
                resource.getUrl(),
                resource.getCategory(),
                resource.getCreatedAt()
        );
    }

    public ResourceResponse createResource(ResourceRequest request) {

        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Company company = null;
        if (request.companyId() != null) {
            company = companyRepository.findById(request.companyId())
                    .orElseThrow(() -> new RuntimeException("Company not found"));
        }

        Resource resource = new Resource();
        resource.setUser(user);
        resource.setCompany(company);
        resource.setTitle(request.title());
        resource.setUrl(request.url());
        resource.setCategory(
                request.category() == null
                        ? ResourceCategory.OTHER
                        : request.category()
        );

        Resource savedResource = resourceRepository.save(resource);

        return mapToResponse(savedResource);
    }

    public List<ResourceResponse> getAllResources() {
        return resourceRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public ResourceResponse getResourceById(UUID id) {
        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resource not found"));

        return mapToResponse(resource);
    }

    public List<ResourceResponse> getResourcesByCategory(ResourceCategory category) {
        return resourceRepository.findByCategory(category)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<ResourceResponse> getResourcesByCompany(UUID companyId) {
        return resourceRepository.findByCompany_Id(companyId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public ResourceResponse updateResource(UUID id, ResourceRequest request) {

        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resource not found"));

        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Company company = null;
        if (request.companyId() != null) {
            company = companyRepository.findById(request.companyId())
                    .orElseThrow(() -> new RuntimeException("Company not found"));
        }

        resource.setUser(user);
        resource.setCompany(company);
        resource.setTitle(request.title());
        resource.setUrl(request.url());
        resource.setCategory(
                request.category() == null
                        ? ResourceCategory.OTHER
                        : request.category()
        );

        Resource updatedResource = resourceRepository.save(resource);

        return mapToResponse(updatedResource);
    }

    public void deleteResource(UUID id) {
        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resource not found"));

        resourceRepository.delete(resource);
    }
}