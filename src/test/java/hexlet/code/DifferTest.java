package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;

import static hexlet.code.Differ.getNormalizedPath;
import static org.assertj.core.api.Assertions.assertThat;

public class DifferTest {
    private static final String PATH = "src/test/java/resources/fixtures/";

    private static String readFile(String fileName) throws IOException {
        return Files.readString(getNormalizedPath(PATH + fileName));
    }
    @Test
    void generateSimpleJsonStylishFormat() throws IOException {
        String fileJson1 = PATH + "1.json";
        String fileJson2 = PATH + "2.json";
        String actualContentSimpleJsonStylish = Differ.generate(fileJson1, fileJson2, "stylish");
        String expectedContentSimpleJsonStylish = readFile("expected_simple_stylish.txt");
        assertThat(actualContentSimpleJsonStylish).isEqualTo(expectedContentSimpleJsonStylish);
    }

    @Test
    void generateSimpleYamlStylishFormat() throws Exception {
        String fileYaml1 = PATH + "1.yaml";
        String fileYaml2 = PATH + "2.yaml";
        String actualContentSimpleYamlStylish = Differ.generate(fileYaml1, fileYaml2, "stylish");
        String expectedContentSimpleYamlStylish = readFile("expected_simple_stylish.txt");
        assertThat(actualContentSimpleYamlStylish).isEqualTo(expectedContentSimpleYamlStylish);
    }

    @Test
    void generateNestedJsonStylishFormat() throws Exception {
        String fileYaml1 = PATH + "nested_json1.json";
        String fileYaml2 = PATH + "nested_json2.json";
        String actualContentNestedJsonStylish = Differ.generate(fileYaml1, fileYaml2, "stylish");
        String expectedContentNestedJsonStylish = readFile("expected_nested_stylish.txt");
        assertThat(actualContentNestedJsonStylish).isEqualTo(expectedContentNestedJsonStylish);
    }

    @Test
    void generateNestedYamlStylishFormat() throws Exception {
        String fileYaml1 = PATH + "nested_yaml1.yaml";
        String fileYaml2 = PATH + "nested_yaml2.yaml";
        String actualContentNestedYamlStylish = Differ.generate(fileYaml1, fileYaml2, "stylish");
        String expectedContentNestedYamlStylish = readFile("expected_nested_stylish.txt");
        assertThat(actualContentNestedYamlStylish).isEqualTo(expectedContentNestedYamlStylish);
    }

    @Test
    void generateNestedYamlPlainFormat() throws Exception {
        String fileYaml1 = PATH + "nested_yaml1.yaml";
        String fileYaml2 = PATH + "nested_yaml2.yaml";
        String actualContentNestedYamlPlain = Differ.generate(fileYaml1, fileYaml2, "plain");
        String expectedContentNestedYamlPlain = readFile("expected_simple_plain.txt");
        assertThat(actualContentNestedYamlPlain).isEqualTo(expectedContentNestedYamlPlain);
    }

    @Test
    void generateSimpleJsonJsonFormat() throws Exception {
        String fileJson1 = PATH + "1.json";
        String fileJson2 = PATH + "2.json";
        String actualContentSimpleJsonJson = Differ.generate(fileJson1, fileJson2, "json");
        String expectedContentSimpleJsonJson = readFile("expected_simple_json.json");
        assertThat(actualContentSimpleJsonJson).isEqualTo(expectedContentSimpleJsonJson);
    }

    @Test
    void generateNestedYamlJsonFormat() throws Exception {
        String fileJson1 = PATH + "nested_yaml1.yaml";
        String fileJson2 = PATH + "nested_yaml2.yaml";
        String actualContentSimpleJsonJson = Differ.generate(fileJson1, fileJson2, "json");
        String expectedContentSimpleJsonJson = readFile("expected_nested_yaml.json");
        assertThat(actualContentSimpleJsonJson).isEqualTo(expectedContentSimpleJsonJson);
    }

    @Test
    void generateSimpleJsonDefaultFormat() throws Exception {
        String fileJson1 = PATH + "1.json";
        String fileJson2 = PATH + "2.json";
        String actualContentSimpleJsonStylish = Differ.generate(fileJson1, fileJson2);
        String expectedContentSimpleJsonStylish = readFile("expected_simple_stylish.txt");
        assertThat(actualContentSimpleJsonStylish).isEqualTo(expectedContentSimpleJsonStylish);
    }
}
