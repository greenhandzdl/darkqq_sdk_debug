package ayzf.darkqq.qqbot.plugin;

import android.graphics.Bitmap;
import android.os.RemoteException;
import ayzf.darkqq.qqbot.msg.Messenger;

/**
 * @Author MCSQNXA
 * @CreateTime 2022-06-25 09:04:06
 * @Description 插件回调
 */
public interface PluginBinderHandler {
    void onLoad() throws RemoteException;

    void onUnLoad() throws Exception;

    void onMsgHandler(Messenger messenger) throws RemoteException;

    Bitmap icon() throws RemoteException;

    String name() throws RemoteException;

    String info() throws RemoteException;

    String author() throws RemoteException;

    String version() throws RemoteException;

    String activity() throws RemoteException;


}
