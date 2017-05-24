/*
 * Copyright (C) 2017 The Android Open Source Project
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

package com.android.settings.fuelgauge.anomaly;

import static com.google.common.truth.Truth.assertThat;

import com.android.settings.SettingsRobolectricTestRunner;
import com.android.settings.TestConfig;
import com.android.settings.fuelgauge.anomaly.action.BackgroundCheckAction;
import com.android.settings.fuelgauge.anomaly.action.ForceStopAction;
import com.android.settings.fuelgauge.anomaly.checker.WakeLockAnomalyDetector;
import com.android.settings.testutils.shadow.ShadowKeyValueListParserWrapperImpl;
import com.android.settings.fuelgauge.anomaly.checker.WakeupAlarmAnomalyDetector;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

@RunWith(SettingsRobolectricTestRunner.class)
@Config(manifest = TestConfig.MANIFEST_PATH, sdk = TestConfig.SDK_VERSION, shadows = {
        ShadowKeyValueListParserWrapperImpl.class})
public class AnomalyUtilsTest {
    private AnomalyUtils mAnomalyUtils;

    @Before
    public void setUp() {
        mAnomalyUtils = new AnomalyUtils(RuntimeEnvironment.application);
    }

    @Test
    public void testGetAnomalyAction_typeWakeLock_returnForceStop() {
        assertThat(mAnomalyUtils.getAnomalyAction(Anomaly.AnomalyType.WAKE_LOCK)).isInstanceOf(
                ForceStopAction.class);
    }

    @Test
    public void testGetAnomalyAction_typeWakeUpAlarm_returnBackgroundCheck() {
        assertThat(mAnomalyUtils.getAnomalyAction(Anomaly.AnomalyType.WAKEUP_ALARM)).isInstanceOf(
                BackgroundCheckAction.class);
    }

    @Test
    public void testGetAnomalyDetector_typeWakeLock_returnWakeLockDetector() {
        assertThat(mAnomalyUtils.getAnomalyDetector(Anomaly.AnomalyType.WAKE_LOCK)).isInstanceOf(
                WakeLockAnomalyDetector.class);
    }

    @Test
    public void testGetAnomalyDetector_typeWakeUpAlarm_returnWakeUpAlarmDetector() {
        assertThat(mAnomalyUtils.getAnomalyDetector(Anomaly.AnomalyType.WAKEUP_ALARM)).isInstanceOf(
                WakeupAlarmAnomalyDetector.class);
    }
}
