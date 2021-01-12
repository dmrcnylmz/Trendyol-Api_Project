import Base.BaseRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import static request.Request.*;


public class ApiRequestTest extends BaseRequest {
        protected static Logger logger = LogManager.getLogger(ApiRequestTest.class);

        //1
        @Test
        public void checkStoreEmty(){

            logger.info(" Check Store Empty");
            verifyEmptyStoreAtStarts();

        }

        //2
        @Test
        public void titleAndAuthorCheckApiTest(){

            logger.info(" Check title required");
            verifyTitleRequired();

            logger.info(" Check Author required");
            verifyAuthorRequired();
        }

        //3
        @Test
        public void titleAndAuthorEmptyCheckTest(){

            logger.info(" Check Emtpy Title ");
            verifyEmptyTitle();

            logger.info(" Check Empty Author ");
            verifyEmptyAuthor();
        }

        //4
        @Test
        public void readOnlyIdApiTest(){

            logger.info(" Check Read Only ID");
            verifyIDReadOnly();

        }

        //5
        @Test
        public void newBookApiTest(){

            logger.info("Add New Book");
            String bookId = verifyCreateNewBook();

            logger.info("Check Book");
            getBookingPath(bookId);
        }

        //6
        @Test
        public void duplicateBookApiTest(){

            logger.info("Get Booking");
            String body = getBooking();

            logger.info("Duplicate Book Check");
            duplicateBook(body);

        }
}