package lache.redbull.commandshit.impl;

import lache.redbull.Nodusplus;
import lache.redbull.commandshit.Command;
import lache.redbull.modules.Module;


public class Toggle extends Command {

    public Toggle() {
        super("Toggle", "Toggles a module", "toggle <name>", "t");
    }

    @Override
    public void onCommand(String[] args, String command) {
        String moduleName = args[0];
        
        boolean foundModule = false;

        for (Module module : Nodusplus.modules) {
            if (module.name.equalsIgnoreCase(moduleName)) {
            	
            	Nodusplus.addChatMessage(module.isEnabled() ? "Enabled" : "Disabled" + " " + module.name);
            	
                module.toggle();
                break;
            }
            
            }
        
        }
    }



