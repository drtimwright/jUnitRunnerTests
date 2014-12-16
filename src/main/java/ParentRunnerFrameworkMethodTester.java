import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.ParentRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

import java.awt.*;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tim on 16/12/14.
 */
public class ParentRunnerFrameworkMethodTester extends ParentRunner<FrameworkMethod> {


    private final List<FrameworkMethod> methods;
    private Class<?> classUnderTest;

    public ParentRunnerFrameworkMethodTester(Class<?> classUnderTest) throws InitializationError {
        super(classUnderTest);
        this.classUnderTest = classUnderTest;
        final String[] methodNames = new String[] { "1", "2", "3"};
        methods = new ArrayList<FrameworkMethod>();
        for (String s: methodNames) {
            methods.add(new FakeFrameworkMethod(s));
        }
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
}
