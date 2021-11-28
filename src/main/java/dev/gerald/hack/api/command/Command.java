//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\srgtmomentum\Desktop\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package dev.gerald.hack.api.command;

public abstract class Command
{
    private String name;
    private String description;
    public String[] args;
    
    public Command(final String name, final String description) {
        this.name = name;
        this.description = description;
        this.args = new String[] { "" };
    }
    
    public Command(final String name, final String description, final String[] commands) {
        this.name = name;
        this.description = description;
        this.args = commands;
    }
    
    public abstract void execute(final String[] p0);
    
    public String getName() {
        return this.name;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public String[] getArgs() {
        return this.args;
    }
}
