package com.availity.test;

import com.availity.test.csv.CsvParser;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CsvParserTests {
  private final CsvParser csvParser = new CsvParser();

  @ParameterizedTest
  @MethodSource("args")
  public void test2(int[] expectedLines, String path) {
    var csv = new File(path);
    List<File> files = csvParser.parse(csv);
    var count = 0;
    for(File file : files) {
      try(var reader = new BufferedReader(new FileReader(file))) {
        assertEquals(expectedLines[count], reader.lines().toList().size());
        count++;
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private static Stream<Arguments> args() {
    return Stream.of(
        Arguments.of(new int[] {3, 2}, "src/test/resources/availity-test1.csv"),
        Arguments.of(new int[] {3, 3}, "src/test/resources/availity-test2.csv")
    );
  }
}