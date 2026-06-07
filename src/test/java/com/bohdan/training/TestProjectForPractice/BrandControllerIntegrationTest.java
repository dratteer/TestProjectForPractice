package com.bohdan.training.TestProjectForPractice;

import com.bohdan.training.TestProjectForPractice.entity.Brand;
import com.bohdan.training.TestProjectForPractice.repository.BrandRepository;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class BrandControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BrandRepository brandRepository;

    @Test
    void createBrand_shouldSaveBrandAndReturnId() throws Exception {
        String requestJson = """
            {
              "name": "Samsung"
            }
            """;

        String responseJson = mockMvc.perform(post("/api/brands")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Number id = JsonPath.read(responseJson, "$.id");
        Long createdId = id.longValue();

        Brand savedBrand = brandRepository.findById(createdId).orElseThrow();

        assertThat(savedBrand.getName()).isEqualTo("Samsung");
    }

    @Test
    void getById_shouldReturnBrand() throws Exception {
        Brand brand = new Brand();
        brand.setName("Apple");

        Brand savedBrand = brandRepository.save(brand);

        mockMvc.perform(get("/api/brands/{id}", savedBrand.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedBrand.getId()))
                .andExpect(jsonPath("$.name").value("Apple"));
    }
}