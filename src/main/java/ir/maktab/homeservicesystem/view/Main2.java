/*
package ir.maktab.homeservicesystem.view;


import ir.maktab.homeservicesystem.config.SpringConfig;
import ir.maktab.homeservicesystem.data.entities.users.Customer;

import ir.maktab.homeservicesystem.data.enumaration.UserStatus;
import ir.maktab.homeservicesystem.service.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.validation.Validation;
import java.text.ParseException;
import java.util.Scanner;

class Main2 {



    final static ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    final static AdminService adminService = context.getBean(AdminService.class);
    final static MainServiceService mainServiceService = context.getBean(MainServiceService.class);
    final static SubServiceService SUB_SERVICE_SERVICE = context.getBean(SubServiceService.class);
    final static CustomerService customerService = context.getBean(CustomerService.class);
    final static ExpertService EXPERT_SERVICE = context.getBean(ExpertService.class);
    final static OrderService ORDER_SERVICE = context.getBean(OrderService.class);
    final static OfferService OFFER_SERVICE = context.getBean(OfferService.class);
    final static UserFeedbackService userFeedbackService = context.getBean(UserFeedbackService.class);
    final static Scanner scanner = new Scanner(System.in);


    final static Validation validation = new Validation();

    public static void main(String[] args) throws ParseException {


//                InsertCustomer

            Customer narges = new Customer();
            narges.setFirstName("narges");
            narges.setLastName("jam");
            narges.setUserName("n123");
            narges.setPassword("narges@");
            narges.setCustomerStatus(UserStatus.UNDER_APPROVAL);


        customerService.save(narges);}
//            Customer roya = new Customer();
//            roya.setFirstName("roya");
//            roya.setLastName("ahmadi");
//            roya.setEmail("roy@gmail.com");
//            roya.setUserName("roya123");
//            roya.setPassword("roya@");
//            roya.setRole(Role.CUSTOMER);
//            roya.setStatus(UserStatus.UNDER_APPROVAL);
////            customerService.saveNewCustomer(roya);
////            Provider sara = (Provider) Provider.builder().firstName("sara").lastName("sarmad").email("sara@gmail.com")
////                    .userName("sara123").password("sara111").role(Role.PROVIDER)
////                    .status(UserStatus.UNDER_APPROVAL).build();
//            Provider provider2 = new Provider();
//            provider2.setFirstName("ali");
//            provider2.setLastName("alavi");
//            provider2.setEmail("ali@gmail.com");
//            provider2.setUserName("ali123");
//            provider2.setPassword("ali20");
//            provider2.setRole(Role.PROVIDER);
//            provider2.setStatus(UserStatus.UNDER_APPROVAL);
//            Provider provider3 = new Provider();
//            provider3.setFirstName("nazanin");
//            provider3.setLastName("nazari");
//            provider3.setEmail("nazi@gmail.com");
//            provider3.setUserName("nazi123");
//            provider3.setPassword("asd123");
//            provider3.setRole(Role.PROVIDER);
//            provider3.setStatus(UserStatus.UNDER_APPROVAL);
//
//
////      InsertAdmin
//            Admin admin1 = new Admin();
//            admin1.setUserName("admin1");
//            admin1.setPassWord("admin123");
//            Admin admin2 = new Admin();
//            admin2.setUserName("admin2");
//            admin2.setPassWord("admin422");
//
////      Insert MainService
//            MainService mainService = new MainService();
//            String name = "building decoration";
//            String replace = name.toUpperCase().replace(" ", "_");
//            mainService.setTitle(replace);
//            MainService mainService2 = new MainService();
//            mainService2.setTitle("BUILDING_FACILITIES");
//            MainService mainService3 = new MainService();
//            mainService3.setTitle("HOME_APPLIANCES");
//            MainService mainService4 = new MainService();
//            mainService4.setTitle("MOVING_HELP");
//            MainService mainService5 = new MainService();
//            mainService5.setTitle("HOME_CLEANING_AND_HYGIENE");
//            MainService mainService6 = new MainService();
//            mainService6.setTitle("VEHICLES");
////            providerService.saveNewProvider(sara);
////            providerService.saveNewProvider(provider2);
////            providerService.saveNewProvider(provider3);
//
////            adminService.saveNewAdmin(admin1);
////            adminService.saveNewAdmin(admin2);
////            mainServiceService.saveNewService(mainService);
////            mainServiceService.saveNewService(mainService2);
////            mainServiceService.saveNewService(mainService3);
////            mainServiceService.saveNewService(mainService4);
////            mainServiceService.saveNewService(mainService5);
////            mainServiceService.saveNewService(mainService6);
//
//
//
////              find mainService by Title
//            MainService home_appliances = mainServiceService.findServiceByTitle("HOME_APPLIANCES");
////       save Category
//            ServiceCategory kitchenAppliances = new ServiceCategory();
//
////        changepassword
//
//            Customer roya123 = customerService.findCustomerByUserNameAndPass("roya123", "roya@");
//            roya123.setPassword("royapo");
//            roya123.setCredit(200000);
//            customerService.updateCustomer(roya123);
//
////            changeUser
//            User n123 = userService.findUserByUserNameAndPass("n123", "narges@");
//            n123.setCredit(300000);
//            userService.update(n123);
//
////            customerdelete
////            customerService.deleteCustomer(narges);
//
////           findAlls
//            adminService.findAll().forEach(System.out::println);
//            System.out.println("********admins********");
//            customerService.findAll().forEach(System.out::println);
//            System.out.println("********customers*********");
//            providerService.findAll().forEach(System.out::println);
//            System.out.println("*********providers**********");
//
////       findAllMainServices
//            mainServiceService.findAllMainService().forEach(System.out::println);
////       findMainServiceByTitle
//            MainService HOME_CLEANING_AND_HYGIENE = mainServiceService.findServiceByTitle("HOME_CLEANING_AND_HYGIENE".trim());
////       findAllCategory
//            serviceCategoryService.findAll().forEach(System.out::println);
////       findCategorbyname
//            ServiceCategory findbyName = serviceCategoryService.findByName("kitchenAppliances ".trim());
//            System.out.println(findbyName);
////      findproviderbyname
//
//            Provider ali123 = providerService.findByUserNameAndPass("ali123", "ali20");
//            System.out.println(ali123);
//
//
//
////slectservice
////MainService byId = mainServiceService.findServiceById(1);
////        System.out.println("******** " + byId);
//        //ایجاد سفارش
//        try {
//            Customer customer = customerService.findCustomerByEmail("roy@gmail.com");
//            Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse("1400-10-15 12:30");
//            ServiceCategory serviceCategory = serviceCategoryService.findByName("cleaning");
//            ServiceRequest serviceRequest = new ServiceRequest();
//            serviceRequest.setPrice(10000);
//                    serviceRequest.setDescription("description");
//                    serviceRequest.setStartDate(date);
//                   serviceRequest.setCustomer(customer);
//                   serviceRequest.setStatus(ServiceRequestStatus.UNDER_OFFERING);
//                   serviceRequest.setServiceCategory(serviceCategory);
//
//           serviceRequestService.saveNewServiceRequest(serviceRequest);
//        }catch (RuntimeException e){
//            System.out.println(e.getMessage());
//        }
//        // ایجاد پیشنهاد برای یک سفارش
//       try {
//            ServiceRequest serviceRequest = serviceRequestService.findById(1);
//            Provider provider = providerService.findByEmail("roya@gmail.com");
//            Date startDate = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse("1400-10-15 13:30");
//            ServiceOffer serviceOffer = new ServiceOffer();
//            serviceOffer.setProvider(provider);
//            serviceOffer.setServiceRequest(serviceRequest);
//            serviceOffer.setPrice(22000);
//            serviceOffer.setStartHour(serviceOffer.getStartHour());
//            serviceOffer.setServiceOfferStatus(ServiceOfferStatus.UNCHECKED);
//
//            serviceOfferService.saveNewServiceOffer(serviceOffer);
//        } catch (RuntimeException e) {
//           System.out.println(e.getMessage());
//       }
//        //نظر دادن راجع به سفارش
//        try {
//            ServiceRequest serviceRequest = serviceRequestService.findById(1);
//            ServiceOffer acceptedOffer = serviceRequestService.findAcceptOfferOfRequest(serviceRequest);
//            Provider provider = acceptedOffer.getProvider();
//            Double ServiceRequestScore = 2D;
//            providerService.updateProvider(provider, ServiceRequestScore);
//            UserFeedback feedback = new UserFeedback();
////            feedback.setCustomer(customer);
//            feedback.setServiceRequest(serviceRequest);
//            feedback.setRate(4);
//            feedback.setText("text");
//
//            userFeedbackService.save(feedback);
//        } catch (RuntimeException e) {
//            System.out.println(e.getMessage());
//        }
       }
*/
