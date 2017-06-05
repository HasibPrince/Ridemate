package com.audacity.ridemate.Utils;

import android.content.Context;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Prince on 6/5/17.
 */

public class Utils {
    public static boolean isFromLocalStorage(String filePath) { ///storage/emulated/0/FcDrawing1454406644872.jpg

        if (filePath == null)
            return false;

        return filePath.contains("storage");
    }

    public static void CopyStream(InputStream inputStream, OutputStream outputStream) {
        final int bufferSize = 1024;

        try {
            byte[] bytes = new byte[bufferSize];
            for (; ; ) {
                //Read byte from input stream
                int count = inputStream.read(bytes, 0, bufferSize);
                if (count == -1)
                    break;

                //Write byte from output stream
                outputStream.write(bytes, 0, count);
            }
        } catch (Exception ex) {
        }
    }

    public static int getScreenWidthInPixels(Context context) {
        int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        return screenWidth;
    }
}
