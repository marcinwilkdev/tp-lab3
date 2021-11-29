package eu.jpereira.trainings.designpatterns.creational.prototype.model;

import eu.jpereira.trainings.designpatterns.creational.prototype.VehiclePart;
import eu.jpereira.trainings.designpatterns.creational.prototype.exception.VehicleDoesNotHavePartsException;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class VehicleTest extends TestCase {

    public void testTestClone() throws CloneNotSupportedException, VehicleDoesNotHavePartsException {
        Vehicle vehicle = new Vehicle();

        List<VehiclePart> parts = new ArrayList<VehiclePart>();

        Shell shell = new Shell();
        Tire tire = new Tire();
        Window window = new Window();

        parts.add(shell);
        parts.add(tire);
        parts.add(window);

        vehicle.setParts(parts);

        Vehicle clonedVehicle = vehicle.clone();

        assertNotSame(vehicle, clonedVehicle);
        assertNotSame(vehicle.getParts(VehiclePartEnumeration.SHELL).get(0), clonedVehicle.getParts(VehiclePartEnumeration.SHELL).get(0));
        assertNotSame(vehicle.getParts(VehiclePartEnumeration.WINDOW).get(0), clonedVehicle.getParts(VehiclePartEnumeration.WINDOW).get(0));
        assertNotSame(vehicle.getParts(VehiclePartEnumeration.TIRE).get(0), clonedVehicle.getParts(VehiclePartEnumeration.TIRE).get(0));
    }
}