package ndk.com.feetndk;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;

public class BsPatch {
    public native static int patch(String oldfile, String newFile, String patchFile);

    static {
        System.loadLibrary("bspatch-lib");
    }

    public static final String PATCH_FILE = "apk.patch";

    public static final String SD_CARD = Environment.getExternalStorageDirectory() + File.separator;
    //新版本apk的目录
    public static final String NEW_APK_PATH = SD_CARD + "dn_apk_new.apk";

    public static final String PATCH_FILE_PATH = SD_CARD + PATCH_FILE;


    /**
     * 获取已安装Apk文件的源Apk文件
     * 如：/data/app/my.apk
     *
     * @return
     */
    public static String getSourceApkPath(Context context, String packageName) {
        if (TextUtils.isEmpty(packageName))
            return null;

        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(packageName, 0);
            return appInfo.sourceDir;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void installApk(Context context, String apkPath) {
        //apk文件的本地路径
        File apkfile = new File(apkPath);
        //会根据用户的数据类型打开android系统相应的Activity。
        Intent intent = new Intent(Intent.ACTION_VIEW);
        //设置intent的数据类型是应用程序application
        intent.setDataAndType(Uri.parse("file://" + apkfile.toString()), "application/vnd.android.package-archive");
        //为这个新apk开启一个新的activity栈
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //开始安装
        context.startActivity(intent);
        //关闭旧版本的应用程序的进程
        android.os.Process.killProcess(android.os.Process.myPid());
    }


}
