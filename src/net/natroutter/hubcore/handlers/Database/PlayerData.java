package net.natroutter.hubcore.handlers.Database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.UUID;

@DatabaseTable(tableName = "PlayerData")
public class PlayerData {

    @DatabaseField(canBeNull = false, id = true)
    UUID uuid;

    @DatabaseField(canBeNull = false)
    String shape;

    @DatabaseField(canBeNull = false)
    Boolean trail;

    @DatabaseField(canBeNull = false)
    Boolean twinkle;

    @DatabaseField(canBeNull = false)
    int color_r;

    @DatabaseField(canBeNull = false)
    int color_g;

    @DatabaseField(canBeNull = false)
    int color_b;

    @DatabaseField(canBeNull = false)
    int fade_r;

    @DatabaseField(canBeNull = false)
    int fade_g;

    @DatabaseField(canBeNull = false)
    int fade_b;

    @DatabaseField(canBeNull = false)
    String particle;

    @DatabaseField(canBeNull = false)
    String particlemode;


    public PlayerData() {}
    public PlayerData(UUID uuid, String shape, Boolean trail, Boolean twinkle, int color_r, int color_g, int color_b, int fade_r, int fade_g, int fade_b, String particle, String particlemode) {
        this.uuid = uuid;
        this.shape = shape;
        this.trail = trail;
        this.twinkle = twinkle;
        this.color_r = color_r;
        this.color_g = color_g;
        this.color_b = color_b;
        this.fade_r = fade_r;
        this.fade_g = fade_g;
        this.fade_b = fade_b;
        this.particle = particle;
        this.particlemode = particlemode;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public Boolean getTrail() {
        return trail;
    }

    public void setTrail(Boolean trail) {
        this.trail = trail;
    }

    public Boolean getTwinkle() {
        return twinkle;
    }

    public String getParticle() {
        return particle;
    }

    public String getParticlemode() {
        return particlemode;
    }

    public void setParticle(String particle) {
        this.particle = particle;
    }

    public void setParticlemode(String particlemode) {
        this.particlemode = particlemode;
    }

    public void setTwinkle(Boolean twinkle) {
        this.twinkle = twinkle;
    }

    public int getColor_r() {
        return color_r;
    }

    public void setColor_r(int color_r) {
        this.color_r = color_r;
    }

    public int getColor_g() {
        return color_g;
    }

    public void setColor_g(int color_g) {
        this.color_g = color_g;
    }

    public int getColor_b() {
        return color_b;
    }

    public void setColor_b(int color_b) {
        this.color_b = color_b;
    }

    public int getFade_r() {
        return fade_r;
    }

    public void setFade_r(int fade_r) {
        this.fade_r = fade_r;
    }

    public int getFade_g() {
        return fade_g;
    }

    public void setFade_g(int fade_g) {
        this.fade_g = fade_g;
    }

    public int getFade_b() {
        return fade_b;
    }

    public void setFade_b(int fade_b) {
        this.fade_b = fade_b;
    }
}
