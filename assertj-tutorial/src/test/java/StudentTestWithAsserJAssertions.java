import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class StudentTestWithAsserJAssertions {

    @Test
    void createStudent() {
        final Student student = new Student("Jhon", "Walker", 27);

        Assertions.assertThat(student.getName()).isEqualTo("Jhon")
                .doesNotContainOnlyWhitespaces()
                .isNotEmpty()
                .isNotBlank()  // isNotEmpty & doesNotContainOnlyWhitespaces
                .isEqualTo("Jhon")
                .isEqualToIgnoringCase("jhon")
                .isIn("kubilay", "Jhon", "Mark")
                .isNotIn("kubilay", "Clark")
                .startsWith("J")
                .doesNotStartWith("k")
                .endsWith("n")
                .doesNotEndWith("y")
                .contains("ho")
                .containsIgnoringCase("JHON")
                .contains(Arrays.asList("J","h","o","n"))
                .hasSize(4)
                .matches("^J\\w{2}n$")
                .as("Student's name %s", student.getName());

    }
}
