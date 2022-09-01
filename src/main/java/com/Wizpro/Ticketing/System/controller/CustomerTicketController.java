package com.Wizpro.Ticketing.System.controller;


import com.Wizpro.Ticketing.System.Entities.*;
import com.Wizpro.Ticketing.System.Repository.*;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class CustomerTicketController {

    @Autowired
    Product product;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CustomerTicketRepository customerTicketRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CommentRepository commentRepository;

//    @Autowired
//    AssignTickets assignTickets;

    @Autowired
    AssignTicketRepository assignTicketRepository;

    Authentication authentication=new Authentication();

    String str="";

//    @PostMapping("/saveTicket")
    @PostMapping(value = "/saveTicket",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String saveTicket(
            @RequestParam("description") String description,
            @RequestParam("serialnumber") String serialnumber,
            @RequestParam("remark") String remark,
            @RequestParam("status") String status,
            @RequestParam("product") String product,
            @RequestParam("cust_name") String cust_name,
            @RequestParam("location") String location,
            @RequestParam("file") MultipartFile file)throws IOException, InvalidDefinitionException
    {
        System.out.println(cust_name+" "+description+" "+serialnumber+" "+remark+" "+status+" "+product+" "+file.getOriginalFilename());
        System.out.println(new Date().getTime());
        Product product1=productRepository.findProductByUserproduct(product);
        Customer customer=customerRepository.findByName(cust_name);
        CustomerTicket ticket=new CustomerTicket();
        ticket.setProduct(product1.getId());
        ticket.setDescription(description);
        ticket.setFiledata(file.getBytes());
        ticket.setRemark(remark);
        ticket.setSerialnumber(serialnumber);
        ticket.setStatus(status);
        ticket.setLocation(location);
        ticket.setCustomer(customer.getId());
//        System.out.println(ticket);
        Long id=customerTicketRepository.save(ticket).getId();
//        customerTicketRepository.insertTickets(description,serialnumber,remark,status,file.getOriginalFilename(), file.getContentType(),file.getBytes(),product1.getId(),customer.getId());

        Optional<CustomerTicket> customerTicket=customerTicketRepository.findById(id);
        if(customerTicket.isPresent())
        {
            CustomerTicket customerTicket1=customerTicket.get();
            List user_product=userRepository.findEnrollProductBy(product1.getId());
            for (Object u:user_product) {
                AssignTickets assignTickets=new AssignTickets();
                assignTickets.setTicket_id(Math.toIntExact(customerTicket1.getId()));
                assignTickets.setUser_id((Integer) u);
                assignTicketRepository.save(assignTickets);
            }
        }
        return "ok";
    }
    @GetMapping("/getTickets/{username}")
    public List<TicketResponse> findAllticketsByUsers(@PathVariable String username) {
        System.out.println(username);
        List<TicketResponse> responses = new ArrayList<>();
//        List<CustomerTicket>list1=customerTicketRepository.findAll();
//        List<Ticket>list=CustomerticketRepository.findAllTickets();
        User user = userRepository.findByEmail(username);
        List<CustomerTicket> list1 = customerTicketRepository.findUserTickets(user.getId());
        AssignTickets assignTickets = new AssignTickets();
        System.out.println(list1);
        for (CustomerTicket t : list1) {
            TicketResponse ticketResponse = new TicketResponse();
            ticketResponse.setId(t.getId());
            ticketResponse.setStatus(t.getStatus());
            ticketResponse.setSerialnumber(t.getSerialnumber());
            ticketResponse.setRemark(t.getRemark());
            ticketResponse.setDescription(t.getDescription());

//            LocalDateTime now = LocalDateTime.now();
//            System.out.println("Before Formatting: " + now);
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String formatDateTime = t.getCreated_at().format(format);
//            System.out.println("After Formatting: " + formatDateTime);
            ticketResponse.setCreated_at(formatDateTime);
            ticketResponse.setAssignby(t.getAssign());

            Optional<Customer> optionalCustomer = customerRepository.findById(t.getCustomer());
            if (optionalCustomer.isPresent()) {
                Customer customer = optionalCustomer.get();
                ticketResponse.setCustomer(customer.getName());
            }

            Optional<Product> product = productRepository.findById(t.getProduct());

            if (product.isPresent()) {
                Product product1 = product.get();
//                System.out.println(product1);
                ticketResponse.setCustomer_product(product1.getProduct());
            }
            String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/getFile/")
                    .path(String.valueOf(t.getId()))
                    .toUriString();
            ticketResponse.setFile(url);
            responses.add(ticketResponse);
        }
//        System.out.println(ticketResponse);

        return responses;
    }
    @GetMapping("/getCustomerTickets/{username}")
    public List<TicketResponse> findAllticketsByCustomer(@PathVariable String username)
    {
//        System.out.println(username);
        List<TicketResponse>responses=new ArrayList<>();
        try {

            Optional<Customer> customerOptional = customerRepository.findByEmail(username);

           if( customerOptional.isPresent())
           {
               Customer customer = customerOptional.get();

               List<CustomerTicket> customerTickets = customerTicketRepository.findByCustomerId(customer.getId());
               for (CustomerTicket t:customerTickets) {
                   TicketResponse ticketResponse=new TicketResponse();
                   ticketResponse.setId(t.getId());
                   ticketResponse.setStatus(t.getStatus());
                   ticketResponse.setSerialnumber(t.getSerialnumber());
                   ticketResponse.setRemark(t.getRemark());
                   ticketResponse.setLocation(t.getLocation());
                   ticketResponse.setDescription(t.getDescription());
                   DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                   String formatDateTime = t.getCreated_at().format(format);
//            System.out.println("After Formatting: " + formatDateTime);
                   ticketResponse.setCreated_at(formatDateTime);
//                   ticketResponse.setCreated_at((t.getCreated_at()));
                   ticketResponse.setCustomer_name(customer.getName());
//                   System.out.println(t.getAssign());
                   Optional<Product> product=productRepository.findById(t.getProduct());
                   if(product.isPresent())
                   {
                       Product product1=product.get();
                       ticketResponse.setCustomer_product(product1.getProduct());
                   }
                   String url= ServletUriComponentsBuilder.fromCurrentContextPath()
                           .path("/getFile/")
                           .path(String.valueOf(t.getId()))
                           .toUriString();
                   ticketResponse.setFile(url);
                   responses.add(ticketResponse);
//                   System.out.println(ticketResponse);
               }
           }

//            List<CustomerTicket>list1=customerTicketRepository.findAll();
         //   List<CustomerTicket>list1=customerTicketRepository.findCustomerTickets(username);

        }catch (HibernateException hibernateException)
        {
            System.out.println(hibernateException);
        }

//        List<Ticket>list1=ticketRepository.findAllTickets();

//        System.out.println(ticketResponse);

        return responses;
    }
    @GetMapping(value = "/getFile/{fileId}",produces = {MediaType.MULTIPART_FORM_DATA_VALUE, "application/json"})
    public ResponseEntity<Resource> getFile(@PathVariable Integer fileId)throws InvalidDefinitionException
    {
        CustomerTicket ticket=customerTicketRepository.findByFile(fileId);
        ResponseEntity<Resource> image=ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(ticket.getFiletype()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + ticket.getFilename() + "\"")
                .body(new ByteArrayResource(ticket.getFiledata()));
        return image;
    }
    @DeleteMapping("/deleteticket/{id}")
    public String deleteTicket(@PathVariable Integer id)
    {
        customerTicketRepository.deleteById(Long.valueOf(id));
        return "deleted";
    }
    @PostMapping(value="/savecomment",consumes = MediaType.APPLICATION_JSON_VALUE)
    public int saveComment(@RequestBody Comment comment)
    {
//        System.out.println(comment);
        commentRepository.save(comment);
        return comment.getTicket_id();
    }
    @GetMapping("/getSingleCommentBy/{id}")
    public CommentResponse getSingleComment(@PathVariable Long id)
    {
        CommentResponse commentResponse=new CommentResponse();
        Comment comment=commentRepository.findSingleCommentBy(Math.toIntExact(id));
        commentResponse.setId(comment.getId());

        Optional<User> user=userRepository.findById(comment.getUser_id());

        if(user.isPresent())
        {
            User user1=user.get();
            commentResponse.setUser(user1.getName());
            commentResponse.setRole(user1.getName());
        }
        Optional<Customer> customer=customerRepository.findById(comment.getCustomer_id());
        if(customer.isPresent())
        {
            Customer customer1=customer.get();
            commentResponse.setCustomer(customer1.getName());
            commentResponse.setRole(customer1.getName());
        }
        commentResponse.setMessage(comment.getMessage());
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formatDateTime = comment.getTimestamp().format(format);
        commentResponse.setTimestamp(formatDateTime);

        return commentResponse;
    }
    @GetMapping("/checkassignTicketTo/{uid}/{tid}")
    public String checkassignTicketToUser(@PathVariable int uid,@PathVariable int tid,@PathVariable Optional<String> username)
    {
        System.out.println(uid+" "+tid+"  "+username);
        User user=userRepository.findByEmail(String.valueOf(username));
        AssignTickets assignTickets=assignTicketRepository.findByAll(uid,tid);
        System.out.println(assignTickets);
        if(assignTickets==null)
        {
            str="Assigned";
//            System.out.println("No value found");
//            List<AssignTickets> assignTickets1=assignTicketRepository.findByUser(user.getId());
//            for (AssignTickets t:assignTickets1) {
//
////                System.out.println("All tickets"+t);
//                if(tid==t.getTicket_id())
//                {
//                    System.out.println("Single ticket "+t);
//                    Integer i=assignTicketRepository.updateUserSetStatusForName(Integer.valueOf(t.getId()),id,user.getName());
//                    Integer z=customerTicketRepository.updateAssign(Long.valueOf(tid),user.getName());
//                    if(i==1)
//                    {
//                        str="Assigned";
//                    }
//                }
//            }
        }
        else
        {
//            System.out.println("Already assign to another user");
            str="Already Assigned";
        }
        return str;
    }
    @GetMapping("/assignTicketTo/{id}/{tid}/{username}")
    public String assignTicketToUser(@PathVariable int id,@PathVariable int tid,@PathVariable String username)
    {
//        System.out.println(id+" "+tid+"  "+username);
        User user=userRepository.findByEmail(username);
        AssignTickets assignTickets=assignTicketRepository.findByAll(id,tid);
        if(assignTickets==null)
        {
//            System.out.println("No value found");
            List<AssignTickets> assignTickets1=assignTicketRepository.findByUser(user.getId());
            for (AssignTickets t:assignTickets1) {

//                System.out.println("All tickets"+t);
                if(tid==t.getTicket_id())
                {
                    System.out.println("Single ticket "+t);
                    Integer i=assignTicketRepository.updateUserSetStatusForName(Integer.valueOf(t.getId()),id,user.getName());
                    Integer z=customerTicketRepository.updateAssign(Long.valueOf(tid),user.getName());
                    if(i==1)
                    {
                       str="Assigned";
                    }
                }
            }
        }
        else
        {
//            System.out.println("Already assign to another user");
            str="Already Assigned";
        }
        return str;
    }
    @GetMapping("/getTickets")
    public List getTickets()
    {
        List<TicketResponse>ticketResponseList=new ArrayList<>();
        List<CustomerTicket>tickets=customerTicketRepository.findAllTickets();
        tickets.stream().forEach((ticket)->{
            Optional<Product> optionalProduct=productRepository.findById(ticket.getProduct());
            TicketResponse ticketResponse=new TicketResponse();
            ticketResponse.setId(ticket.getId());
            ticketResponse.setDescription(ticket.getDescription());
//            ticketResponse.setCreated_at(ticket.getCreated_at());
            if (optionalProduct.isPresent())
            {
                Product product=optionalProduct.get();
                ticketResponse.setCustomer_product(product.getProduct());
            }
            ticketResponse.setSerialnumber(ticket.getSerialnumber());
            ticketResponse.setRemark(ticket.getRemark());
            String url= ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/getFile/")
                    .path(String.valueOf(ticket.getId()))
                    .toUriString();
            ticketResponse.setFile(url);
            ticketResponse.setStatus(ticket.getStatus());
//            System.out.println(ticketResponse);
            ticketResponseList.add(ticketResponse);
        }
        );
        return ticketResponseList;
    }
    @GetMapping("/getCommentBy/{id}")
    public List<CommentResponse> getComments(@PathVariable Long id)
    {
        System.out.println(id);
        List<CommentResponse> commentResponseList=new ArrayList<>();
//        List<Comment> commentList = commentRepository.findByTicket_id(Math.toIntExact(id));
        List<Comment> commentList = commentRepository.findAllComments(Math.toIntExact(id));
//        System.out.println(commentList);
        for (Comment c:commentList) {

            CommentResponse commentResponse=new CommentResponse();
            commentResponse.setId(c.getId());

            Optional<User> user=userRepository.findById(c.getUser_id());
            if(user.isPresent())
            {
                User user1=user.get();
                commentResponse.setUser(user1.getName());
                commentResponse.setRole(user1.getName());
            }

            Optional<Customer> customer=customerRepository.findById(c.getCustomer_id());
            if(customer.isPresent())
            {
                Customer customer1=customer.get();
                commentResponse.setCustomer(customer1.getName());
                commentResponse.setRole(customer1.getName());
            }
            commentResponse.setMessage(c.getMessage());
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String formatDateTime = c.getTimestamp().format(format);
            commentResponse.setTimestamp(formatDateTime);
            commentResponseList.add(commentResponse);
        }
//        System.out.println(commentList);
        return commentResponseList;
    }
}
