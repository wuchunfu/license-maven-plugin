/**
 * Copyright (C) 2008 Mycila (mathieu.carbou@gmail.com)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mycila.maven.plugin.license;

import java.io.File;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.testing.stubs.MavenProjectStub;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */
class IncludesMojoTest {

  @Test
  void test_include() throws Exception {
    LicenseCheckMojo check = new LicenseCheckMojo();
    check.defaultBasedir = new File("src/test/resources/check");
    check.legacyConfigHeader = "header.txt";
    check.project = new MavenProjectStub();
    check.legacyConfigIncludes = new String[]{"inexisting"};
    check.strictCheck = true;
    check.execute();
  }

  @Test
  void test_include_and_fail() throws Exception {
    LicenseCheckMojo check = new LicenseCheckMojo();
    check.defaultBasedir = new File("src/test/resources/check");
    check.legacyConfigHeader = "header.txt";
    check.project = new MavenProjectStub();
    check.legacyConfigIncludes = new String[]{"doc1.txt"};
    check.strictCheck = true;
    Assertions.assertThrows(MojoExecutionException.class, () -> {
      check.execute();
    });
  }

  @Test
  void test_include_overrides_default_exclusion() throws Exception {
    LicenseCheckMojo check = new LicenseCheckMojo();
    check.defaultBasedir = new File("src/test/resources/issues/issue-71");
    check.legacyConfigHeader = "../../check/header.txt";
    check.project = new MavenProjectStub();
    check.legacyConfigIncludes = new String[]{"**/.travis.yml"};
    Assertions.assertThrows(MojoExecutionException.class, () -> {
      check.execute();
    });
  }

}
