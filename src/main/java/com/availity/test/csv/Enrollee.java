package com.availity.test.csv;

import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Data
@Builder
public class Enrollee implements Comparable<Enrollee> {
  private String id;
  private String firstName;
  private String lastName;
  private int version;
  private String insurance;

  @Override
  public String toString() {
    return "\"" + id + "\",\"" + firstName + "\",\"" + lastName + "\",\"" + version + "\",\"" + insurance + "\"";
  }

  @Override
  public boolean equals(Object obj) {
    if(obj == null) return false;
    if(obj instanceof Enrollee entry) {
      return id.equals(entry.getId()) && insurance.equals(entry.getInsurance());
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, insurance);
  }

  @Override
  public int compareTo(final Enrollee o) {
    var lastNameComparison = lastName.compareTo(o.getLastName());
    if(lastNameComparison == 0) {
      return firstName.compareTo(o.getFirstName());
    } else {
      return lastNameComparison;
    }
  }
}