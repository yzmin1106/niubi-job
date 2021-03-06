/*
 * Copyright 2002-2016 the original author or authors.
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

package com.zuoxiaolong.niubi.job.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 该注解可以用作方法上,代表该方法是一个需要调度的job
 *
 * @author Xiaolong Zuo
 * @since 16/1/9 00:28
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Schedule {

    String DEFAULT_GROUP = "_default_group";

    /**
     * 分组名称
     * @return group
     */
    String group() default DEFAULT_GROUP;

    /**
     * 任务名称
     * @return group
     */
    String name() default "";

    /**
     * 仅当type为CRON时有效
     * @return cron
     */
    String cron();

    /**
     * 丢失的任务策略
     * @return misfirePolicy
     */
    MisfirePolicy misfirePolicy() default MisfirePolicy.None;

}
