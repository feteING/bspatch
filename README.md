# bspatch \n
增量更新

使用要求
1.as的项目支持c
2.把cpp拷贝过去 包括bspatch.c bzip2目录
3.cmake 添加 src/main/cpp/bspatch.c
4.修改bspatch.c JNIEXPORT jint JNICALL Java_ndk_com_feetndk_BsPatch_patch  nkd_com_feetndk 变成自己的包名
5.rebuild project

使用流程
1.bsdiffexe文件目录有打差分包工具do_bsdiff.py 把老的apk与新的apk分别放入运行
2.本项目测试差分包放到sdcard根下面 修改在bspatch类中
3.运行
注意.老的apk与你要测试的老的apk md5要一样，不行就adb install -r old.apk

项目
1.先查看有没有差分再全量
2.最好穿过来old.apk与new.apk md5
3.下载差分包，
4.差分生成new.apk 比较生成的与传过来的new.apk的md5是否相同
5.安装




