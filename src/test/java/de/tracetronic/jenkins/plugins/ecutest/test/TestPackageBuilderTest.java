/**
 * Copyright (c) 2015-2016 TraceTronic GmbH
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 *   1. Redistributions of source code must retain the above copyright notice, this
 *      list of conditions and the following disclaimer.
 *
 *   2. Redistributions in binary form must reproduce the above copyright notice, this
 *      list of conditions and the following disclaimer in the documentation and/or
 *      other materials provided with the distribution.
 *
 *   3. Neither the name of TraceTronic GmbH nor the names of its
 *      contributors may be used to endorse or promote products derived from
 *      this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package de.tracetronic.jenkins.plugins.ecutest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import de.tracetronic.jenkins.plugins.ecutest.test.config.ExecutionConfig;
import de.tracetronic.jenkins.plugins.ecutest.test.config.PackageConfig;
import de.tracetronic.jenkins.plugins.ecutest.test.config.TestConfig;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Unit tests for {@link TestPackageBuilder}.
 *
 * @author Christian Pönisch <christian.poenisch@tracetronic.de>
 */
public class TestPackageBuilderTest {

    @Test
    public void testDefaultStep() throws IOException {
        final TestConfig testConfig = new TestConfig("", "");
        final PackageConfig packageConfig = new PackageConfig(true, true);
        final ExecutionConfig executionConfig = new ExecutionConfig("", true, true);
        final TestPackageBuilder builder = new TestPackageBuilder("");
        builder.setTestConfig(testConfig);
        builder.setPackageConfig(packageConfig);
        builder.setExecutionConfig(executionConfig);
        assertBuilder(builder);
    }

    @SuppressFBWarnings("NP_NONNULL_PARAM_VIOLATION")
    @Test
    public void testNullStep() {
        final TestPackageBuilder builder = new TestPackageBuilder(null);
        builder.setTestConfig(null);
        builder.setPackageConfig(null);
        builder.setExecutionConfig(null);
        assertBuilder(builder);
    }

    @Deprecated
    @Test
    public void testDefault() {
        final TestConfig testConfig = new TestConfig("", "");
        final PackageConfig packageConfig = new PackageConfig(true, true);
        final ExecutionConfig executionConfig = new ExecutionConfig("", true, true);
        final TestPackageBuilder builder = new TestPackageBuilder("", testConfig, packageConfig, executionConfig);
        assertBuilder(builder);
    }

    @Deprecated
    @Test
    public void testNull() {
        final TestConfig testConfig = new TestConfig(null, null, false, false, null);
        final PackageConfig packageConfig = new PackageConfig(true, true, null);
        final ExecutionConfig executionConfig = new ExecutionConfig(null, true, true);
        final TestPackageBuilder builder = new TestPackageBuilder(null, testConfig, packageConfig, executionConfig);
        assertBuilder(builder);
    }

    /**
     * Asserts the builder properties.
     *
     * @param builder
     *            the builder
     */
    private void assertBuilder(final TestPackageBuilder builder) {
        assertNotNull(builder);
        assertNotNull(builder.getTestFile());
        assertTrue(builder.getTestFile().isEmpty());
        assertNotNull(builder.getTestConfig().getTbcFile());
        assertTrue(builder.getTestConfig().getTbcFile().isEmpty());
        assertNotNull(builder.getTestConfig().getTcfFile());
        assertTrue(builder.getTestConfig().getTcfFile().isEmpty());
        assertFalse(builder.getTestConfig().isForceReload());
        assertFalse(builder.getTestConfig().isLoadOnly());
        assertTrue(builder.getTestConfig().getConstants().isEmpty());
        assertTrue(builder.getPackageConfig().isRunTest());
        assertTrue(builder.getPackageConfig().isRunTraceAnalysis());
        assertNotNull(builder.getPackageConfig().getParameters());
        assertNotNull(builder.getExecutionConfig().getTimeout());
        assertEquals(ExecutionConfig.getDefaultTimeout(), builder.getExecutionConfig().getTimeout());
        assertTrue(builder.getExecutionConfig().isStopOnError());
        assertTrue(builder.getExecutionConfig().isCheckTestFile());
    }
}
