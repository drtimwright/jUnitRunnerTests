import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tim on 16/12/14.
 */
public class TestJUnitRunner extends Runner {

    private final Class<?> classUnderTest;

    public TestJUnitRunner(Class<?> classUnderTest) {
        this.classUnderTest = classUnderTest;
    }

    @Override
    public Description getDescription() {
        return Description.createSuiteDescription(classUnderTest);
    }

    @Override
    public void run(RunNotifier runNotifier) {

        String[] tests = new String[] {"1", "2", "3"};
        List<Description> descriptionList = new ArrayList<Description>();

        for (String test: tests)  {
            descriptionList.add(Description.createTestDescription(classUnderTest, test));
        }

        for (Description d: descriptionList) {
        	System.out.println(String.format("%s -> %s -> %s",
        			d.getClassName(),
        			d.getMethodName(),
        			d.getDisplayName()));
            runNotifier.fireTestStarted(d);
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (Description d: descriptionList) {
            runNotifier.fireTestFinished(d);
        }

    }
}
