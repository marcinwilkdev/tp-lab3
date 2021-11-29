package eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty;

import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.CodeMismatchException;
import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.IncorrectDoorCodeException;
import eu.jpereira.trainings.designpatterns.structural.adapter.model.Door;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotChangeCodeForUnlockedDoor;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotChangeStateOfLockedDoor;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotUnlockDoorException;

public class ThirdPartyDoorAdapter extends ThirdPartyDoor implements Door {
    @Override
    public void open(String code) throws IncorrectDoorCodeException {
        try {
            this.unlock(code);
            this.setState(ThirdPartyDoor.DoorState.OPEN);
        } catch (CannotUnlockDoorException | CannotChangeStateOfLockedDoor e) {
            throw new IncorrectDoorCodeException();
        }
    }

    @Override
    public void close() {
        try {
            this.setState(ThirdPartyDoor.DoorState.CLOSED);
            this.lock();
        } catch (CannotChangeStateOfLockedDoor e) {}
    }

    @Override
    public boolean isOpen() {
        return this.getState() == ThirdPartyDoor.DoorState.OPEN;
    }

    @Override
    public void changeCode(String oldCode, String newCode, String newCodeConfirmation) throws IncorrectDoorCodeException, CodeMismatchException {
        if(!newCode.equals(newCodeConfirmation)) throw new CodeMismatchException();

        boolean prevIsOpen = this.isOpen();

        try {
            this.unlock(oldCode);
        } catch (CannotUnlockDoorException e) {
            throw new IncorrectDoorCodeException();
        }

        try {
            this.setNewLockCode(newCode);
        } catch (CannotChangeCodeForUnlockedDoor e) {}

        if(!prevIsOpen) this.lock();
    }

    @Override
    public boolean testCode(String code) {
        boolean prevIsOpen = this.isOpen();

        try {
            this.unlock(code);
        } catch (CannotUnlockDoorException e) {
            return false;
        }

        if(!prevIsOpen) this.lock();

        return true;
    }
}
