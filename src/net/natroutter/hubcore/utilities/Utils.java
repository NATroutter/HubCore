package net.natroutter.hubcore.utilities;

import net.natroutter.hubcore.HubCore;
import net.natroutter.natlibs.objects.BasePlayer;
import net.natroutter.natlibs.utilities.StringHandler;

public class Utils {

	private static Lang lang = HubCore.getLang();
	
	public static enum CET {
		TooMany(lang.TooManyArguments),
		Invalid(lang.InvalidArguments),
		NoPerm(lang.NoPerm),
		InvalidPlayer(lang.InvalidPlayer);
		
		private String message;
		private CET(String str) {
			this.message = str;	
		}
		public String getMessage() {
			return message;
		}
	}
	public static void CommandError(BasePlayer p, CET type) {CommandError(p, type, null);}
	public static void CommandError(BasePlayer p, CET type, String usage) {
		StringHandler message = new StringHandler(type.getMessage());
		message.setPrefix(lang.Prefix);
		if (usage != null) {
			message.replaceAll("{Usage}", usage);
		}
		message.send(p);
	}
	
}
