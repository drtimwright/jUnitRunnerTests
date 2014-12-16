import org.junit.runners.model.FrameworkMethod;

import java.lang.annotation.Annotation;

/**
* Created by tim on 16/12/14.
*/
class FakeFrameworkMethod extends FrameworkMethod {


    private final String name;

    public FakeFrameworkMethod(String name) {
        super(null);
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Annotation[] getAnnotations() {
        return new Annotation[0];
    }

    @Override
    public <T extends Annotation> T getAnnotation(Class<T> annotationType) {
        return null;
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
