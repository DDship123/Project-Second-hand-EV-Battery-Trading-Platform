package org.example.fe.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.fe.response.ApiResponse;
import org.example.fe.response.PostResponse;
import org.example.fe.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private RestTemplate restTemplate;
    private String apiBaseUrl = "http://localhost:8001";
    @Override
    public ApiResponse<List<PostResponse>> getAllPost() {
        ApiResponse<List<PostResponse>> response = new ApiResponse<>();

        try {
            // Create headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            // Create request entity
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

            // Make API call to backend
            ResponseEntity<ApiResponse<List<PostResponse>>> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/posts/top-rated",
                    HttpMethod.GET,
                    requestEntity,
                    new ParameterizedTypeReference<ApiResponse<List<PostResponse>>>(){}
            );

            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                // Get posts successful
                response.ok((List<PostResponse>) apiResponse.getBody().getPayload());
            } else {
                // Get posts failed
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("message", "Failed to retrieve posts");
                response.error(errorMap);
            }
        } catch (Exception e) {
            // Handle exceptions
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Failed to get posts: " + e.getMessage());
            response.error(errorMap);
        }

        return response;
    }



    @Override
    public ApiResponse<List<PostResponse>> getLatestPost() {
        ApiResponse<List<PostResponse>> response = new ApiResponse<>();
        try {
            // Create headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");
            // Create request entity
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
            // Make API call to backend
            ResponseEntity<ApiResponse<List<PostResponse>>> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/posts/latest",
                    HttpMethod.GET,
                    requestEntity,
                    new ParameterizedTypeReference<ApiResponse<List<PostResponse>>>(){}
            );
            if (apiResponse.getStatusCode().is2xxSuccessful() || apiResponse.getBody() != null) {
                // Get latest posts successful
                response.ok(apiResponse.getBody().getPayload());
            } else {
                // Get latest posts failed
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("message", "Failed to retrieve latest posts");
                response.error(errorMap);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Failed to get latest posts: " + e.getMessage());
            response.error(errorMap);
        }

        return response;
    }

    @Override
    public ApiResponse<List<PostResponse>> getAllPostBatery() {
        ApiResponse<List<PostResponse>> response = new ApiResponse<>();

        try {
            // Create headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            // Create request entity
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

            // Make API call to backend
            ResponseEntity<ApiResponse<List<PostResponse>>> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/posts/battery",
                    HttpMethod.GET,
                    requestEntity,
                    new ParameterizedTypeReference<ApiResponse<List<PostResponse>>>(){}
            );

            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                // Get battery posts successful
                response.ok((List<PostResponse>) apiResponse.getBody().getPayload());
            } else {
                // Get battery posts failed
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("message", "Failed to retrieve battery posts");
                response.error(errorMap);
            }
        } catch (Exception e) {
            // Handle exceptions
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Failed to get battery posts: " + e.getMessage());
            response.error(errorMap);
        }

        return response;

    }

    @Override
    public ApiResponse<List<PostResponse>> getAllPostVehicle() {
        ApiResponse<List<PostResponse>> response = new ApiResponse<>();

        try {
            // Create headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            // Create request entity
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

            // Make API call to backend
            ResponseEntity<ApiResponse<List<PostResponse>>> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/posts/vehicle",
                    HttpMethod.GET,
                    requestEntity,
                    new ParameterizedTypeReference<ApiResponse<List<PostResponse>>>(){}
            );

            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                // Get vehicle posts successful
                response.ok((List<PostResponse>) apiResponse.getBody().getPayload());
            } else {
                // Get vehicle posts failed
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("message", "Failed to retrieve vehicle posts");
                response.error(errorMap);
            }
        } catch (Exception e) {
            // Handle exceptions
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Failed to get vehicle posts: " + e.getMessage());
            response.error(errorMap);
        }

        return response;

    }

    @Override
    public ApiResponse<PostResponse> getPostDetail(int postID) {
        ApiResponse<PostResponse> response = new ApiResponse<>();

        try {
            // Create headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            // Create request entity
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

            // Make API call to backend
            ResponseEntity<ApiResponse<PostResponse>> apiResponse = restTemplate.exchange(
                        apiBaseUrl + "/api/posts/" + postID,
                    HttpMethod.GET,
                    requestEntity,
                    new ParameterizedTypeReference<ApiResponse<PostResponse>>() {}
            );

            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                // Get post detail successful
                response.ok(apiResponse.getBody().getPayload());
            } else {
                // Get post detail failed
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("message", "Failed to retrieve post detail");
                response.error(errorMap);
            }
        } catch (Exception e) {
            // Handle exceptions
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Failed to get post detail: " + e.getMessage());
            response.error(errorMap);
        }

        return response;

    }

    @Override
    public ApiResponse<List<PostResponse>> getLatestPosts() {
        ApiResponse<List<PostResponse>> apiResponse = new ApiResponse<>();
        try {
            ResponseEntity<ApiResponse<List<PostResponse>>> response = restTemplate.exchange(
                    apiBaseUrl + "/api/posts/latest",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<ApiResponse<List<PostResponse>>>() {}
            );
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                apiResponse.ok(response.getBody().getPayload());
                return apiResponse;
            }else {
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("message", "Failed to retrieve latest posts");
                apiResponse.error(errorMap);
                return apiResponse;
            }
        }catch (Exception e) {
            e.printStackTrace();
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Failed to get latest posts: " + e.getMessage());
            apiResponse.error(errorMap);
            return apiResponse;
        }
    }

    @Override
    public ApiResponse<List<PostResponse>> getLatestVehiclePosts() {
        ApiResponse<List<PostResponse>> response = new ApiResponse<>();
        try {
            ResponseEntity<ApiResponse<List<PostResponse>>> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/posts/latest/vehicle",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<ApiResponse<List<PostResponse>>>() {}
            );
            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                response.ok(apiResponse.getBody().getPayload());
                response.setMetadata(apiResponse.getBody().getMetadata());
            } else {
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("message", "Failed to retrieve latest vehicle posts");
                response.error(errorMap);
            }
        } catch (Exception e) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Failed to get latest vehicle posts: " + e.getMessage());
            response.error(errorMap);
        }
        return response;
    }

    @Override
    public ApiResponse<List<PostResponse>> getLatestBatteryPosts() {
        ApiResponse<List<PostResponse>> response = new ApiResponse<>();
        try {
            ResponseEntity<ApiResponse<List<PostResponse>>> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/posts/latest/battery",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<ApiResponse<List<PostResponse>>>() {}
            );
            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                response.ok(apiResponse.getBody().getPayload());
                response.setMetadata(apiResponse.getBody().getMetadata());
            } else {
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("message", "Failed to retrieve latest battery posts");
                response.error(errorMap);
            }
        } catch (Exception e) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Failed to get latest battery posts: " + e.getMessage());
            response.error(errorMap);
        }
        return response;
    }
    @Override
    public ApiResponse<List<PostResponse>> findAllPostByMemberCity(String city) {
        ApiResponse<List<PostResponse>> response = new ApiResponse<>();

        try {
            // Create headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            // Create request entity
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

            // Make API call to backend with city parameter
            ResponseEntity<ApiResponse<List<PostResponse>>> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/posts/by-city?city=" + city,
                    HttpMethod.GET,
                    requestEntity,
                    new ParameterizedTypeReference<ApiResponse<List<PostResponse>>>(){}
            );

            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                // Get posts by city successful
                response.ok((List<PostResponse>) apiResponse.getBody().getPayload());
            } else {
                // Get posts by city failed
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("message", "Failed to retrieve posts by city: " + city);
                response.error(errorMap);
            }
        } catch (Exception e) {
            // Handle exceptions
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Failed to get posts by city: " + e.getMessage());
            response.error(errorMap);
        }

        return response;
    }

    @Override
    public ApiResponse<List<PostResponse>> findAllPostByMemberCityAndProductType(String city, String productType) {
        ApiResponse<List<PostResponse>> response = new ApiResponse<>();

        try {
            // Create headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            // Create request entity
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

            // Make API call to backend with city and productType parameters
            ResponseEntity<ApiResponse<List<PostResponse>>> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/posts/city-type?"+
                            "productType=" + productType + "&city=" + city,
                    HttpMethod.GET,
                    requestEntity,
                    new ParameterizedTypeReference<ApiResponse<List<PostResponse>>>(){}
            );

            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                // Get posts by city and productType successful
                response.ok((List<PostResponse>) apiResponse.getBody().getPayload());
                response.setMetadata(apiResponse.getBody().getMetadata());
            } else {
                // Get posts by city and productType failed
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("message", "Failed to retrieve posts by city: " + city + " and productType: " + productType);
                response.error(errorMap);
            }
        } catch (Exception e) {
            // Handle exceptions
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Failed to get posts by city and productType: " + e.getMessage());
            response.error(errorMap);
        }

        return response;

    }

    @Override
    public ApiResponse<List<PostResponse>> findAllPostByMemberCityAndProductTypeAndTitle(String productType, String city, String title) {
        ApiResponse<List<PostResponse>> response = new ApiResponse<>();

        try {
            // Create headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            // Create request entity
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(apiBaseUrl + "/api/posts/city-type-title")
                    .queryParam("city", city)
                    .queryParam("productType", productType)
                    .queryParam("title", title);

            // Make API call to backend with city, productType and title parameters
            ResponseEntity<ApiResponse<List<PostResponse>>> apiResponse = restTemplate.exchange(
                    uriBuilder.toUriString(),
                    HttpMethod.GET,
                    requestEntity,
                    new ParameterizedTypeReference<ApiResponse<List<PostResponse>>>(){}
            );

            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                // Get posts by city, productType and title successful
                response.ok(apiResponse.getBody().getPayload());
                response.setMetadata(apiResponse.getBody().getMetadata());
            } else {
                // Get posts by city, productType and title failed
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("message", "Failed to retrieve posts by city: " + city + ", productType: " + productType + " and title: " + title);
                response.error(errorMap);
            }
        } catch (Exception e) {
            // Handle exceptions
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Failed to get posts by search criteria: " + e.getMessage());
            response.error(errorMap);
        }

        return response;

    }

    @Override
    public ApiResponse<List<PostResponse>> getAllPostByMemberId(int memberId) {
        ApiResponse<List<PostResponse>> response = new ApiResponse<>();
        try {
            //create header
            HttpHeaders headers = new HttpHeaders();
            headers.set("content-type", "application/json");
            //create request entity
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
            //make api call backend
            ResponseEntity<ApiResponse<List<PostResponse>>> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/posts/member/" + memberId,
                    HttpMethod.GET,
                    requestEntity,
                    new ParameterizedTypeReference<ApiResponse<List<PostResponse>>>() {
                    }
            );
            //check response
            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                response.ok(apiResponse.getBody().getPayload());
            } else {
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("message", "Failed to retrieve posts by member id: " + memberId);
                response.error(errorMap);

            }
        } catch(Exception e){
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Failed to get posts for member: " + e.getMessage());
            response.error(errorMap);
        }
        return response;
    }

    @Override
    public ApiResponse<PostResponse> create(PostResponse post) {
        ApiResponse<PostResponse> response = new ApiResponse<>();
        Map<String, String> error = new HashMap<>();

        try {
            // Create headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            // Create request entity with post data
            HttpEntity<PostResponse> requestEntity = new HttpEntity<>(post, headers);

            // Make API call to backend
            ResponseEntity<ApiResponse<PostResponse>> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/posts/create",
                    HttpMethod.POST,
                    requestEntity,
                    new ParameterizedTypeReference<ApiResponse<PostResponse>>(){}
            );

            if (apiResponse.getBody().getStatus().equals("SUCCESS") && apiResponse.getBody() != null) {
                // Create post successful
                response.ok(apiResponse.getBody().getPayload());
            } else {
                // Create post failed
                error.put("message", "Failed to create post");
                response.error(error);
            }
        }catch (HttpClientErrorException e) {
            //  Nếu backend trả lỗi 400 → parse JSON body
            try {
                String responseBody = e.getResponseBodyAsString();
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(responseBody);

                JsonNode errorNode = root.path("error");
                Map<String, String> errorMap = new HashMap<>();
                if (errorNode.isObject()) {
                    errorNode.fieldNames().forEachRemaining(
                            field -> errorMap.put(field, errorNode.get(field).asText())
                    );
                } else {
                    errorMap.put("message", "Registration failed");
                }

                response.error(errorMap);
            } catch (Exception parseEx) {
                error.put("message", "Registration failed: " + e.getMessage());
                response.error(error);
            }

        } catch (Exception e) {
            // Handle exceptions
            error.put("message", "Failed to create post: " + e.getMessage());
            response.error(error);
        }
        return response;
    }

    @Override
    public ApiResponse<PostResponse> update(PostResponse post) {
        ApiResponse<PostResponse> response = new ApiResponse<>();
        Map<String,String> error = new HashMap<>();

        try {
            // Create headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            // Create request entity with post data
            HttpEntity<PostResponse> requestEntity = new HttpEntity<>(post, headers);

            // Make API call to backend
            ResponseEntity<ApiResponse<PostResponse>> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/posts/"+ post.getPostsId(),
                    HttpMethod.PUT,
                    requestEntity,
                    new ParameterizedTypeReference<ApiResponse<PostResponse>>(){}
            );

            if (apiResponse.getBody().getStatus().equals("SUCCESS") && apiResponse.getBody() != null) {
                // Create post successful
                response.ok(apiResponse.getBody().getPayload());
            } else {
                // Create post failed
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("message", "Failed to create post");
                response.error(errorMap);
            }
        }catch (HttpClientErrorException e) {
            //  Nếu backend trả lỗi 400 → parse JSON body
            try {
                String responseBody = e.getResponseBodyAsString();
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(responseBody);

                JsonNode errorNode = root.path("error");
                Map<String, String> errorMap = new HashMap<>();
                if (errorNode.isObject()) {
                    errorNode.fieldNames().forEachRemaining(
                            field -> errorMap.put(field, errorNode.get(field).asText())
                    );
                } else {
                    errorMap.put("message", "Registration failed");
                }

                response.error(errorMap);
            } catch (Exception parseEx) {
                error.put("message", "Registration failed: " + e.getMessage());
                response.error(error);
            }

        } catch (Exception e) {
            // Handle exceptions
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Failed to create post: " + e.getMessage());
            response.error(errorMap);
        }
        return response;
    }

    @Override
    public ApiResponse<PostResponse> updateStatus(int postId, String status) {
        try {
            // Create headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            formData.add("postsId", String.valueOf(postId));
            formData.add("status", status);

            // Create request entity
            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(formData,headers);

            // Make API call to backend
            ResponseEntity<ApiResponse<PostResponse>> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/posts/admin/update/status",
                    HttpMethod.PUT,
                    requestEntity,
                    new ParameterizedTypeReference<ApiResponse<PostResponse>>(){}
            );

            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                // Update status successful
                return apiResponse.getBody();
            } else {
                // Update status failed
                ApiResponse<PostResponse> response = new ApiResponse<>();
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("message", "Failed to update post status");
                response.error(errorMap);
                return response;
            }
        } catch (Exception e) {
            // Handle exceptions
            ApiResponse<PostResponse> response = new ApiResponse<>();
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Failed to update post status: " + e.getMessage());
            response.error(errorMap);
            return response;
        }
    }

    @Override
    public ApiResponse<PostResponse> delete(int postId) {
        ApiResponse<PostResponse> response = new ApiResponse<>();

        try {
            // Create headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            // Create request entity
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

            // Make API call to backend
            ResponseEntity<ApiResponse<PostResponse>> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/posts/" + postId,
                    HttpMethod.DELETE,
                    requestEntity,
                    new ParameterizedTypeReference<ApiResponse<PostResponse>>(){}
            );

            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                // Delete post successful
                response.ok(apiResponse.getBody().getPayload());
            } else {
                // Delete post failed
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("message", "Failed to delete post");
                response.error(errorMap);
            }
        } catch (Exception e) {
            // Handle exceptions
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Failed to delete post: " + e.getMessage());
            response.error(errorMap);
        }

        return response;

    }

    @Override
    public ApiResponse<List<PostResponse>> getAllPostByStatus(String status) {
        ApiResponse<List<PostResponse>> response = new ApiResponse<>();

        try {
            // Create headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // Create request entity
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

            // Make API call to backend
            ResponseEntity<ApiResponse<List<PostResponse>>> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/posts/admin/status/" + status,
                    HttpMethod.GET,
                    requestEntity,
                    new ParameterizedTypeReference<ApiResponse<List<PostResponse>>>(){}
            );

            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                // Delete post successful
                response.ok(apiResponse.getBody().getPayload());
            } else {
                // Delete post failed
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("message", "Failed to get post for status");
                response.error(errorMap);
            }
        } catch (Exception e) {
            // Handle exceptions
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Failed to get post for status: " + e.getMessage());
            response.error(errorMap);
        }

        return response;
    }

    @Override
    public ApiResponse<List<PostResponse>> findAllPostByProductTypeAndPostTitle(String productType, String postTitle) {
        ApiResponse<List<PostResponse>> response = new ApiResponse<>();

        try {
            // Create headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            // Create request entity
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

            // Make API call to backend
            ResponseEntity<ApiResponse<List<PostResponse>>> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/posts/type-title?"+
                            "productType=" + productType + "&title=" + postTitle,
                    HttpMethod.GET,
                    requestEntity,
                    new ParameterizedTypeReference<ApiResponse<List<PostResponse>>>(){}
            );

            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                // Delete post successful
                response.ok(apiResponse.getBody().getPayload());
                response.setMetadata(apiResponse.getBody().getMetadata());
            } else {
                // Delete post failed
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("message", "Failed to get post for type and title");
                response.error(errorMap);
            }
        } catch (Exception e) {
            // Handle exceptions
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Failed to get post for type and title: " + e.getMessage());
            response.error(errorMap);
        }

        return response;
    }

}
