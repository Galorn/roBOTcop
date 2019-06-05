package botPackage;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class mainBot extends ListenerAdapter{

	// lancement du bot
	public static void main(String[] args) throws LoginException, InterruptedException {
		String token = "NTg1ODkyMzAwNjE4NTk2MzUz.XPglDA.lPziYqVH2R5Qc2k3vQTulbsvgPM";
		JDA jdab = new JDABuilder(AccountType.BOT).setToken(token).buildAsync();
		jdab.addEventListener(new mainBot());
		jdab.awaitReady(); // Blocking guarantees that JDA will be completely loaded.
        System.out.println("Finished Building JDA!");
	}
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		System.out.println("reception" + event.getAuthor().getName() + " : " + event.getMessage().getContentDisplay());;
		// si BOT return 
		JDA jdab = event.getJDA();
		long responseNumber = event.getResponseNumber();
        //Event specific information
        User author = event.getAuthor();                //The user that sent the message
        Message message = event.getMessage();           //The message that was received.
        MessageChannel channel = event.getChannel();    //This is the MessageChannel that the message was sent to.
        //  This could be a TextChannel, PrivateChannel, or Group!

        String msg = message.getContentDisplay();     
		if (event.getAuthor().isBot()) {
			return;
		}
		// uniquement si dans le channel de test
		if (channel.getName().equals("test-bot")){
			// si pas de # en debut de content, return 
			if (message.getContentRaw().startsWith("TET")) {
				event.getChannel().sendMessage("KEYWORD").queue();
			}
		}

	}
}
