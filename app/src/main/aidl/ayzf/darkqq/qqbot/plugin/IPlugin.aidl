package ayzf.darkqq.qqbot.plugin;

import android.graphics.Bitmap;
import ayzf.darkqq.qqbot.msg.Messenger;


interface IPlugin {
   void onLoad();//加载

   void onMsgHandler(in Messenger msg);//消息

   Bitmap icon();//插件图标

   String name();//插件名称

   String info();//插件简介

   String author();//插件作者

   String version();//插件版本

   String activity();//跳转的活动

}
