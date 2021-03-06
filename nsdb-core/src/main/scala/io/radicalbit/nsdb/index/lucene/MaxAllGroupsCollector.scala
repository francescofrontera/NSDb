/*
 * Copyright 2018 Radicalbit S.r.l.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.radicalbit.nsdb.index.lucene

import scala.reflect.ClassTag

class MaxAllGroupsCollector[T: Numeric, S: Ordering: ClassTag](override val groupField: String,
                                                               override val aggField: String)
    extends AllGroupsAggregationCollector[T, S] {

  override def accumulateFunction(prev: T, actual: T): Option[T] =
    if (implicitly[Numeric[T]].lt(prev, actual)) Some(actual) else Some(prev)

}
