package com.example.demo;

import com.example.demo.model.Review;
import com.example.demo.model.task;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class DemoApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void contextLoads() {
	}

	@Test
	void testGetReviewEndpoint() throws Exception {

		Integer taskId = 1;

		mockMvc.perform(MockMvcRequestBuilders.get("/getRe")
						.param("task_id", taskId.toString()))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}


	@Test
	void testCreateReviewEndpoint() throws Exception {

		Review review = new Review();

		mockMvc.perform(MockMvcRequestBuilders.post("/createRe")
						.contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(review)))
				.andExpect(status().isOk());

	}
	@Test
	void testCreateTaskEndpoint() throws Exception {
		task task = new task();
		task.setTitle("Sample Title");
		task.setDescription("Sample Description");
		task.setPerformer(1);

		mockMvc.perform(MockMvcRequestBuilders.post("/create")
						.param("user_id", "1")
						.contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(task)))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}


	@Test
	void testDeleteTaskEndpoint() throws Exception {
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.delete("/delete")
				.param("task_id", "11"));
		resultActions.andExpect(status().isOk())
				.andExpect(content().string("Task deleted successfully"));
	}

	@Test
	void testEditTaskEndpoint() throws Exception {
		task task = new task();
		task.setId(7);
		task.setTitle("Sample Title");
		task.setDescription("Sample Description");
		task.setPerformer(1);

		mockMvc.perform(MockMvcRequestBuilders.post("/edit")
						.contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(task)))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testGetAllTasksEndpoint() throws Exception {
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/get"));

		resultActions.andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray());
	}

	private static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

