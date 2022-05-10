package net.natroutter.hubcore.utilities;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.natroutter.hubcore.Handler;
import net.natroutter.natlibs.utilities.StringHandler;
import org.bukkit.entity.Player;

public class ServerSwitcher {

    private Handler handler;
    private boolean useCommand = false;
    private String command = "";

    public ServerSwitcher(Handler handler) {
        this.handler = handler;
    }

    public void setUseCommand(boolean useCommand) {
        this.useCommand = useCommand;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void switchServer(Player p, String server) {
        if (!useCommand) {
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("ConnectOther");
            out.writeUTF(p.getName());
            out.writeUTF(server);
            p.sendPluginMessage(handler.getInstance(), "BungeeCord", out.toByteArray());
        } else {
            StringHandler str = new StringHandler(command);
            str.replaceAll("%server%", server);
            p.performCommand(str.build());
        }
    }

}
