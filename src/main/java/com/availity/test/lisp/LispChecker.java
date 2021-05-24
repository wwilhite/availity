package com.availity.test.lisp;

import org.springframework.util.StringUtils;

import java.util.ArrayDeque;

public class LispChecker {

  public boolean check(String s) {
    if(!StringUtils.hasLength(s)) return false;

    var stack = new ArrayDeque<Character>();
    for(char c : s.toCharArray()) {
      if(c == '(') {
        stack.push(c);
      } else if(c == ')') {
        if(stack.peek() != null) {
          stack.pop();
        } else {
          return false;
        }
      }
    }
    return stack.isEmpty();
  }
}
