/*
 * Copyright 2019-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.nativex.hint;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * The various types of access that can be requested. The values of these match
 * the names that go into the json configuration for native-image.
 *
 * @see <a href="https://www.graalvm.org/reference-manual/native-image/Reflection/#manual-configuration">Manual configuration of reflection use in native images</a>
 * @author Andy Clement
 * @author Sebastien Deleuze
 */
public enum TypeAccess {

	/**
	 * Inferred with:
	 * <ul>
	 *     <li>{@link TypeAccess#PUBLIC_METHODS} for annotations and interfaces</li>
	 *     <li>None for arrays (class access)</li>
	 *     <li>{@link TypeAccess#DECLARED_CONSTRUCTORS} for other types</li>
	 * </ul>
	 */
	AUTO_DETECT("autoDetect"),

	/**
	 * Configure related JNI reflection entry, to be combined with other `Flag` entries.
	 */
	JNI("jni"),

	/**
	 * Configure access to the *.class file resource.
	 */
	RESOURCE("resource"),

	/**
	 * Public fields access.
	 * @see Class#getFields()
	 */
	PUBLIC_FIELDS("allPublicFields"),

	/**
	 * Declared fields access: public, protected, default (package) access, and private, but excluding inherited ones.
	 * @see Class#getDeclaredFields()
	 */
	DECLARED_FIELDS("allDeclaredFields"),

	/**
	 * Declared constructors access: public, protected, default (package) access, and private ones.
	 * @see Class#getDeclaredConstructors()
	 */
	DECLARED_CONSTRUCTORS("allDeclaredConstructors"),

	/**
	 * Public constructors.
	 * @see Class#getConstructors()
	 */
	PUBLIC_CONSTRUCTORS("allPublicConstructors"),

	/**
	 * Declared methods access: public, protected, default (package) access, and private, but excluding inherited ones.
	 * Consider whether you need this or @link {@link #PUBLIC_METHODS}.
	 * @see Class#getDeclaredMethods()
	 */
	DECLARED_METHODS("allDeclaredMethods"),

	/**
	 * Public methods access: public methods of the class including inherited ones.
	 * Consider whether you need this or @link {@link #DECLARED_METHODS}.
	 * @see Class#getMethods()
	 */
	PUBLIC_METHODS("allPublicMethods"),


	/**
	 * Do not automatically register the inner classes for reflective access but make them available via {@link Class#getDeclaredClasses}.
	 */
	DECLARED_CLASSES("allDeclaredClasses"),

	/**
	 * Do not automatically register the inner classes for reflective access but make them available via {@link Class#getClasses}.
 	 */
	PUBLIC_CLASSES("allPublicClasses"),

	/**
	 * Declared method's metadata query: public, protected, default (package) access, and private, but excluding inherited ones.
	 * Consider whether you need this or @link {@link #QUERY_PUBLIC_METHODS}.
	 * @see Class#getDeclaredMethods()
	 */
	QUERY_DECLARED_METHODS("queryAllDeclaredMethods"),

	/**
	 * Public method's metadata query access: public methods of the class including inherited ones.
	 * Consider whether you need this or @link {@link #QUERY_DECLARED_METHODS}.
	 * @see Class#getMethods()
	 */
	QUERY_PUBLIC_METHODS("queryAllPublicMethods"),

	/**
	 * Declared constructor's metadata query: public, protected, default (package) access, and private ones.
	 * @see Class#getDeclaredConstructors()
	 */
	QUERY_DECLARED_CONSTRUCTORS("queryAllDeclaredConstructors"),

	/**
	 * Queried public constructors.
	 * @see Class#getConstructors()
	 */
	QUERY_PUBLIC_CONSTRUCTORS("queryAllPublicConstructors");

	private final String value;

	TypeAccess(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}

	public static String toString(TypeAccess[] access) {
		List<TypeAccess> asList = Arrays.asList(access);
		Collections.sort(asList);
		return asList.toString();
	}
}