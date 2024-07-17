package com.sandro.nameinverter;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NameInverterTest {

    NameInverter nameInverter = new NameInverter();

    @Test
    void name() throws Exception {
        assertThat(nameInverter.invert(null)).isEqualTo("");
        assertThat(nameInverter.invert("")).isEqualTo("");
        assertThat(nameInverter.invert("name")).isEqualTo("name");
        assertThat(nameInverter.invert("first last")).isEqualTo("last, first");
        assertThat(nameInverter.invert("   name   ")).isEqualTo("name");
        assertThat(nameInverter.invert("first     last")).isEqualTo("last, first");
        assertThat(nameInverter.invert("Mr. first last")).isEqualTo("last, first");
        assertThat(nameInverter.invert("Mrs. first last")).isEqualTo("last, first");
        assertThat(nameInverter.invert("first last SR.")).isEqualTo("last, first SR.");
        assertThat(nameInverter.invert("first last BS. Phd.")).isEqualTo("last, first BS. Phd.");
    }
}
