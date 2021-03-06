package org.mockserver.collections.multimap.nottablematched;

import org.junit.Test;
import org.mockserver.collections.CaseInsensitiveRegexMultiMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockserver.collections.CaseInsensitiveRegexMultiMap.multiMap;
import static org.mockserver.model.NottableString.not;

/**
 * @author jamesdbloom
 */
public class CaseInsensitiveRegexMultiMapTestNottableContainsValue {

    @Test
    public void singleValuedMapShouldContainValueForSingleValue() {
        // given
        CaseInsensitiveRegexMultiMap multiMap = multiMap(
            true, new String[]{"keyOne", "keyOne_valueOne"}
        );

        // then
        assertThat(multiMap.containsValue(not("notKeyOne_valueOne")), is(true));
    }

    @Test
    public void multiValuedMapShouldContainValueForSingleValue() {
        // given
        CaseInsensitiveRegexMultiMap multiMap = multiMap(
            true, new String[]{"keyOne", "keyOne_valueOne"},
                new String[]{"keyTwo", "keyTwo_valueOne", "keyTwo_valueTwo"},
                new String[]{"keyThree", "keyThree_valueOne", "keyThree_valueTwo", "keyThree_valueThree"}
        );

        // then
        assertThat(multiMap.containsValue(not("notKeyOne_valueOne")), is(true));
    }

    @Test
    public void singleValuedMapShouldContainValueForMultipleValues() {
        // given
        CaseInsensitiveRegexMultiMap multiMap = multiMap(
            true, new String[]{"keyTwo", "keyTwo_valueOne", "keyTwo_valueTwo"}
        );

        // then
        assertThat(multiMap.containsValue(not("notKeyTwo_valueOne")), is(true));
        assertThat(multiMap.containsValue(not("notKeyTwo_valueTwo")), is(true));
    }

    @Test
    public void multiValuedMapShouldContainValueForMultipleValues() {
        // given
        CaseInsensitiveRegexMultiMap multiMap = multiMap(
            true, new String[]{"keyOne", "keyOne_valueOne"},
                new String[]{"keyTwo", "keyTwo_valueOne", "keyTwo_valueTwo"},
                new String[]{"keyThree", "keyThree_valueOne", "keyThree_valueTwo", "keyThree_valueThree"}
        );

        // then
        assertThat(multiMap.containsValue(not("notKeyTwo_valueOne")), is(true));
        assertThat(multiMap.containsValue(not("notKeyTwo_valueTwo")), is(true));
    }

    @Test
    public void singleValuedMapShouldNotContainValueForSingleValueWithValueMismatch() {
        // given
        CaseInsensitiveRegexMultiMap multiMap = multiMap(
            true, new String[]{"keyOne", "keyOne_valueOne"}
        );

        // then
        assertThat(multiMap.containsValue(not("keyOne_valueOne")), is(false));
    }

    @Test
    public void singleValuedMapShouldNotContainValueForMultipleValuesWithValueMismatch() {
        // given
        CaseInsensitiveRegexMultiMap multiMap = multiMap(
            true, new String[]{"keyTwo", "keyTwo_valueOne", "keyTwo_valueTwo"}
        );

        // then
        assertThat(multiMap.containsValue(not("key.*")), is(false));
    }

    @Test
    public void multiValuedMapShouldNotContainValueForMultipleValuesWithValueMismatch() {
        // given
        CaseInsensitiveRegexMultiMap multiMap = multiMap(
            true, new String[]{"keyOne", "keyOne_valueOne"},
                new String[]{"keyTwo", "keyTwo_valueOne", "keyTwo_valueTwo"},
                new String[]{"keyThree", "keyThree_valueOne", "keyThree_valueTwo", "keyThree_valueThree"}
        );

        // then
        assertThat(multiMap.containsValue(not("key.*")), is(false));
    }
}
