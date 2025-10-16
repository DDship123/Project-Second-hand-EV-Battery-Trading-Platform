package org.example.fe.service.impl;

import org.example.fe.entity.ApiResponse;
import org.example.fe.entity.PostResponse;
import org.example.fe.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
                    apiBaseUrl + "/api/posts/by-city-and-type?city=" + city + "&productType=" + productType,
                    HttpMethod.GET,
                    requestEntity,
                    new ParameterizedTypeReference<ApiResponse<List<PostResponse>>>(){}
            );

            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                // Get posts by city and productType successful
                response.ok((List<PostResponse>) apiResponse.getBody().getPayload());
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
            // create Url
            String url = apiBaseUrl + "/api/posts/search?city=" + city + "&productType=" + productType + "&title=" + title;

            // Make API call to backend with city, productType and title parameters
            ResponseEntity<ApiResponse<List<PostResponse>>> apiResponse = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    requestEntity,
                    new ParameterizedTypeReference<ApiResponse<List<PostResponse>>>(){}
            );

            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                // Get posts by city, productType and title successful
                response.ok((List<PostResponse>) apiResponse.getBody().getPayload());
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

        try {
            // Create headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            // Create request entity with post data
            HttpEntity<PostResponse> requestEntity = new HttpEntity<>(post, headers);
            requestEntity.getBody();

            // Make API call to backend
            ResponseEntity<ApiResponse<PostResponse>> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/posts/create",
                    HttpMethod.POST,
                    requestEntity,
                    new ParameterizedTypeReference<ApiResponse<PostResponse>>(){}
            );

            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                // Create post successful
                response.ok(apiResponse.getBody().getPayload());
            } else {
                // Create post failed
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("message", "Failed to create post");
                response.error(errorMap);
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

}
