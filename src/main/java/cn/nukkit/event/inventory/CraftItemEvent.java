package cn.nukkit.event.inventory;

import cn.nukkit.Player;
import cn.nukkit.event.Cancellable;
import cn.nukkit.event.Event;
import cn.nukkit.event.HandlerList;
import cn.nukkit.item.Item;
import cn.nukkit.recipe.Recipe;

public class CraftItemEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();

    public static HandlerList getHandlers() {
        return handlers;
    }

    private Item[] input = Item.EMPTY_ARRAY;

    private final Recipe recipe;

    private final Player player;

    private final int count;

    public CraftItemEvent(Player player, Item[] input, Recipe recipe, int count) {
        this.player = player;
        this.input = input;
        this.recipe = recipe;
        this.count = count;
    }

    public Item[] getInput() {
        return input;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public Player getPlayer() {
        return this.player;
    }

    public int getCount() {
        return this.count;
    }
}