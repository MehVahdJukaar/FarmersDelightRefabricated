package com.nhoryzon.mc.farmersdelight.exception;

@Deprecated(forRemoval = true)
public class SlotInvalidRangeException extends RuntimeException {

    public SlotInvalidRangeException(int slotNumber, int maxRange) {
        super("Slot " + slotNumber + " not in valid range - [0," + maxRange + ")");
    }

}