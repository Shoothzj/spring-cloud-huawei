/*

  * Copyright (C) 2020-2022 Huawei Technologies Co., Ltd. All rights reserved.

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

package com.huaweicloud.common.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author GuoYl123
 * @Date 2020/2/6
 **/
public class VersionCompareUtilTest {

  @Test
  public void testVersion() {
    Assert.assertTrue(VersionCompareUtil.compareVersion("0.0.1", "0.0.0") > 0);
    Assert.assertEquals(0, VersionCompareUtil.compareVersion("0.0.0", "0.0.0"));
    Assert.assertTrue(VersionCompareUtil.compareVersion("0.0.0", "0.0.1") < 0);
    Assert.assertTrue(VersionCompareUtil.compareVersion("0.0.0", "0.0.0.0") < 0);
    Assert.assertTrue(VersionCompareUtil.compareVersion("0.0.1", "0.0.0.0") > 0);
    Assert.assertTrue(VersionCompareUtil.compareVersion("0.0.1", "0.0.0.0") > 0);
  }
}
