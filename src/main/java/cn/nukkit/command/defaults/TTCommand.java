package cn.nukkit.command.defaults;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.command.tree.ParamList;
import cn.nukkit.command.utils.CommandLogger;
import cn.nukkit.inventory.fake.FakeStructBlock;
import cn.nukkit.level.Level;
import org.jetbrains.annotations.ApiStatus;

import java.util.Map;

@ApiStatus.Internal
public class TTCommand extends TestCommand {
    FakeStructBlock fakeStructBlock;

    public TTCommand(String name) {
        super(name, "tt");
        this.commandParameters.clear();
        this.commandParameters.put("default", new CommandParameter[]{
                CommandParameter.newEnum("sub", new String[]{"1", "2"}),
        });
        this.enableParamTree();
    }

    @Override
    public int execute(CommandSender sender, String commandLabel, Map.Entry<String, ParamList> result, CommandLogger log) {
        ParamList value = result.getValue();
        String v = value.getResult(0);

        if (sender.isOp()) {
            boolean isPlayer = sender.isPlayer();
            if (isPlayer) {
                Player player = sender.asPlayer();
                if (v.equals("1")) {
                    Level level = player.getLevel();
                    System.out.println(level.getBlock(player.getPosition()));
                    System.out.println(level.getBlock(player.getPosition().add(1, 0, 0)));
                    System.out.println(level.getBlock(player.getPosition().add(0, 0, 1)));
                    System.out.println(level.getBlock(player.getPosition().add(0, 0, -1)));
                    System.out.println(level.getBlock(player.getPosition().add(-1, 0, 0)));
                } else if (v.equals("2")) {
                }
            }
            return 1;
        } else return 0;
    }
}
