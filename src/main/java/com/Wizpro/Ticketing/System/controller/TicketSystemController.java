package com.Wizpro.Ticketing.System.controller;

import com.Wizpro.Ticketing.System.Entities.*;
import com.Wizpro.Ticketing.System.Repository.*;
import lombok.Generated;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@RestController
@CrossOrigin(origins = "*")
public class TicketSystemController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    LoginRepository loginRepository;

    @Autowired
    CustomerTicketRepository customerTicketRepository;

    @Autowired
    TicketStatusAuditRepository ticketStatusAuditRepository;

    @Autowired
    Login login;

    @Autowired
    User user;

    @Autowired
    Optional<Product> product;

    List<Product> productList=new ArrayList<>();

    @PostMapping("/login")
    public Login login(@RequestBody Login login)
    {
        System.out.println(login.getUsername()+" "+login.getPassword());
//        Login users=loginRepository.findByUsernameAndPassword(login.getUsername(),login.getPassword());
//        Login login1=loginRepository.findAllByName(login.getUsername(),login.getPassword());
//        System.out.println(login1);


        return loginRepository.findByUsernameAndPassword(login.getUsername(),login.getPassword());
    }
    @PostMapping("/usersignup")
    public String signupUser(@RequestParam("name")String name,
                             @RequestParam("email")String email,
                             @RequestParam("password")String password,
                             @RequestParam("role")String role,
                             @RequestParam("product")String[] product)
    {
//        user.setProduct(user.getProduct());
        System.out.println(name+" "+email+" "+password+" "+role+" "+product[0]);
        System.out.println(product.length);
        for (String n:product) {
//            System.out.println(name+" "+email+" "+password+" "+role+" "+n);
//           Product product1=productRepository.findById(Integer.valueOf(name)).get();
           Product list=productRepository.findProductByUserproduct(n);
            System.out.println("List is "+list);
           productList.add(list);
        }
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);
        user.setEnrollProduct(productList);
        userRepository.save(user);
        System.out.println(user);

        login.setUsername(email);
        login.setPassword(password);
        login.setRole(role);
        User user2=userRepository.findById(user.getId()).get();
        login.setUser(user2.getId());
        System.out.println("Login "+login);
        loginRepository.save(login);
        return "ok";
    }
    @PostMapping("/customersignup")
    public String signupCustomer(@RequestBody Customer customer)
    {
        customerRepository.save(customer);
        login.setUsername(customer.getEmail());
        login.setPassword(customer.getPassword());
        login.setRole("customer");
        Customer customer1=customerRepository.findById(customer.getId()).get();
        login.setCustomer(customer1.getId());
//        login.setUser_id(customer.getId());
        loginRepository.save(login);
        return String.valueOf(customer);
    }
    @PostMapping("/productsignup")
    public String signupProduct(@RequestBody Product product)
    {
//        System.out.println(product.getProduct());
        productRepository.save(product);
//        System.out.println(product);
        return String.valueOf(product);
    }
    @PutMapping("/edituser/{id}")
    public String editUser(@PathVariable Integer id,@RequestBody User user)
    {
        System.out.println(id+" "+user);
        userRepository.save(user);
        return String.valueOf(id);
    }
    @PutMapping("/editcustomer/{id}")
    public String editCustomer(@PathVariable Integer id,@RequestBody Customer customer)
    {
        customerRepository.save(customer);
        return String.valueOf(id);
    }
    @PutMapping("/editproduct/{id}")
    public String editProduct(@PathVariable Integer id,@RequestBody Product product)
    {
        System.out.println(id+" "+product);
        productRepository.save(product);
        return String.valueOf(id);
    }
    @GetMapping("/getUserBy/{username}")
    public User getUserByName(@PathVariable String username)
    {
        return userRepository.findByEmail(username);
    }
    @GetMapping("/getCustomerBy/{username}")
    public Customer getCustomerByName(@PathVariable String username)
    {
        return customerRepository.findByEmail(username).get();
    }
    @GetMapping("/getUsers")
    public List getUsers()
    {
//        return userRepository.findAll();
//        List<User>list=userRepository.findAll();
        List<User> list=userRepository.findProduct();
        for (User u:list) {
            System.out.println(u.getEnrollProduct());
        }
        return userRepository.findUserAndProduct();
    }
    @GetMapping("/findUsers/{username}")
    public List<UserListResponse> getusers(@PathVariable String username)
    {
        System.out.println(username);
        List<UserListResponse>userListResponseList=new ArrayList<>();
        List<User> list=userRepository.findAllUserByName(username);
        for (User u:list) {
            UserListResponse userListResponse=new UserListResponse();
//            userListResponse.setKey(u.getId());
            userListResponse.setLabel(u.getName());
            userListResponse.setValue(u.getId());

            userListResponseList.add(userListResponse);
        }
        return userListResponseList;
    }
    @GetMapping("/getCustomers")
    public List getCustomers()
    {
        List<Customer>customers=customerRepository.findAll();

//        System.out.println(users);

        return customers;
    }
    @GetMapping("/setStatusInprogress/{id}")
    public String setStatus(@PathVariable Long id)
    {
        System.out.println(id);
        Optional<CustomerTicket> customerTicket=customerTicketRepository.findById(id);
        if(customerTicket.isPresent())
        {
            CustomerTicket customerTicket1=customerTicket.get();
            customerTicket1.setStatus("Inprogress");
            customerTicketRepository.save(customerTicket1);
        }
        return new String("Successfull");
    }
    @GetMapping("/setStatusCompleted/{id}")
    public String setStatusClose(@PathVariable Long id)
    {
        System.out.println(id);
        Optional<CustomerTicket> customerTicket=customerTicketRepository.findById(id);
        if(customerTicket.isPresent())
        {
            CustomerTicket customerTicket1=customerTicket.get();
            customerTicket1.setStatus("Closed");
            customerTicketRepository.save(customerTicket1);
        }
        return new String("Successfull");
    }
    @GetMapping("/createTicketStatusAudit/{ticketid}/{userid}/{status}")
    public String setStatusCompleted(@PathVariable Long ticketid,@PathVariable Long userid,@PathVariable String status)
    {
        System.out.println(ticketid+" "+userid+" "+status);
        TicketStatusAudit ticketStatusAudit=new TicketStatusAudit();

        Optional<CustomerTicket> optionalCustomerTicket=customerTicketRepository.findById(ticketid);
        if(optionalCustomerTicket.isPresent())
        {
            CustomerTicket ticket=optionalCustomerTicket.get();
            ticketStatusAudit.setCustomer_id((long) ticket.getCustomer());
        }
        Optional<User> optionalUser=userRepository.findById(Math.toIntExact(userid));
        if(optionalUser.isPresent())
        {
            User user=optionalUser.get();
            ticketStatusAudit.setUser_id((long) user.getId());
//            System.out.println(user.getId());
        }
        ticketStatusAudit.setTicket_id(ticketid);
        ticketStatusAudit.setDummy_status(status);
//        System.out.println(id+" "+status);
        ticketStatusAuditRepository.save(ticketStatusAudit);

        Optional<CustomerTicket> customerTicket=customerTicketRepository.findById(ticketid);
        if(customerTicket.isPresent())
        {
            CustomerTicket customerTicket1=customerTicket.get();
            customerTicket1.setStatus("Complete");
            customerTicketRepository.save(customerTicket1);
        }
        return new String("successfull");
    }
    @GetMapping("/getTicketStatusAudit/{cname}")
    public List<TicketStatusAudit> getTicketStatusAudit(@PathVariable String cname)
    {
        List<TicketStatusAudit> responseList=new ArrayList<>();
        System.out.println("Customer "+cname);
        Optional<Customer> optionalCustomer =customerRepository.findByEmail(cname);
        if(optionalCustomer.isPresent())
        {
            Customer customer=optionalCustomer.get();
            List<TicketStatusAudit> ticketStatusAuditList=ticketStatusAuditRepository.findByCustomer_id((long) customer.getId());
            for (TicketStatusAudit status:ticketStatusAuditList) {
                responseList.add(status);
            }
        }
        return responseList;
    }
    @GetMapping("/getSingleTicketStatusAudit/{tid}")
    public ResponseEntity<Map> getSingleTicketStatusAudit(@PathVariable Long tid)
    {
            Map<String,String>stringMap=new HashMap<>();
            TicketStatusAudit ticketStatusAudit=ticketStatusAuditRepository.findByTicket_id(tid);
            if(ticketStatusAudit==null)
            {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                        Collections.singletonMap("data", "There is no ticketStatus Created"));
//                return new ResponseEntity<Map>(Map.of("error","TicketStatus Not Created"),HttpStatus.NO_CONTENT);
            }
            else
            {
                System.out.println(ticketStatusAudit);
                Optional<User>optionalUser=userRepository.findById(Math.toIntExact(ticketStatusAudit.getUser_id()));
                if(optionalUser.isPresent())
                {
                    User user=optionalUser.get();
                    stringMap.put("user",user.getName());
                    stringMap.put("status",ticketStatusAudit.getDummy_status());
                    stringMap.put("date",String.valueOf(ticketStatusAudit.getDummy_created_at()));
                }
            }
        System.out.println(ticketStatusAudit);
        return new ResponseEntity<Map>((stringMap),HttpStatus.OK);
    }
    @GetMapping("/getProducts")
    public List getProducts()
    {
        List<Product>products=productRepository.findAllProduct();
        System.out.println(products);


        return products;
    }
    @GetMapping("/getProductsByname")
    public List getProductsByName()
    {
        List<Product>products=productRepository.findAllProduct();
        System.out.println(products);
        for (Product p:products) {
            System.out.println(p);
        }

        return products;
    }
    @GetMapping("/countTickets")
    public int countTickets()
    {
        int x= (int) customerTicketRepository.count();
//        System.out.println(x);
        return x;
    }
    @GetMapping("/countByCompleted")
    public Long countTicketsByStatusCompleted()
    {
        long x= customerTicketRepository.countByStatus("Completed");
//        System.out.println(x);
        return x;
    }
    @GetMapping("/countByInprogress")
    public Long countTicketsByStatusInprogress()
    {
        Long x= customerTicketRepository.countByStatus("Inprogress");
//        System.out.println(x);
        return x;
    }
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Integer id)
    {
//        System.out.println(id);
        userRepository.deleteById(id);
        return "deleted";
    }
    @DeleteMapping("/deletecustomer/{id}")
    public String deleteCustomer(@PathVariable Integer id)
    {
//        System.out.println(id);
        customerRepository.deleteById(id);
        return "deleted";
    }
    @DeleteMapping("/deleteproduct/{id}")
    public String deleteProduct(@PathVariable Integer id)
    {
//        System.out.println(id);
        productRepository.deleteById(id);
        return "deleted";
    }
    @GetMapping("/add")
    public String addTicket()
    {
        return "Hello World";
    }
    @GetMapping("/viewTicket")
    public String viewTicket()
    {
        return "View Ticket";
    }
    @GetMapping("/users")
    public Product enrollUserToProduct()
    {
        Product list=productRepository.findProductByUserproduct("RFID");
        System.out.println(list.getId());
        return list;
    }

}
