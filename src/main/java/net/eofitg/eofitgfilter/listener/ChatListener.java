package net.eofitg.eofitgfilter.listener;

import net.eofitg.eofitgfilter.EofitgFilter;
import net.eofitg.eofitgfilter.config.FilterData;
import net.eofitg.eofitgfilter.util.PlayerUtil;
import net.eofitg.eofitgfilter.util.StringUtil;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

public class ChatListener {

    @SubscribeEvent
    public void onChatMessage(ClientChatReceivedEvent event) {
        if (EofitgFilter.config.isEnabled() && shouldFilter(event.message)) {
            event.setCanceled(true);
        }
    }

    private boolean shouldFilter(IChatComponent msg) {
        final String unformattedText = msg.getUnformattedText();
        final String formattedText = msg.getFormattedText();
        final String pureText = StringUtil.removeFormattingCodes(unformattedText);

        final List<FilterData> filters = EofitgFilter.config.getFilterList();

        for (FilterData filter : filters) {
            String check = filter.message;
            boolean useRegex = filter.regex;

            if (check.contains("*")) {
                useRegex = true;
            }

            if (useRegex) {
                check = regexInit(check);
                PlayerUtil.addMessage(check);
                PlayerUtil.addMessage(unformattedText.matches(check) + " " + unformattedText);
                if (unformattedText.matches(check)) return true;
                if (check.contains(StringUtil.FORMAT_SYMBOL)) {
                    if (formattedText.matches(check)) return true;
                } else {
                    if (pureText.matches(check)) return true;
                }
            } else {
                if (unformattedText.contains(check)) return true;
                if (check.contains(StringUtil.FORMAT_SYMBOL)) {
                    if (formattedText.contains(check)) return true;
                } else {
                    if (pureText.contains(check)) return true;
                }
            }
        }

        return false;
    }

    private String regexInit(String msg) {
        msg = msg.replace(" ", "\\ ");
        msg = msg.replace(".", "\\.");
        msg = msg.replace("*", ".*");  // for wildcard matching
        msg = msg.replace("+", "\\+");
        msg = msg.replace("$", "\\$");
        msg = msg.replace("?", "\\?");
        msg = msg.replace("^", "\\^");
        msg = msg.replace("[", "\\[");
        msg = msg.replace("]", "\\]");
        msg = msg.replace("(", "\\(");
        msg = msg.replace(")", "\\)");
        msg = msg.replace("{", "\\{");
        msg = msg.replace("}", "\\}");
        return msg.replace("|", "\\|");
    }

}
