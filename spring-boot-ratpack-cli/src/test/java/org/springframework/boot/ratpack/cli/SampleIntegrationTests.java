/*
 * Copyright 2014-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.boot.ratpack.cli;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;

/**
 * @author Dave Syer
 *
 */
public class SampleIntegrationTests {

	@Rule
	public CliTester cli = new CliTester("samples/");

	@Test
	public void appSample() throws Exception {
		this.cli.run("app.groovy");
		String output = this.cli.getHttpOutput();
		assertTrue("Wrong output: " + output,
				output.contains("\"msg\":\"Hello World\""));
	}

	@Test
	public void actuatorSample() throws Exception {
		this.cli.run("actuator.groovy");
		String output = this.cli.getHttpOutput("/metrics");
		assertTrue("Wrong output: " + output, output.contains("\"mem.free\""));
	}

	@Test
	public void bindingsSample() throws Exception {
		this.cli.run("bindings.groovy");
		String output = this.cli.getHttpOutput();
		assertTrue("Wrong output: " + output, output.contains("\"message\""));
		assertTrue("Wrong output: " + output, output.contains("\"Hello World\"}"));
		assertFalse("Wrong output: " + output, output.contains("\n")); // no pretty print
	}

}
