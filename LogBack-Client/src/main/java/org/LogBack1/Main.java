package org.LogBack1;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import ch.qos.logback.classic.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Main {
    final static Logger logger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
    public static void main(String[] args) {

        //test the 0 case with LogBackInit to see difference between running with and without logback-console.xml

        Scanner scanner = new Scanner(System.in);
        int program = 0;

        System.out.println("Choose your log test: ");
        program = scanner.nextInt();

        switch(program){
            case 0:
                LogBackInit();
                break;
            case 1:
                TestLevels();
                break;
            case 2:
                InitConfig();
                break;
        }

    }
    public static void LogBackInit(){
        Logger logger = LoggerFactory.getLogger("org.LogBack1"); //set the name of Log
        logger.debug("Hello log world."); //show log at DEBUG level. We have others like INFO, WARN and ERROR.
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory(); //this calls the main factory
        StatusPrinter.print(lc);
        /* About the print above
            If no configuration of logback-console.xml is available, this print will show it,
            this occurs because the default policy of Logback is using ConsoleAppender as Appender.

            Appender is a class that sets the output destination.

            Also, this print shows the version of Logback, and finally, the state of your internal state.
            * if an error occurs, this will be showed at state.

            Documentation: https://logback.qos.ch/manual/introduction.html
         */
    }
    public static void TestLevels(){
        //set level for logger for test priority levels TRACK < DEBUG < INFO < WARN < ERROR
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        ch.qos.logback.classic.Logger rootLogger = lc.getLogger(Logger.ROOT_LOGGER_NAME); //root name
        rootLogger.setLevel(Level.INFO); // this is te parameter to filter logs

        Logger barlogger = LoggerFactory.getLogger("Root Child");

        //enabled
        rootLogger.warn("Warn logger");
        //disabled
        rootLogger.debug("Debug logger");
        //enabled
        barlogger.info("Info child logger");
        //disabled
        barlogger.debug("Debug child logger");
    }

    public static void InitConfig(){
        logger.info("Entering application.");

        Foo foo = new Foo();
        foo.doIt();
        logger.info("Exiting application.");
    }

    public static class Foo {
        final Logger logger = LoggerFactory.getLogger(Foo.class);

        public void doIt() {
            logger.debug("Did it again!");
        }
    }

}

