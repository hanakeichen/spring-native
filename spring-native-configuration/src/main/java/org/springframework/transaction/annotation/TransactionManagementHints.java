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

package org.springframework.transaction.annotation;

import org.springframework.context.annotation.AutoProxyRegistrar;
import org.springframework.nativex.hint.TypeAccess;
import org.springframework.nativex.hint.NativeHint;
import org.springframework.nativex.type.NativeConfiguration;
import org.springframework.nativex.hint.TypeHint;
import org.springframework.transaction.aspectj.AspectJJtaTransactionManagementConfiguration;
import org.springframework.transaction.aspectj.AspectJTransactionManagementConfiguration;

// TODO really the 'skip if missing' can be different for each one here...
@NativeHint(trigger = ProxyTransactionManagementConfiguration.class, types = {
	@TypeHint(types= {
			AutoProxyRegistrar.class,
			ProxyTransactionManagementConfiguration.class
	}, typeNames = "org.springframework.transaction.interceptor.BeanFactoryTransactionAttributeSourceAdvisor$1")
})
@NativeHint(trigger = Transactional.class, types = {
		@TypeHint(types= {
				Transactional.class,
				javax.transaction.Transactional.class
		}),
		@TypeHint(types = Propagation.class, access = { TypeAccess.DECLARED_METHODS, TypeAccess.DECLARED_FIELDS}) // TODO this is an enum - we can probably infer what access an enum requires if exposed
		})
@NativeHint(trigger = AspectJJtaTransactionManagementConfiguration.class,
		types = @TypeHint(types = {
				AspectJJtaTransactionManagementConfiguration.class,
				AspectJTransactionManagementConfiguration.class
}))
public class TransactionManagementHints implements NativeConfiguration {
}
