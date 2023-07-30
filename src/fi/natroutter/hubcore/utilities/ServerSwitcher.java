package fi.natroutter.hubcore.utilities;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import fi.natroutter.hubcore.HubCore;
import fi.natroutter.natlibs.utilities.Utilities;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.entity.Player;

public class ServerSwitcher {

    private boolean useCommand = false;
    private String command = "";

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
            p.sendPluginMessage(HubCore.getInstance(), "BungeeCord", out.toByteArray());
        } else {
            p.performCommand(Utilities.parse(command,
                    Placeholder.parsed("server", server)
            ));
        }
    }

}
