/*
 * Copyright 2014 Shazam Entertainment Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License.
 *
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.shazam.fork.summary;

import com.shazam.fork.model.Device;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class TestResult {
	private final Device device;
	private final float timeTaken;
	private final String testClass;
	private final String testMethod;
	private final String errorTrace;
	private final String failureTrace;

	private final String poolName;

	public Device getDevice() {
		return device;
	}

	public String getPoolName() {
		return poolName;
	}

	public float getTimeTaken() {
		return timeTaken;
	}

	public String getTestClass() {
		return testClass;
	}

	public String getTestMethod() {
		return testMethod;
	}

	public ResultStatus getResultStatus() {
		if (isNotBlank(errorTrace)) {
			return ResultStatus.ERROR;
		}
        if (isNotBlank(failureTrace)) {
			return ResultStatus.FAIL;
		}
        return ResultStatus.PASS;
	}

	public String getTrace() {
		switch(getResultStatus()) {
		case ERROR:
			return errorTrace;
		case FAIL:
			return failureTrace;
		default:
			return "";
		}
	}

	@Override
	public String toString() {
		return new StringBuilder()
			.append("TestResult [device=").append(device)
			.append(", poolName=").append(poolName)
			.append(", testClass=").append(testClass)
			.append(", testMethod=").append(testMethod)
			.append(", timeTaken=").append(timeTaken)
			.append(", errorTrace=").append(errorTrace)
			.append(", failureTrace=").append(failureTrace)
			.append("]")
			.toString();
	}

	public enum ResultStatus {
		PASS, FAIL, ERROR
	}

	public static class Builder {
		private Device device;
		private float timeTaken;
		private String testClass;
		private String testMethod;
		private String errorTrace;
		private String failureTrace;
		private String poolName;

		public static Builder aTestResult() {
			return new Builder();
		}

		public Builder withDevice(Device device) {
			this.device = device;
			return this;
		}

		public Builder withTimeTaken(float timeTaken) {
			this.timeTaken = timeTaken;
			return this;
		}

		public Builder withTestClass(String testClass) {
			this.testClass = testClass;
			return this;
		}

		public Builder withTestMethod(String testMethod) {
			this.testMethod = testMethod;
			return this;
		}

		public Builder withErrorTrace(String trace) {
			if (isNotBlank(trace)) {
				errorTrace = trace;
			}
			return this;
		}

		public Builder withFailureTrace(String trace) {
			if (isNotBlank(trace)) {
				failureTrace = trace;
			}
			return this;
		}

		public Builder withPoolName(String poolName) {
			this.poolName = poolName;
			return this;
		}

		public TestResult build() {
			return new TestResult(this);
		}
	}

	private TestResult(Builder builder) {
		device = builder.device;
		timeTaken = builder.timeTaken;
		testClass = builder.testClass;
		testMethod = builder.testMethod;
		errorTrace = builder.errorTrace;
		failureTrace = builder.failureTrace;
		poolName = builder.poolName;
	}
}
