package fi.natroutter.hubcore.utilities;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import fi.natroutter.hubcore.HubCore;
import fi.natroutter.hubcore.files.Config;
import fi.natroutter.natlibs.utilities.Utilities;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.entity.Player;

public class ServerSwitcher {


    public void switchServer(Player p, String server) {
        if (!Config.ServerSelector_UseOverride.asBoolean()) {
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("ConnectOther");
            out.writeUTF(p.getName());
            out.writeUTF(server);
            p.sendPluginMessage(HubCore.getInstance(), "BungeeCord", out.toByteArray());
        } else {
            p.performCommand(Utilities.parse(Config.ServerSelector_OverrideCommand.asString(),
                    Placeholder.parsed("server", server)
            ));
        }
    }

}
