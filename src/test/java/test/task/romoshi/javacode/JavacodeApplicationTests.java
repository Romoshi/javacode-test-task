package test.task.romoshi.javacode;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;

import java.util.HashMap;

import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
class JavacodeApplicationTests {

	private static ConfigurableApplicationContext context;

	@BeforeAll
	public static void setUp() {
		context = SpringApplication.run(JavacodeApplication.class);
	}

	@AfterAll
	public static void tearDown() {
		context.stop();
	}

	private final String baseUri = "http://localhost:5050/";

	@Test
	void depositWalletTest() {
		HashMap<String, Object> jsonTest = new HashMap<>();
		jsonTest.put("walletId", "123R");
		jsonTest.put("operationType", "DEPOSIT");
		jsonTest.put("amount", 123);

		RestAssured.given()
				.contentType("application/json")
				.body(jsonTest)
				.when()
				.post(baseUri + "api/v1/wallet")
				.then()
				.statusCode(HttpStatus.OK.value());
	}

	@Test
	void withdrawOkStatusTest() {
		HashMap<String, Object> jsonTest = new HashMap<>();
		jsonTest.put("walletId", "123R");
		jsonTest.put("operationType", "WITHDRAW");
		jsonTest.put("amount", 123);

		RestAssured.given()
				.contentType("application/json")
				.body(jsonTest)
				.when()
				.post(baseUri + "api/v1/wallet")
				.then()
				.statusCode(HttpStatus.OK.value());
	}

	@Test
	void withdrawBadRequestStatusTest() {
		HashMap<String, Object> jsonTest = new HashMap<>();
		jsonTest.put("walletId", "123R");
		jsonTest.put("operationType", "WITHDRAW");
		jsonTest.put("amount", 123123214);

		RestAssured.given()
				.contentType("application/json")
				.body(jsonTest)
				.when()
				.post(baseUri + "api/v1/wallet")
				.then()
				.statusCode(HttpStatus.BAD_REQUEST.value());
	}

	@Test
	void validJsonTest() {
		HashMap<String, Object> jsonTest = new HashMap<>();
		jsonTest.put("walletId", "123R");
		jsonTest.put("operationType", "DEPOSIT");
		jsonTest.put("amnt", 123);

		RestAssured.given()
				.contentType("application/json")
				.body(jsonTest)
				.when()
				.post(baseUri + "api/v1/wallet")
				.then()
				.statusCode(HttpStatus.BAD_REQUEST.value());
	}

	@Test
	void walletExistTest() {
		RestAssured.when()
				.get(baseUri + "api/v1/wallets/randomId")
				.then()
				.statusCode(HttpStatus.NOT_FOUND.value());
	}
}
