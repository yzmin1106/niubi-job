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

package com.zuoxiaolong.niubi.job.core.container;

import com.zuoxiaolong.niubi.job.core.config.Context;
import com.zuoxiaolong.niubi.job.core.config.DefaultContext;
import com.zuoxiaolong.niubi.job.core.scanner.JobScanner;
import com.zuoxiaolong.niubi.job.core.scanner.LocalJobScanner;
import com.zuoxiaolong.niubi.job.core.scanner.MethodTriggerDescriptor;
import com.zuoxiaolong.niubi.job.core.scanner.RemoteJobScanner;
import com.zuoxiaolong.niubi.job.core.schedule.DefaultScheduleManager;
import com.zuoxiaolong.niubi.job.core.schedule.ScheduleManager;

import java.util.List;

/**
 * 默认的容器实现类
 *
 * @author Xiaolong Zuo
 * @since 16/1/9 01:18
 */
public class DefaultContainer implements Container {

    private JobScanner jobScanner;

    private Context context;

    private ScheduleManager scheduleManager;

    public DefaultContainer() {
        this.context = new DefaultContext();
        this.jobScanner = new LocalJobScanner(context);
        createScheduleManager();
    }

    public DefaultContainer(String jarUrl) {
        this.context = new DefaultContext();
        this.jobScanner = new RemoteJobScanner(context, jarUrl);
        createScheduleManager();
    }

    private void createScheduleManager() {
        scheduleManager = new DefaultScheduleManager(context);
        List<MethodTriggerDescriptor> descriptorList = jobScanner.scan();
        for (MethodTriggerDescriptor descriptor : descriptorList) {
            scheduleManager.addJob(descriptor);
        }
        scheduleManager.bindContext(context);
    }

    public Context getContext() {
        return context;
    }

    public ScheduleManager getScheduleManager() {
        return scheduleManager;
    }

}
