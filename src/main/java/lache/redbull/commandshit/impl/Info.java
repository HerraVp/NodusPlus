package lache.redbull.commandshit.impl;

import lache.redbull.commandshit.Command;
import lache.redbull.utils.XeroxPrinter;

public class Info extends Command {

    public Info() {
        super("Info", "Displays information", "info", "i", "information");
    }

    @Override
    public void onCommand(String[] args, String command) {
    	XeroxPrinter.print("\u00A7 Nodus+ a refresher to \u00A7 Nodus made by SirJava \u00A7 continued by WizardHax, now continued by KayEmEfDem");
        XeroxPrinter.print("\u00A7 do .help for the list of commands");
    }
}
