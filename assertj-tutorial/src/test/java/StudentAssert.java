import org.assertj.core.api.AbstractAssert;

import java.util.Objects;

public class StudentAssert extends AbstractAssert<StudentAssert, Student> {

    protected StudentAssert(Student student) {
        super(student, StudentAssert.class);
    }

    public static StudentAssert assertThat(Student actual) {
        return new StudentAssert(actual);
    }

    public StudentAssert hasName(String name) {
        isNotNull();
        if (!Objects.equals(name, actual.getName())) {
            failWithMessage("Expected student's name %s but was found %s", name, actual.getName());
        }
        return this;
    }
}