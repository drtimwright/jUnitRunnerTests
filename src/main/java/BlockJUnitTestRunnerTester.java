import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tim on 16/12/14.
 */
public class BlockJUnitTestRunnerTester extends BlockJUnit4ClassRunner {

    private final Class<?> classUnderTest;
    private static final List<FrameworkMethod> methods;
    static {
        final String[] methodNames = new String[] { "1", "2", "3"};
        methods = new ArrayList<FrameworkMethod>();
        for (String s: methodNames) {
            methods.add(new FakeFrameworkMethod(s));
        }
    }

    public BlockJUnitTestRunnerTester(Class<?> classUnderTest) throws InitializationError {
        super(classUnderTest);
        this.classUnderTest = classUnderTest;
    }

    @Override
    protected List<FrameworkMethod> getChildren() {
        return methods;
    }

    @Override
    protected Description describeChild(FrameworkMethod child) {
        return Description.createTestDescription(classUnderTest, child.getName());
    }

    @Override
    protected void runChild(FrameworkMethod child, RunNotifier notifier) {
        Description d = Description.createTestDescription(classUnderTest, child.getName());
        notifier.fireTestStarted(d);
        System.out.println("done " + child.getName());
//        notifier.fireTestFailure(new Failure(d, new Exception()));
        notifier.fireTestFinished(d);
    }


    @Override
    protected Statement methodInvoker(FrameworkMethod method, Object test) {
       System.out.println("methodInvoker!");
        new Exception().printStackTrace();
        return super.methodInvoker(method, test);
    }

    @Override
    protected void validateInstanceMethods(List<Throwable> errors) {
        validatePublicVoidNoArgMethods(BeforeClass.class, true, errors);
        validatePublicVoidNoArgMethods(AfterClass.class, true, errors);
        validatePublicVoidNoArgMethods(After.class, false, errors);
        validatePublicVoidNoArgMethods(Before.class, false, errors);
        validateTestMethods(errors);
    }
}
