package com.apakhomov.leetenv.core.parsers;

/**
 * Parses an input string in Leetcode format into java type O.
 */
public interface Parser<O> {
    O parse(String input);
}
