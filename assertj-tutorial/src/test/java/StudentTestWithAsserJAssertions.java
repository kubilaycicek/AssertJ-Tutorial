import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.filter.InFilter.in;

public class StudentTestWithAsserJAssertions {

    @Test
    void createStudent() {
        final Student student = new Student("Jhon", "Walker", LocalDate.of(1990, Month.JANUARY, 16));

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
                .contains(Arrays.asList("J", "h", "o", "n"))
                .hasSize(4)
                .matches("^J\\w{2}n$")
                .as("Student's name %s", student.getName());

    }

    @Test
    void studentList() {

        final Student student1 = new Student("Jhon", "Doe", LocalDate.of(1990, Month.SEPTEMBER, 5));
        final Student student2 = new Student("Mark", "Walker", LocalDate.of(1969, Month.AUGUST, 12));
        final Student student3 = new Student("Michael", "Jackson", LocalDate.of(2000, Month.JANUARY, 16));
        final Student student4 = new Student("Dominic", "Toretto", LocalDate.of(2005, Month.DECEMBER, 7));
        final Student student5 = new Student("Kubilay", "Cicek", LocalDate.of(1990, Month.MAY, 13));

        final List<Student> students = Arrays.asList(student1, student2, student3, student4);

        Assertions.assertThat(students).as("Student's List")
                .isNotNull()
                .isNotEmpty()
                .hasSize(4)
                .contains(student1, student2, student3)
                .doesNotContain(student5)
                .containsOnly(student2, student4, student1, student3)
                .containsExactly(student1, student2, student3, student4)
                .containsExactlyInAnyOrder(student2, student4, student1, student3);

        Assertions.assertThat(students)
                .filteredOn(student -> student.getBirthDate().until(LocalDate.now(), ChronoUnit.YEARS) >= 25)
                .contains(student1, student2);

        Assertions.assertThat(students).filteredOn(new Condition<Student>() {
            @Override
            public boolean matches(Student student) {
                return student.getBirthDate().until(LocalDate.now(), ChronoUnit.YEARS) >= 25;
            }
        }).hasSize(2)
                .containsOnly(student1, student2);


        Assertions.assertThat(students)
                .filteredOn("birthDate",in(LocalDate.of(1990, Month.SEPTEMBER, 5))).hasSize(1)
                .containsOnly(student1);

        Assertions.assertThat(students)
                .extracting(Student::getName)
                .filteredOn(name->name.contains("a"))
                .hasSize(2)
                .containsOnly("Mark","Michael");

        Assertions.assertThat(students)
                .filteredOn(student -> student.getName().contains("a"))
                .extracting(Student::getName,Student::getSurname)
                .containsOnly(
                        Tuple.tuple("Mark","Walker"),
                        Tuple.tuple("Michael","Jackson")
                );
    }
}
