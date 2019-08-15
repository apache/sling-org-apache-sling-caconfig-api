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
package org.apache.sling.caconfig;

import java.util.Collection;

import org.apache.sling.api.resource.ValueMap;
import org.jetbrains.annotations.NotNull;
import org.osgi.annotation.versioning.ProviderType;

/**
 * Defines how the configuration should be mapped and returned.
 */
@ProviderType
public interface ConfigurationBuilder {

    /**
     * Define configuration name.
     * Optional for the {@link #as(Class)}, {@link #asCollection(Class)} and {@link #has(Class)} methods, mandatory for the others.
     * @param configName Relative path
     * @return Configuration builder
     */
    @NotNull ConfigurationBuilder name(@NotNull String configName);

    /**
     * Get configuration as singleton resource and its properties mapped to the given annotation class.
     * Configuration name is optional - if not given via {@link #name(String)} method it is derived
     * from the annotation interface class name.
     * @param clazz Annotation interface class
     * @param <T> Annotation class type
     * @return Configuration object. Contains only the default values if content resource or configuration cannot be found.
     */
    @NotNull <T> T as(@NotNull Class<T> clazz);

    /**
     * Get collection of configuration resources with their properties mapped to the given annotation class.
     * Configuration name is optional - if not given via {@link #name(String)} method it is derived
     * from the annotation interface class name.
     * @param clazz Annotation interface class
     * @param <T> Annotation class type
     * @return Collection of configuration objects. Is empty if content resource or configuration cannot be found.
     */
    @NotNull <T> Collection<T> asCollection(@NotNull Class<T> clazz);

    /**
     * Get configuration as singleton resource and return its properties as value map.
     * @return Value map. If content resource or configuration cannot be found the map is empty unless default or configuration override values are present for this configuration.
     */
    @NotNull ValueMap asValueMap();

    /**
     * Get collection of configuration resources with their properties mapped to the given annotation class.
     * @return Collection of value maps. Is empty if content resource or configuration cannot be found.
     */
    @NotNull Collection<ValueMap> asValueMapCollection();

    /**
     * Get configuration as singleton configuration resource and adapt it to the given class.
     * @param clazz Class that can be adapted from a {@link org.apache.sling.api.resource.Resource}
     * @param <T> Annotation class type
     * @return Object instance or null if content resource or configuration cannot be found or if the adaption was not possible.
     */
    <T> T asAdaptable(@NotNull Class<T> clazz);

    /**
     * Get collection of configuration resources and adapt them to the given class.
     * @param clazz Class that can be adapted from a {@link org.apache.sling.api.resource.Resource}
     * @param <T> Annotation class type
     * @return Collection of object instances. Is empty if content resource or configuration cannot be found or if the adaption was not possible.
     */
    @NotNull <T> Collection<T> asAdaptableCollection(@NotNull Class<T> clazz);

    /**
     * This method checks for the configuration existence based on {@code configName} defined in the configuration definition. It extracts the {@code configName} 
     * from the given annotation class and checks if the configuration is available for the context path in the current resource hierarchy. If not found, also
     * checks in the global fall-back paths configured via <code>org.apache.sling.caconfig.resource.impl.def.DefaultConfigurationResourceResolvingStrategy</code>
     * which by default are {@code /conf/global}, {@code /apps/config} and {@code /libs/config}.This method does not consider the default values provided in
     * the configuration definition.
     * @param clazz Class that can be adapted from a {@link org.apache.sling.api.resource.Resource}
     * @param <T> Annotation class type
     * @return True/False based on configuration resource node existence.
     */
    <T> boolean has(@NotNull Class<T> clazz);

    /**
     * This method checks for the configuration existence based on provided {@code configName} value.
     * It checks if the configuration is available for the context path in the current resource hierarchy and if not found, also
     * checks in the global fall-back paths configured via <code>org.apache.sling.caconfig.resource.impl.def.DefaultConfigurationResourceResolvingStrategy</code>
     * which by default are {@code /conf/global}, {@code /apps/config} and {@code /libs/config}. This method does not consider the default values provided in
     * the configuration definition.
     * @param configName Name of the configuration
     * @return True/False based on configuration resource node existence.
     */
    boolean has(@NotNull String configName);

}
