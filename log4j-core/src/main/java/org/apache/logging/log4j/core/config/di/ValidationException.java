/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache license, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the license for the specific language governing permissions and
 * limitations under the license.
 */

package org.apache.logging.log4j.core.config.di;

import java.util.Collection;

public class ValidationException extends InjectionException {
    public static ValidationException fromValidationErrors(final Collection<Throwable> validationErrors) {
        final StringBuilder sb = new StringBuilder();
        sb.append("Found ").append(validationErrors.size()).append(" error(s) in bean deployment. Errors:");
        validationErrors.forEach(error -> sb.append("\n • ").append(error.getMessage()));
        final String message = sb.toString();
        final ValidationException exception = new ValidationException(message);
        validationErrors.forEach(exception::addSuppressed);
        return exception;
    }

    private ValidationException(final String message) {
        super(message);
    }
}