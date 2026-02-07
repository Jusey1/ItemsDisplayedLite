package net.salju.display.init;

import net.salju.display.Display;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;

public class DisplayTags {
    public static final TagKey<Item> DISPLAYABLE = ItemTags.create(Identifier.fromNamespaceAndPath(Display.MODID, "displayable"));
    public static final TagKey<Item> MUSIC = ItemTags.create(Identifier.fromNamespaceAndPath(Display.MODID, "music_discs"));
    public static final TagKey<Item> SHERDS = ItemTags.create(Identifier.fromNamespaceAndPath(Display.MODID, "sherds"));
    public static final TagKey<Item> TEMPLATES = ItemTags.create(Identifier.fromNamespaceAndPath(Display.MODID, "templates"));
    public static final TagKey<Item> CALCITE = ItemTags.create(Identifier.fromNamespaceAndPath(Display.MODID, "templates_calcite"));
    public static final TagKey<Item> COPPER = ItemTags.create(Identifier.fromNamespaceAndPath(Display.MODID, "templates_copper"));
    public static final TagKey<Item> DEEPSLATE = ItemTags.create(Identifier.fromNamespaceAndPath(Display.MODID, "templates_deepslate"));
    public static final TagKey<Item> GRAVEL = ItemTags.create(Identifier.fromNamespaceAndPath(Display.MODID, "templates_gravel"));
    public static final TagKey<Item> NETHERBRICK = ItemTags.create(Identifier.fromNamespaceAndPath(Display.MODID, "templates_netherbrick"));
    public static final TagKey<Item> NETHERRACK = ItemTags.create(Identifier.fromNamespaceAndPath(Display.MODID, "templates_netherrack"));
}