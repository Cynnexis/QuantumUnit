package fr.berger.qube;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScaleTest {

    Scale scale;

    @BeforeEach
    void setUp() {
        scale = new Scale();
    }

    @Test
    void test_isPowerOfTen() {
        Assertions.assertFalse(Scale.isPowerOfTen(0));

        Assertions.assertTrue(Scale.isPowerOfTen(1));
        Assertions.assertTrue(Scale.isPowerOfTen(10));
        Assertions.assertTrue(Scale.isPowerOfTen(100));
        Assertions.assertTrue(Scale.isPowerOfTen(1000));
        Assertions.assertTrue(Scale.isPowerOfTen(Math.pow(10, 21)));

        Assertions.assertTrue(Scale.isPowerOfTen(0.1));
        Assertions.assertTrue(Scale.isPowerOfTen(0.01));
        Assertions.assertTrue(Scale.isPowerOfTen(0.001));
        Assertions.assertTrue(Scale.isPowerOfTen(0.0001));
        Assertions.assertTrue(Scale.isPowerOfTen(Math.pow(10, -21)));

        Assertions.assertFalse(Scale.isPowerOfTen(-8));
        Assertions.assertFalse(Scale.isPowerOfTen(7));
        Assertions.assertFalse(Scale.isPowerOfTen(625));
        Assertions.assertFalse(Scale.isPowerOfTen(256));
        Assertions.assertFalse(Scale.isPowerOfTen(-821562310));
    }

    @Test
    void test_toString() {
        // See https://en.wikipedia.org/wiki/Metric_prefix
        assertScale("E", Math.pow(10, 21));
        assertScale("E", Math.pow(10, 18));
        assertScale("P", Math.pow(10, 15));
        assertScale("T", Math.pow(10, 12));
        assertScale("G", Math.pow(10, 9));
        assertScale("M", Math.pow(10, 6));
        assertScale("k", Math.pow(10, 3));
        assertScale("h", Math.pow(10, 2));
        assertScale("da", Math.pow(10, 1));
        assertScale("", Math.pow(10, 0));
        assertScale("d", Math.pow(10, -1));
        assertScale("c", Math.pow(10, -2));
        assertScale("m", Math.pow(10, -3));
        assertScale("μ", Math.pow(10, -6));
        assertScale("n", Math.pow(10, -9));
        assertScale("p", Math.pow(10, -12));
        assertScale("f", Math.pow(10, -15));
        assertScale("a", Math.pow(10, -18));
        assertScale("a", Math.pow(10, -21));
    }

    @Disabled
    void assertScale(@NotNull String expectedString, @NotNull Scale scale) {
        double value = scale.getValue();
        String str = scale.toString();
        System.out.println("ScaleTest.test_toString> value: " + value + "; scale: " + str);
        Assertions.assertEquals(expectedString, str);
    }
    @Disabled
    void assertScale(@NotNull String expectedString, double value) {
        assertScale(expectedString, new Scale(value));
    }
}