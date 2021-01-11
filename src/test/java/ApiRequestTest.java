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
            emptyStore();

        }

        //2
        @Test
        public void titleAndAuthorCheckApiTest(){

            logger.info(" Check title required");
            titleCheck();

            logger.info(" Check Author required");
            authorCheck();
        }

        //3
        @Test
        public void titleAndAuthorEmptyCheckTest(){

            logger.info(" Check Emtpy Title ");
            titleEmptyCheck();

            logger.info(" Check Empty Author ");
            authorEmptyCheck();
        }

        //4
        @Test
        public void readOnlyIdApiTest(){

            logger.info(" Check Read Only ID");
            ReadOnlyId();

        }

        //5
        @Test
        public void newBookApiTest(){

            logger.info("Add New Book");
            String bookId = newBook();

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