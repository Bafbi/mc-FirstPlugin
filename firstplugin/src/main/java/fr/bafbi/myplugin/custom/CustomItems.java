package fr.bafbi.myplugin.custom;

public enum CustomItems {
    VICTORY_SWORD("IRON_SWORD", 100101, "victory", "§cEpee de la victoire", "aa&bb", "x");

    private String material;
    private int customModelData;
    private String setName;
    private String displayName;
    private String lore;
    private String description;

    CustomItems(String material, int customModelData, String setName, String displayName, String lore, String description) {

        this.material = material;
        this.customModelData = customModelData;
        this.setName = setName;
        this.displayName = displayName;
        this.lore = lore;
        this.description = description;

    }
}
