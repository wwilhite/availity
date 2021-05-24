package com.availity.test.csv;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CSV Parser for Availity enrollee CSV files.
 * Assumptions made about CSV file:
 * No header entry.
 * No multi-line entries.
 * Commas are only used to delimit fields.
 */
@Slf4j
public class CsvParser {
  private final Map<String, List<Enrollee>> enrollees = new HashMap<>();

  /**
   * Parses a CSV of enrollees, filters them by insurance company and outputs them as separate CSV files.
   * @param csv the file of enrollees.
   * @return The list of output files.
   */
  public List<File> parse(File csv) {
    try (var reader = new BufferedReader(new FileReader(csv))) {
      for(String line : reader.lines().toList()) {
        this.filterParseLine(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    var outputs = new ArrayList<File>();
    enrollees.keySet().forEach(i -> outputs.add(createOutputFile(enrollees.get(i).stream().filter(e -> e.getInsurance().equals(i)).sorted().toList(), i)));
    return outputs;
  }

  /**
   * Checks for duplicate entries before adding new CsvEntry.
   * @param line
   */
  private void filterParseLine(String line) {
    var entry = parseLine(line);
    var currentList = enrollees.get(entry.getInsurance());
    if(currentList.isEmpty()) {
      currentList.add(entry);
    } else {
      if(currentList.contains(entry)) {
        if(currentList.removeIf(e -> entry.equals(e) && e.getVersion() < entry.getVersion())) {
          currentList.add(entry);
        }
      } else {
        currentList.add(entry);
      }
    }
  }

  private Enrollee parseLine(String line) {
    String[] splitLine = line.split(",");
    var csvEntry = new Enrollee.EnrolleeBuilder()
        .id(removeQuotes(splitLine[0]))
        .firstName(removeQuotes(splitLine[1]))
        .lastName(removeQuotes(splitLine[2]))
        .version(Integer.parseInt(splitLine[3].replaceAll("\"", "")))
        .insurance(removeQuotes(splitLine[4]));
    if(!enrollees.containsKey(splitLine[4])) {
      enrollees.put(removeQuotes(splitLine[4]), new ArrayList<>());
    }
    return csvEntry.build();
  }

  private String removeQuotes(String value) {
    if(value.charAt(0) == '"' && value.charAt(value.length() - 1) == '"') {
      return value.substring(1, value.length() - 1);
    }
    return value;
  }

  private File createOutputFile(List<Enrollee> entries, String insurance) {
    var file = new File(insurance + ".csv");
    try (var writer = new FileWriter(file)) {
      for(Enrollee entry : entries) {
        writer.write(entry.toString() + "\n");
      }
    } catch (IOException e) {
      log.error(e.getLocalizedMessage(), e);
    }

    return file;
  }
}
