package net.eofitg.eofitgfilter.command;

import net.eofitg.eofitgfilter.EofitgFilter;
import net.eofitg.eofitgfilter.util.PlayerUtil;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumChatFormatting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EofitgFilterCommand extends CommandBase {

    private static final List<String> SUBCOMMANDS = Arrays.asList("toggle", "reload");

    @Override
    public String getCommandName() {
        return "eofitgfilter";
    }

    @Override
    public List<String> getCommandAliases() {
        return Arrays.asList("eof", "eofilter");  // 'eofilter' is undocumented because I never really thought it was a good name (_ _)
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/eofitgfilter toggle|reload";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (!(sender instanceof EntityPlayer)) return;
        if (args.length == 0) {
            PlayerUtil.addMessage(EnumChatFormatting.YELLOW + "Usage: " + getCommandUsage(sender));
            return;
        }

        String sub = args[0].toLowerCase();

        switch (sub) {
            case "toggle": {
                EofitgFilter.config.setEnabled(!EofitgFilter.config.isEnabled());
                boolean isEnabled = EofitgFilter.config.isEnabled();
                String status = isEnabled ? EnumChatFormatting.GREEN + "enabled" : EnumChatFormatting.RED + "disabled";
                PlayerUtil.addMessage(EnumChatFormatting.GOLD + "Mod " + status + EnumChatFormatting.GOLD + ".");
                EofitgFilter.saveConfig();
                break;
            }
            case "reload": {
                EofitgFilter.loadConfig();
                PlayerUtil.addMessage(EnumChatFormatting.GOLD + "Mod configuration reloaded.");
                break;
            }
            default: {
                PlayerUtil.addMessage(EnumChatFormatting.RED + "Unknown argument: " + sub);
                PlayerUtil.addMessage(EnumChatFormatting.YELLOW + "Usage: " + getCommandUsage(sender));
            }
        }
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        List<String> completions = new ArrayList<>();
        if (args.length == 1) {
            String prefix = args[0].toLowerCase();
            for (String cmd : SUBCOMMANDS) {
                if (cmd.startsWith(prefix)) {
                    completions.add(cmd);
                }
            }
        }
        return completions.isEmpty() ? null : completions;
    }

}
