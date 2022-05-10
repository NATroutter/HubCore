package net.natroutter.hubcore;

import net.natroutter.hubcore.features.particles.ParticleGUI;
import net.natroutter.hubcore.features.playercarry.PlayerCarryHandler;
import net.natroutter.hubcore.features.SelectorItems.SelectorItemHandler;
import net.natroutter.hubcore.features.gadgets.GadgetHandler;
import net.natroutter.hubcore.features.particles.ParticleScheduler;
import net.natroutter.hubcore.features.protections.ProtectionHandler;
import net.natroutter.hubcore.features.protections.ProtectionListener;
import net.natroutter.hubcore.handlers.AdminModeHandler;
import net.natroutter.hubcore.handlers.Hooks;
import net.natroutter.hubcore.utilities.Items;
import net.natroutter.hubcore.utilities.ServerSwitcher;

public class HubCoreAPI {

    private Handler handler;

    public HubCoreAPI(Handler handler) {
        this.handler = handler;
    }

    public AdminModeHandler getAdminModeHandler() {
        return handler.getAdminModeHandler();
    }

    public GadgetHandler getGadgetHandler() {
        return handler.getGadgetHandler();
    }

    public SelectorItemHandler getSelectorItemHandler() {
        return handler.getSelectorItemHandler();
    }

    public Hooks getHooks() {
        return handler.getHooks();
    }

    public Items getItems() {
        return handler.getItems();
    }

    public ParticleScheduler getParticleScheduler() {
        return handler.getParticleScheduler();
    }

    public PlayerCarryHandler getPlayerCarryHandler() {
        return handler.getPlayerCarryHandler();
    }
    public ProtectionHandler getProtectionHandler() {
        return handler.getProtectionHandler();
    }

    public ParticleGUI getParticleGUI() {
        return handler.getParticleGUI();
    }

    public ServerSwitcher getServerSwitcher() {
        return handler.getServerSwitcher();
    }

}
