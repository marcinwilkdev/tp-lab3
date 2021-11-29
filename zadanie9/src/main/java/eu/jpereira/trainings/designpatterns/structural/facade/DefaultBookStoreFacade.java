package eu.jpereira.trainings.designpatterns.structural.facade;

import eu.jpereira.trainings.designpatterns.structural.facade.model.Book;
import eu.jpereira.trainings.designpatterns.structural.facade.model.Customer;
import eu.jpereira.trainings.designpatterns.structural.facade.model.DispatchReceipt;
import eu.jpereira.trainings.designpatterns.structural.facade.model.Order;
import eu.jpereira.trainings.designpatterns.structural.facade.service.*;

public class DefaultBookStoreFacade implements BookstoreFacade {
    private BookDBService bookService;
    private CustomerDBService customerService;
    private CustomerNotificationService customerNotificationService;
    private OrderingService orderingService;
    private WharehouseService wharehouseService;

    @Override
    public void placeOrder(String customerId, String isbn) {
        Customer customer = customerService.findCustomerById(customerId);
        Book book = bookService.findBookByISBN(isbn);

        Order order = orderingService.createOrder(customer, book);
        DispatchReceipt dispatchReceipt = wharehouseService.dispatch(order);

        customerNotificationService.notifyClient(dispatchReceipt);
    }

    public void setBookService(BookDBService bookService) {
        this.bookService = bookService;
    }

    public void setCustomerService(CustomerDBService customerService) {
        this.customerService = customerService;
    }

    public void setCustomerNotificationService(CustomerNotificationService customerNotificationService) {
        this.customerNotificationService = customerNotificationService;
    }

    public void setOrderingService(OrderingService orderingService) {
        this.orderingService = orderingService;
    }

    public void setWharehouseService(WharehouseService wharehouseService) {
        this.wharehouseService = wharehouseService;
    }
}
