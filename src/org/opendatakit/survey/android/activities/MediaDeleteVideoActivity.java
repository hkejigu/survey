/*
 * Copyright (C) 2012-2013 University of Washington
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package org.opendatakit.survey.android.activities;

import org.opendatakit.common.android.provider.FileProvider;
import org.opendatakit.common.android.utilities.MediaUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Simple wrapper for delete-file functionality.
 *
 * Called from javascript with: shim.doAction(promptPath, internalPromptContext,
 * "org.opendatakit.survey.android.activities.MediaDeleteVideoActivity",
 * JSON.stringify({ mediaPath: uriFromDatabase }));
 *
 * @author mitchellsundt@gmail.com
 *
 */
public class MediaDeleteVideoActivity extends Activity {
  private static final String t = "MediaDeleteVideoActivity";
  private static final String MEDIA_PATH = "mediaPath";

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    String binaryName = savedInstanceState.getString(MEDIA_PATH);
    int del = MediaUtils.deleteVideoFileFromMediaProvider(this, FileProvider.getAsFile(this,binaryName)
        .getAbsolutePath());
    Log.i(t, "Deleted " + del + " matching entries for " + MEDIA_PATH + ": " + binaryName);

    Intent i = new Intent();

    i.putExtra(MEDIA_PATH, binaryName);
    i.putExtra("deleteCount", del);
    setResult(Activity.RESULT_OK, i);
    finish();
  }
}
