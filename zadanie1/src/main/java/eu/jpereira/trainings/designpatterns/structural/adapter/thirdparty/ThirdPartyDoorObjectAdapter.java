package eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty;

import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.CodeMismatchException;
import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.IncorrectDoorCodeException;
import eu.jpereira.trainings.designpatterns.structural.adapter.model.Door;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotChangeCodeForUnlockedDoor;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotChangeStateOfLockedDoor;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotUnlockDoorException;

public class ThirdPartyDoorObjectAdapter implements Door {
    private final ThirdPartyDoor thirdPartyDoor;

    public ThirdPartyDoorObjectAdapter() {
        this.thirdPartyDoor = new ThirdPartyDoor();
    }

    @Override
    public void open(String code) throws IncorrectDoorCodeException {
        try {
            this.thirdPartyDoor.unlock(code);
            this.thirdPartyDoor.setState(ThirdPartyDoor.DoorState.OPEN);
        } catch (CannotUnlockDoorException | CannotChangeStateOfLockedDoor e) {
            throw new IncorrectDoorCodeException();
        }
    }

    @Override
    public void close() {
        try {
            this.thirdPartyDoor.setState(ThirdPartyDoor.DoorState.CLOSED);
            this.thirdPartyDoor.lock();
        } catch (CannotChangeStateOfLockedDoor e) {}
    }

    @Override
    public boolean isOpen() {
        return this.thirdPartyDoor.getState() == ThirdPartyDoor.DoorState.OPEN;
    }

    @Override
    public void changeCode(String oldCode, String newCode, String newCodeConfirmation) throws IncorrectDoorCodeException, CodeMismatchException {
        if(!newCode.equals(newCodeConfirmation)) throw new CodeMismatchException();

        boolean prevIsOpen = this.isOpen();

        try {
            this.thirdPartyDoor.unlock(oldCode);
        } catch (CannotUnlockDoorException e) {
            throw new IncorrectDoorCodeException();
        }

        try {
            this.thirdPartyDoor.setNewLockCode(newCode);
        } catch (CannotChangeCodeForUnlockedDoor e) {}

        if(!prevIsOpen) this.thirdPartyDoor.lock();
    }

    @Override
    public boolean testCode(String code) {
        boolean prevIsOpen = this.isOpen();

        try {
            this.thirdPartyDoor.unlock(code);
        } catch (CannotUnlockDoorException e) {
            return false;
        }

        if(!prevIsOpen) this.thirdPartyDoor.lock();

        return true;
    }
}
