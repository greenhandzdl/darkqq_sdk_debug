package ayzf.darkqq.qqbot.plugin;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.RemoteException;
import ayzf.darkqq.qqbot.msg.Messenger;

/**
 * @Author MCSQNXA
 * @CreateTime 2022-06-19 12:11:01
 * @Description 实列
 */
public class PluginBinder extends Service implements ServiceConnection {
    /**
     * @CreateTime 2022-06-18 08:58:06
     * @Description 发送消息
     */
    public Messenger send(Messenger msg) {
        if (this.program != null && msg != null && msg.getListSize() > 0) {
            try {
                return this.program.send(this.plugin_id, this.plugin_service, msg);
            } catch (Exception e) {

            }
        }

        return new Messenger();
    }

    /**
     * @CreateTime 2022-06-18 09:01:21
     * @Description 发送消息
     */
    public Messenger send(Messenger.Builder builder) {
        Messenger msg = new Messenger();
        builder.build(msg);

        return this.send(msg);
    }

    /**
     * @CreateTime 2022-06-25 09:33:48
     * @Description 打印错误
     */
    public void printE(String error) {
        if (this.program != null) {
            try {
                this.program.printE(this.plugin_id, this.plugin_service, error);
            } catch (Exception e) {

            }
        }
    }

    /**
     * @CreateTime 2022-06-25 09:35:14
     * @Description 打印信息
     */
    public void printI(String info) {
        if (this.program != null) {
            try {
                this.program.printI(this.plugin_id, this.plugin_service, info);
            } catch (Exception e) {

            }
        }
    }

    /**
     * @CreateTime 2022-06-25 09:35:47
     * @Description 打印警告
     */
    public void printW(String warn) {
        if (this.program != null) {
            try {
                this.program.printW(this.plugin_id, this.plugin_service, warn);
            } catch (Exception e) {

            }
        }
    }

    /**
     * @CreateTime 2022-07-19 11:06:24
     * @Description 在主程序端新建缓存文件并且返回文件路径
     */
    public String newCacheFile(byte[] data) {
        if (this.program != null && data != null) {
            try {
                return this.program.newCacheFile(this.plugin_id, this.plugin_service, data);
            } catch (Exception e) {

            }
        }

        return "";
    }

    private String program_id;//主程序包名
    private String program_service;//主程序服务
    private String plugin_id;//插件包名
    private String plugin_service;//插件服务
    private IProgram program;//AIDL上下文
    private PluginBinderHandler handler;//接口

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        this.program = IProgram.Stub.asInterface(service);//插件消息接收服务启动成功
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        try {
            this.handler.onUnLoad();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.exit(0);
    }

    /**
     * @CreateTime 2022-06-19 12:23:50
     * @Description 创建绑定
     */
    public IBinder newBinder(Intent i, PluginBinderHandler handler) {
        if (i == null || (this.handler = handler) == null) {
            throw new NullPointerException();
        }

        this.program_id = i.getStringExtra("program_id");
        this.program_service = i.getStringExtra("program_service");
        this.plugin_id = i.getStringExtra("plugin_id");
        this.plugin_service = i.getStringExtra("plugin_service");

        Intent intent = new Intent();
        intent.setAction("mcsq.ayzf.qqbot.plugin");//这一行和下面一行禁止修改
        intent.setClassName(this.program_id, this.program_service);

        super.bindService(intent, this, Context.BIND_AUTO_CREATE);

        return new IPlugin.Stub() {
            @Override
            public void onLoad() throws RemoteException {
                handler.onLoad();
            }

            @Override
            public void onMsgHandler(Messenger msg) throws RemoteException {
                handler.onMsgHandler(msg);
            }

            @Override
            public Bitmap icon() throws RemoteException {
                return handler.icon();
            }

            @Override
            public String name() throws RemoteException {
                return handler.name();
            }

            @Override
            public String info() throws RemoteException {
                return handler.info();
            }

            @Override
            public String author() throws RemoteException {
                return handler.author();
            }

            @Override
            public String version() throws RemoteException {
                return handler.version();
            }

            @Override
            public String activity() throws RemoteException {
                return handler.activity();
            }
        };
    }

    /**
     * @CreateTime 2022-06-19 12:24:58
     * @Description 空载方法
     */
    @Override
    public IBinder onBind(Intent i) {
        return null;
    }

    /**
     * @CreateTime 2022-06-24 20:50:18
     * @Description 主程序可用
     */
    public boolean isAvailable() {
        return this.program != null && this.program.asBinder().pingBinder();
    }

    /**
     * @CreateTime 2022-08-05 09:53:06
     * @Description 对象
     */
    private Object tag;

    /**
     * @CreateTime 2022-08-05 09:53:23
     * @Description Set
     */
    public void setTag(Object tag) {
        this.tag = tag;
    }

    /**
     * @CreateTime 2022-08-05 09:53:57
     * @Description Get
     */
    public Object getTag() {
        return this.tag;
    }


}
