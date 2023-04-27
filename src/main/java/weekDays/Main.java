package weekDays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

        private static final Logger logger = LogManager.getLogger(Main.class);

        public static void main(String[] args){

                Converter conv = new Converter();

                logger.info("result from the main logger: "+ conv.getDay(0));

        }
}
