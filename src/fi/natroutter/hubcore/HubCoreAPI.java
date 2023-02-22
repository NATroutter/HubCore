package fi.natroutter.hubcore;

import fi.natroutter.hubcore.features.SelectorItems.SelectorItemHandler;
import fi.natroutter.hubcore.handlers.AdminModeHandler;
import fi.natroutter.hubcore.handlers.Hooks;
import fi.natroutter.hubcore.utilities.Items;
import fi.natroutter.hubcore.features.particles.ParticleGUI;
import fi.natroutter.hubcore.features.playercarry.PlayerCarryHandler;
import fi.natroutter.hubcore.features.gadgets.GadgetHandler;
import fi.natroutter.hubcore.features.particles.ParticleScheduler;
import fi.natroutter.hubcore.features.protections.ProtectionHandler;
import fi.natroutter.hubcore.utilities.ServerSwitcher;

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
