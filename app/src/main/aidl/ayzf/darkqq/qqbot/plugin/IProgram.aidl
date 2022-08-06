package ayzf.darkqq.qqbot.plugin;

import ayzf.darkqq.qqbot.msg.Messenger;

interface IProgram {
   Messenger send(in String id,in String service,in Messenger msg);//发送消息

   void printE(in String id,in String service,in String error);//打印错误

   void printI(in String id,in String service,in String info);//打印信息

   void printW(in String id,in String service,in String warn);//打印警告

   String newCacheFile(in String id,in String service,in byte[] data);//在主程序新建缓存文件
}
