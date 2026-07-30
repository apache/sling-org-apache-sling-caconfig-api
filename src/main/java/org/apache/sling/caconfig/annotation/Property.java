/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.sling.caconfig.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Adds further metadata for properties of context-aware configuration annotation classes.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Property {

    /**
     * @return Label for the property (e.g. for configuration editor GUIs).
     */
    String label() default "";

    /**
     * @return Description for the property (e.g. for configuration editor GUIs).
     */
    String description() default "";

    /**
     * @return Further properties e.g. for configuration editor GUIs.
     */
    String[] property() default {};

    /**
     * @return Number to control property order in configuration editor.
     */
    int order() default 0;

    /**
     * Indicates whether the property value is encrypted in the underlying repository storage and should be automatically
     * decrypted during reading.
     * It also acts as a hint for configuration editor GUIs to write the property value in encrypted form.
     * @return {@code true} if the property value is encrypted, {@code false} otherwise.
     * @since 1.2.0 (Bundle version 1.4.0)
     */
    boolean encrypted() default false;
}
