package com.availity.test;

import com.availity.test.lisp.LispChecker;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LispCheckerTests {
  private final LispChecker lispChecker = new LispChecker();

  @ParameterizedTest
  @ValueSource(strings = {"()", "(stuff x y)", "(stuff x (stuff y z))", "((stuff))", "(asdf(stuff(asdf)))", "\"(asdf(stuff(asdf)))\" \"((stuff))\" \"(stuff  )\""})
  public void validString(String input) {
    assertTrue(lispChecker.check(input));
  }

  @ParameterizedTest
  @ValueSource(strings = {"(", "", "(stuff", "(stuff x stuff y z))", "(stuff(asdf", "(asdf)(asdf)(aasdf(asdf)asdf", "((sadf()(asdf))(asdf)"})
  public void invalidString(String input) {
    assertFalse(lispChecker.check(input));
  }
}