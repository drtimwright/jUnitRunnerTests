import java.util.ArrayList;
import java.util.List;

import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.ParentRunner;
import org.junit.runners.model.InitializationError;

/**
 * Created by tim on 16/12/14.
 */
public class ParentRunnerRunnerTester extends ParentRunner<Runner> {

    static class InternalRunner extends Runner {
        private final String name;
        private final Description description;

        public InternalRunner(String name, Class<?> classUnderTest) {
            this.name = name;
            this.description = Description.createTestDescription(classUnderTest, name);
        }

        @Override
        public Description getDescription() {
            return description;
        }

        @Override
        public void run(RunNotifier notifier) {
            notifier.fireTestStarted(description);
            System.out.println("done " + name);
//            notifier.fireTestFailure(new Failure(description, new Exception()));
            notifier.fireTestFinished(description);

        }
    }

    private final List<Runner> methods;

    public ParentRunnerRunnerTester(Class<?> classUnderTest) throws InitializationError {
        super(classUnderTest);
        final String[] methodNames = new String[] { "1", "2", "3"};
        methods = new ArrayList<Runner>();
        for (String s: methodNames) {
            methods.add(new InternalRunner(s, classUnderTest));
        }
    }

    @Override
    protected List<Runner> getChildren() {
        return methods;
    }

    @Override
    protected Description describeChild(Runner child) {
        return child.getDescription();
    }

    @Override
    protected void runChild(Runner child, RunNotifier notifier) {
        child.run(notifier);
    }
}
