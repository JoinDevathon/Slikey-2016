package org.devathon.contest2016;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.devathon.contest2016.armorstand.OffsetArmorStand;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author kevin
 * @since 06.11.2016
 */
public class TerminatorModel {

    private final List<OffsetArmorStand> allBlocks;
    private final List<OffsetArmorStand> head;
    private final List<OffsetArmorStand> leftArm, rightArm;
    private final List<OffsetArmorStand> body;
    private final List<OffsetArmorStand> energySource;
    private final List<OffsetArmorStand> leftLeg, rightLeg;
    private boolean destroyed;

    public TerminatorModel() {
        allBlocks = new ArrayList<>();
        head = new ArrayList<>();
        leftArm = new ArrayList<>();
        rightArm = new ArrayList<>();
        body = new ArrayList<>();
        energySource = new ArrayList<>();
        leftLeg = new ArrayList<>();
        rightLeg = new ArrayList<>();
        destroyed = false;
    }

    public OffsetArmorStand spawn(Supplier<Location> originSupplier,
                                  Vector offsetVector,
                                  ItemStack itemStack,
                                  TerminatorModel.TerminatorModelPart part) {
        final OffsetArmorStand oas = OffsetArmorStand.spawn(originSupplier.get(), originSupplier, offsetVector, itemStack);
        addParts(part, oas);
        return oas;
    }

    public void addParts(TerminatorModelPart modelPart, OffsetArmorStand... parts) {
        for (OffsetArmorStand oas : parts) {
            allBlocks.add(oas);
            switch (modelPart) {
                case HEAD:
                    head.add(oas);
                    break;
                case LEFT_ARM:
                    leftArm.add(oas);
                    break;
                case RIGHT_ARM:
                    rightArm.add(oas);
                    break;
                case BODY:
                    body.add(oas);
                    break;
                case ENERGY_SOURCE:
                    energySource.add(oas);
                    break;
                case LEFT_LEG:
                    leftLeg.add(oas);
                    break;
                case RIGHT_LEG:
                    rightLeg.add(oas);
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }

    public List<OffsetArmorStand> getParts(TerminatorModelPart modelPart) {
        switch (modelPart) {
            case HEAD:
                return head;
            case LEFT_ARM:
                return leftArm;
            case RIGHT_ARM:
                return rightArm;
            case BODY:
                return body;
            case ENERGY_SOURCE:
                return energySource;
            case LEFT_LEG:
                return leftLeg;
            case RIGHT_LEG:
                return rightLeg;
            default:
                throw new AssertionError();
        }
    }

    public void destroy() {
        destroyed = true;
        for (OffsetArmorStand oas : allBlocks) {
            oas.despawn();
        }
    }

    public void removePart(OffsetArmorStand oas) {
        allBlocks.remove(oas);
        head.remove(oas);
        leftArm.remove(oas);
        rightArm.remove(oas);
        body.remove(oas);
        energySource.remove(oas);
        leftLeg.remove(oas);
        rightLeg.remove(oas);
    }

    public enum TerminatorModelPart {

        HEAD, LEFT_ARM, RIGHT_ARM, BODY, ENERGY_SOURCE, LEFT_LEG, RIGHT_LEG

    }

}
