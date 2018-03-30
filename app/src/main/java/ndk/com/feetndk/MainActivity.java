package ndk.com.feetndk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.fete.rxjava2asynctask.Rxjava2;
import com.fete.rxjava2asynctask.UITask;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doPatch();
            }
        });

    }

    private void doPatch() {

        // 1、拿到老的APK
        String oldFile = BsPatch.getSourceApkPath(MainActivity.this, getPackageName());
        // 2、拿增量包的路径
        String patchFile = BsPatch.PATCH_FILE_PATH;

        Log.e("====<", "patch路径:" + patchFile);
        // 3、开始执行合并操作
        int ret = BsPatch.patch(oldFile, BsPatch.NEW_APK_PATH, patchFile);
        Log.e("====<", "新apk路径:" + BsPatch.NEW_APK_PATH);

        // 4、合并的成功和失败
        if (ret == 0) {
            Log.e("====<", "合并成功");
            Rxjava2.execute(new UITask() {
                @Override
                public void doInUIThread() {
                    BsPatch.installApk(MainActivity.this, BsPatch.NEW_APK_PATH);
                }
            });
        } else {
            Log.i("====<", "合并失败");
        }
    }



}
