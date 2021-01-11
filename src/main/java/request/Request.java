package request;

import Base.BaseRequest;
import io.restassured.response.Response;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class Request extends BaseRequest{

        public static void emptyStore() {

            Response response = given()
                    .headers(headersMapping())
                    .log()
                    .all()
                    .when()
                    .get("api/books/")
                    .prettyPeek()
                    .then()
                    .extract().response();

            Assert.assertEquals("{}", response.asString());


        }

        public static void titleCheck() {

            Response titleCheckResponse = given()
                    .headers(headersMapping())
                    .body("{ \n" +
                            "  \"author\": \"Author Example\"\n" +
                            "}")
                    .log()
                    .all()
                    .when()
                    .put("api/books/")
                    .prettyPeek()
                    .then()
                    .statusCode(400)
                    .extract().response();

            String titleResponseBody = "{ \n" +
                    "  \"error\": \"Field 'title' is required\" \n" +
                    "}";
            Assert.assertEquals(titleResponseBody, titleCheckResponse.prettyPrint());



        }

        public static void authorCheck() {

            Response authorCheckResponse = given()
                    .headers(headersMapping())
                    .body("{ \n" +
                            "  \"title\": \"Title Example\"\n" +
                            "}")
                    .log()
                    .all()
                    .when()
                    .put("api/books/")
                    .prettyPeek()
                    .then()
                    .statusCode(400)
                    .extract().response();

            String authorResponseBody = "{ \n" +
                    "  \"error\": \"Field 'author' is required\" \n" +
                    "}";
            Assert.assertEquals(authorResponseBody, authorCheckResponse.prettyPrint());
        }

        public static void titleEmptyCheck() {

            Response titleCheckResponse = given()
                    .headers(headersMapping())
                    .body("{ \n" +
                            "  \"author\": \"Example\", \n" +
                            "  \"title\": \"\"\n" +
                            "}")
                    .log()
                    .all()
                    .when()
                    .put("api/books/")
                    .prettyPeek()
                    .then()
                    .statusCode(400)
                    .extract().response();

            String titleResponseBody = "{ \n" +
                    "  \"error\": \"Field 'title' cannot be empty.\" \n" +
                    "}";
            Assert.assertEquals(titleResponseBody, titleCheckResponse.prettyPrint());

        }

        public static void authorEmptyCheck() {

            Response authorCheckResponse = given()
                    .headers(headersMapping())
                    .body("{ \n" +
                            "  \"author\": \"\", \n" +
                            "  \"title\": \"Example\"\n" +
                            "}")
                    .log()
                    .all()
                    .when()
                    .put("api/books/")
                    .prettyPeek()
                    .then()
                    .statusCode(400)
                    .extract().response();

            String authorResponseBody = "{ \n" +
                    "  \"error\": \"Field 'author' cannot be empty.\" \n" +
                    "}";
            Assert.assertEquals(authorResponseBody, authorCheckResponse.prettyPrint());
        }

        public static void ReadOnlyId() {

            Response response = given()
                    .headers(headersMapping())
                    .body("{ \n" +
                            "  \"id\": 61, \n" +
                            "  \"author\": \"John Smith\", \n" +
                            "  \"title\": \"Reliability of late night deployments\" \n" +
                            "}")
                    .log()
                    .all()
                    .when()
                    .put("api/books/")
                    .prettyPeek()
                    .then()
                    .extract().response();

            Assert.assertEquals(405, response.statusCode());

        }

        public static String newBook() {


            Response addBooking = given()
                    .headers(headersMapping())
                    .body("{ \n" +
                            "  \"author\": \"Example 1\", \n" +
                            "  \"title\": \"Example 2\" \n" +
                            "}")
                    .log()
                    .all()
                    .when()
                    .put("api/books/")
                    .prettyPeek()
                    .then()
                    .statusCode(201).extract().response();

            String bookId = addBooking.jsonPath().getString("id");

            return bookId;
        }

        public static void getBookingPath(String bookId) {


            Response getBooking = given()
                    .headers(headersMapping())
                    .log()
                    .all()
                    .when()
                    .get("api/books/" + bookId + "")
                    .prettyPeek()
                    .then()
                    .statusCode(200).extract().response();

            String body = "{ \n" +
                    "  \"id\": " + bookId + ", \n" +
                    "  \"author\": \"Example 1\", \n" +
                    "  \"title\": \"Example 2\" \n" +
                    "}";

            Assert.assertEquals(body, getBooking.prettyPrint());
        }

        public static String getBooking() {



            Response getBooking = given()
                    .headers(headersMapping())
                    .log()
                    .all()
                    .when()
                    .get("api/books/")
                    .prettyPeek()
                    .then()
                    .statusCode(200).extract().response();

            String body = getBooking.jsonPath().getString("0");

            return body;
        }

        public static void duplicateBook(String body) {

            Response addBooking = given()
                    .headers(headersMapping())
                    .body(body)
                    .log()
                    .all()
                    .when()
                    .put("api/books/")
                    .prettyPeek()
                    .then()
                    .statusCode(400).extract().response();

            String errorMessage = "{ \n" +
                    "  \"error\": \"Title and author already exist.\" \n" +
                    "}";
            Assert.assertEquals(errorMessage, addBooking.prettyPrint());
        }
}
