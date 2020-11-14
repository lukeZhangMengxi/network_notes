package lib;

import org.junit.AssumptionViolatedException;
import org.junit.Rule;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;

public class MyUnitTests {
    @Rule
    public final Stopwatch stopwatch = new Stopwatch() {
        protected void succeeded(long nanos, Description description) {
            System.out.println(description.getMethodName() + " succeeded, time taken " + nanos);
        }
 
        /**
         * Invoked when a test fails
         */
        protected void failed(long nanos, Throwable e, Description description) {
            System.out.println(description.getMethodName() + " failed, time taken " + nanos);
        }
 
        /**
         * Invoked when a test is skipped due to a failed assumption.
         */
        protected void skipped(long nanos, AssumptionViolatedException e,
                Description description) {
            System.out.println(description.getMethodName() + " skipped, time taken " + nanos);
        }
 
        /**
         * Invoked when a test method finishes (whether passing or failing)
         */
        protected void finished(long nanos, Description description) {
            System.out.println(description.getMethodName() + " finished, time taken " + nanos);
        }
 
    };
}