import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.example.demo.controllers.AdminController;
import com.example.demo.dto.ItemDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.models.Item;
import com.example.demo.models.User;
import com.example.demo.repositories.ItemImagesRepository;
import com.example.demo.repositories.ItemRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.ItemService;
import com.example.demo.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.mock.web.MockHttpSession;
import org.eclipse.jetty.http.ComplianceViolation.Mode;
import org.junit.jupiter.api.Assertions;


import static org.mockito.ArgumentMatchers.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;





@ExtendWith(MockitoExtension.class)
public class AdminControllerTest {

    private MockHttpSession session = new MockHttpSession();

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserService userService;

    @Mock
    private ItemService itemService;

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private ItemImagesRepository itemImageRepository;

    @InjectMocks
    private AdminController adminController;

    @Captor
    private ArgumentCaptor<ItemDTO> itemDTOCaptor;

    private MockMvc mockMvc;

    @Mock
    private RestTemplate restTemplate;

    @Test
    public void testGetDashboard() throws Exception {
         // Simulating a logged-in admin user
         User adminUser = new User("Admin", "admin@example.com", "adminPassword", "Admin", "Admin Address", true);
        
         // Create a MockHttpSession
         MockHttpSession mockSession = new MockHttpSession();
         mockSession.setAttribute("user", adminUser);
 
         // Mock userService to return true when valid credentials are provided
       //  when(userService.getUser("validemail@example.com", "validpassword", mockSession)).thenReturn(true);
 
         mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
 
         mockMvc.perform(get("/admin/dashboard")
                 .session(mockSession))
                 .andExpect(status().isOk())
                 .andExpect(view().name("admin_templates/dashboard.html"))
                 .andExpect(model().attributeExists("title"));
    }

    @Test
    public void testGetProducts() throws Exception {
        // Simulating a logged-in admin user
        User adminUser = new User("Admin", "admin@example.com", "adminPassword", "Admin", "Admin Address", true);
        
        // Create a MockHttpSession
        MockHttpSession mockSession = new MockHttpSession();
        mockSession.setAttribute("user", adminUser);


        // Mock itemRepository to return a list of products
        when(itemRepository.findAll()).thenReturn(Arrays.asList(new Item()));

        mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();

        mockMvc.perform(get("/admin/products")
                .session(mockSession))
                .andExpect(status().isOk())
                .andExpect(view().name("admin_templates/products"))
                .andExpect(model().attributeExists("products"))
                .andExpect(model().attributeExists("title"));
    }

    @Test
    public void testGetUsers() throws Exception {
        // Simulating a logged-in admin user
        User adminUser = new User("Admin", "admin@example.com", "adminPassword", "Admin", "Admin Address", true);
        
        // Create a MockHttpSession
        MockHttpSession mockSession = new MockHttpSession();
        mockSession.setAttribute("user", adminUser);

        // Mock userRepository to return a list of users
        when(userRepository.findAll()).thenReturn(Arrays.asList(new User()));

        mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();

        mockMvc.perform(get("/admin/users")
                .session(mockSession))
                .andExpect(status().isOk())
                .andExpect(view().name("admin_templates/users.html"))
                .andExpect(model().attributeExists("users"))
                .andExpect(model().attributeExists("title"));
    }

    @Test
    public void testToggleAdmin() throws Exception {
        Long userId = 1L;
        doNothing().when(userService).toggleAdmin(userId);

        mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();

        mockMvc.perform(get("/admin/users/toggle_admin/{id}", userId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/users"));
    }

    @Test
    public void testToggleAdmintoUSer() throws Exception {
        Long userId = 1L;

        // Simulating a logged-in admin user
        User adminUser = new User("Admin", "admin@example.com", "adminPassword", "Admin", "Admin Address", true);
        MockHttpSession mockSession = new MockHttpSession();
        mockSession.setAttribute("user", adminUser);

        // Mock userService to perform toggleAdmin action
        doAnswer(invocation -> {
            Long id = invocation.getArgument(0);
            // Simulate toggling admin status
            adminUser.setUser_isAdmin(!adminUser.getUser_isAdmin()); // Toggles admin status
            return null;
        }).when(userService).toggleAdmin(userId);

        mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();

        mockMvc.perform(get("/admin/users/toggle_admin/{id}", userId)
                .session(mockSession))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/users"));
    }

    @Test
    public void testGetOrders() throws Exception {
        // Simulating a logged-in admin user
        User adminUser = new User("Admin", "admin@example.com", "adminPassword", "Admin", "Admin Address", true);
        MockHttpSession mockSession = new MockHttpSession();
        mockSession.setAttribute("user", adminUser);

        // Mock userService to return true when valid credentials are provided

        mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();

        mockMvc.perform(get("/admin/orders")
                .session(mockSession))
                .andExpect(status().isOk())
                .andExpect(view().name("admin_templates/orders.html"))
                .andExpect(model().attributeExists("title"));
    }

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
    }

  @Test
public void testAddItem() throws Exception {
    // Simulating a logged-in admin user
    User adminUser = new User("Admin", "admin@example.com", "adminPassword", "Admin", "Admin Address", true);
    MockHttpSession mockSession = new MockHttpSession();
    mockSession.setAttribute("user", adminUser);

    ItemDTO newItemDTO = new ItemDTO();
    newItemDTO.setProductName("New Item");
    newItemDTO.setCategory("Category");
    newItemDTO.setBrand("Brand");
    newItemDTO.setPrice(100.0);
    newItemDTO.setDescription("Description");
    newItemDTO.setQuantity(10);
    newItemDTO.setOffers(5.0);

    List<String> uploadedImagePaths = new ArrayList<>();
        uploadedImagePaths.add("image1.jpg");
        uploadedImagePaths.add("image2.jpg");

       mockMvc.perform(MockMvcRequestBuilders.post("/admin/createProduct")
                .param("uploadedImagePaths", "image1.jpg,image2.jpg")
                .flashAttr("itemDTO", newItemDTO)
                .session(mockSession))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/products"));

        
       verify(itemService, times(1)).createItem(itemDTOCaptor.capture(), eq(uploadedImagePaths));

       ItemDTO capturedItemDTO = itemDTOCaptor.getValue();

       // Perform assertions on capturedItemDTO if needed
       assertEquals(newItemDTO.getProductName(), capturedItemDTO.getProductName());
       assertEquals(newItemDTO.getCategory(), capturedItemDTO.getCategory());
       assertEquals(newItemDTO.getPrice(), capturedItemDTO.getPrice());
       assertEquals(newItemDTO.getDescription(), capturedItemDTO.getDescription());
       assertEquals(newItemDTO.getQuantity(), capturedItemDTO.getQuantity());
       assertEquals(newItemDTO.getOffers(), capturedItemDTO.getOffers());
    }



   @Test
public void testEditItem() throws Exception {
    // Simulating a logged-in admin user
    User adminUser = new User("Admin", "admin@example.com", "adminPassword", "Admin", "Admin Address", true);
    MockHttpSession mockSession = new MockHttpSession();
    mockSession.setAttribute("user", adminUser);

    // Mock itemService to perform editItem action
    ItemDTO itemDTO = new ItemDTO();
    itemDTO.setProductName("Updated Item");
    itemDTO.setCategory("Updated Category");
    itemDTO.setBrand("Updated Brand");
    itemDTO.setPrice(150.0);
    itemDTO.setDescription("Updated Description");
    itemDTO.setQuantity(20);
    itemDTO.setOffers(10.0);

    List<String> uploadedImagePaths = List.of("image1.jpg", "image2.jpg");

    // Setting up the expectations for the editItem method
    doNothing().when(itemService).editItem(anyLong(), any(ItemDTO.class), any(List.class));

    mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();

    // Performing the request
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/admin/editProduct")
            .param("productId", "61") // Assuming the product ID to be edited is 61
            .param("editUploadedImagePaths", "image1.jpg,image2.jpg") // Example image paths
            .flashAttr("itemDTO", itemDTO)
            .session(mockSession))
            .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
            .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/products"))
            .andReturn();

    // Log the response for debugging
    System.out.println("Response: " + result.getResponse().getContentAsString());

    // Verify that itemService.editItem was called with the correct arguments
    ArgumentCaptor<Long> productIdCaptor = ArgumentCaptor.forClass(Long.class);
    ArgumentCaptor<ItemDTO> itemDTOCaptor = ArgumentCaptor.forClass(ItemDTO.class);
    ArgumentCaptor<List> uploadedImagePathsCaptor = ArgumentCaptor.forClass(List.class);

    verify(itemService, times(1)).editItem(productIdCaptor.capture(), itemDTOCaptor.capture(), uploadedImagePathsCaptor.capture());

    // Retrieve captured values
    Long capturedProductId = productIdCaptor.getValue();
    ItemDTO capturedItemDTO = itemDTOCaptor.getValue();
    List<String> capturedUploadedImagePaths = uploadedImagePathsCaptor.getValue();

    // Perform assertions on captured values
    assertEquals(61L, capturedProductId.longValue()); // Assuming the product ID is 61
    assertEquals(itemDTO.getProductName(), capturedItemDTO.getProductName());
    assertEquals(itemDTO.getCategory(), capturedItemDTO.getCategory());
    assertEquals(itemDTO.getBrand(), capturedItemDTO.getBrand());
    assertEquals(itemDTO.getPrice(), capturedItemDTO.getPrice());
    assertEquals(itemDTO.getDescription(), capturedItemDTO.getDescription());
    assertEquals(itemDTO.getQuantity(), capturedItemDTO.getQuantity());
    assertEquals(itemDTO.getOffers(), capturedItemDTO.getOffers());

    //  assert capturedUploadedPaths if necessary
    assertEquals(2, capturedUploadedImagePaths.size());
    assertTrue(capturedUploadedImagePaths.contains("image1.jpg"));
    assertTrue(capturedUploadedImagePaths.contains("image2.jpg"));
}


@Test
public void testDeleteItem() throws Exception {
    Long itemId = 1L;

    doNothing().when(itemService).deleteItem(itemId);

    mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();

    mockMvc.perform(get("/admin/deleteProduct/{id}", itemId))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/admin/products"));

    // Verify that the itemService.deleteItem() method is called with the correct item ID
    verify(itemService, times(1)).deleteItem(itemId);
}

// @Test
// public void testUpdateUser() {
//     UserDTO userDTO = new UserDTO();
//     userDTO.setUserId(1L);
//     userDTO.setEmail("userx@example.com");

//     User adminUser = new User("Admin", "admin@example.com", "adminPassword", "Admin", "Admin Address", true);
//     MockHttpSession mockSession = new MockHttpSession();
//     mockSession.setAttribute("user", adminUser);

//     when(restTemplate.exchange(anyString(), eq(HttpMethod.PUT), any(HttpEntity.class), eq(Void.class)))
//                 .thenReturn(null);
   
//                 try {
//                     mockMvc.perform(MockMvcRequestBuilders.post("/admin/updateUser")
//                     .param("userId", String.valueOf(userDTO.getUserId()))
//                     .flashAttr("userDTO", userDTO))
//                     .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
//                     .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/users"));
//                 } catch (Exception e) {
//                     e.printStackTrace();
//                 }

//         // Verify that userService.updateUser was called with the correct arguments
//         ArgumentCaptor<UserDTO> userDTOCaptor = ArgumentCaptor.forClass(UserDTO.class);
//         verify(userService, times(1)).updateUser(userDTOCaptor.capture());

//         // Retrieve captured UserDTO
//         UserDTO capturedUserDTO = userDTOCaptor.getValue();

//         // Perform assertions on capturedUserDTO
//         assertEquals(userDTO.getUserId(), capturedUserDTO.getUserId());
//         assertEquals(userDTO.getEmail(), capturedUserDTO.getEmail());
//         assertEquals(userDTO.getUserFname(), capturedUserDTO.getUserFname());
//         assertEquals(userDTO.getUserAddress(), capturedUserDTO.getUserAddress());
      

// }

// @Test
// public void testDeleteUser() throws Exception {
//     // Arrange
//     Long userId = 111L;
//     String deleteUrl = "http://localhost:8081/User/" + userId;

//     // Mock userRepository behavior to return a User when findById is called
//     User userToDelete = new User("hesham", "zeinaa@gmail.com", "123456789", "Zeina","fdx", false);
//     when(userRepository.findById(userId)).thenReturn(Optional.of(userToDelete));

//     // Mock restTemplate behavior to return a ResponseEntity<Void>
//     when(restTemplate.exchange(
//             eq(deleteUrl),
//             eq(HttpMethod.DELETE),
//             any(HttpEntity.class),
//             eq(Void.class)
//     )).thenReturn(new ResponseEntity<>(HttpStatus.NO_CONTENT));

//     // Act
//     mockMvc.perform(MockMvcRequestBuilders.delete("/admin/deleteUser/{userId}", userId))
//             .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
//             .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/users"));

//     // Verify
//     verify(restTemplate, times(1)).exchange(
//             eq(deleteUrl),
//             eq(HttpMethod.DELETE),
//             any(HttpEntity.class),
//             eq(Void.class)
//     );
// }

// @Test
// public void testUpdateUser() {
//     UserDTO userDTO = new UserDTO();
//     userDTO.setUserId(1L);
//     userDTO.setEmail("userx@example.com");

//     User adminUser = new User("Admin", "admin@example.com", "adminPassword", "Admin", "Admin Address", true);
//     MockHttpSession mockSession = new MockHttpSession();
//     mockSession.setAttribute("user", adminUser);

//     when(restTemplate.exchange(anyString(), eq(HttpMethod.PUT), any(HttpEntity.class), eq(Void.class)))
//                 .thenReturn(null);
   
//                 try {
//                     mockMvc.perform(MockMvcRequestBuilders.post("/admin/updateUser")
//                     .param("userId", String.valueOf(userDTO.getUserId()))
//                     .flashAttr("userDTO", userDTO))
//                     .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
//                     .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/users"));
//                 } catch (Exception e) {
//                     e.printStackTrace();
//                 }

//         // Verify that userService.updateUser was called with the correct arguments
//         ArgumentCaptor<UserDTO> userDTOCaptor = ArgumentCaptor.forClass(UserDTO.class);
//         verify(userService, times(1)).updateUser(userDTOCaptor.capture());

//         // Retrieve captured UserDTO
//         UserDTO capturedUserDTO = userDTOCaptor.getValue();

//         // Perform assertions on capturedUserDTO
//         assertEquals(userDTO.getUserId(), capturedUserDTO.getUserId());
//         assertEquals(userDTO.getEmail(), capturedUserDTO.getEmail());
//         assertEquals(userDTO.getUserFname(), capturedUserDTO.getUserFname());
//         assertEquals(userDTO.getUserAddress(), capturedUserDTO.getUserAddress());
      

// }

@Test
public void testDeleteUser() throws Exception {
    // Given
    User adminUser = new User("Admin", "admin@example.com", "adminPassword", "Admin", "Admin Address", true);
    MockHttpSession mockSession = new MockHttpSession();
    mockSession.setAttribute("user", adminUser);
    Long userId = 1L;

    // Mocking service method to verify if it's called
    doNothing().when(userService).deleteUser(userId);

    // When
    String mav = adminController.deleteUser(userId);

    // Then
    verify(userService, times(1)).deleteUser(userId);
    assertEquals("redirect:/admin/users", mav);
}

// @Test
// public void testUpdateUser() throws Exception {
//     // Mock admin user session
//     User adminUser = new User("Admin", "admin@example.com", "adminPassword", "Admin", "Admin Address", true);
//     MockHttpSession mockSession = new MockHttpSession();
//     mockSession.setAttribute("user", adminUser);

//     // User data to update
//     Long userId = 1L;
//     User userToUpdate = new User("UserToUpdate", "updateuser@example.com", "updatePassword", "Update", "User Address", false);
//     UserDTO userDTO = new UserDTO();
//     userDTO.setUserId(userId);
//     userDTO.setUserFname("Updated First Name");
//     userDTO.setUserLname("Updated Last Name");
//     userDTO.setEmail("updateduser@example.com");
//     userDTO.setUserAddress("Updated Address");

//     // Mock userRepository behavior to return a User when findById is called
//     when(userRepository.findById(userId)).thenReturn(Optional.of(userToUpdate));

//     // Perform the PUT request and assert the response
//     mockMvc.perform(put("/User/{id}", userId)
//             .session(mockSession)
//             .contentType(MediaType.APPLICATION_JSON)
//             .content(asJsonString(userDTO)));
            

//     // Verify that the userRepository save method was called with the expected user
//     ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
//     verify(userRepository).save(userCaptor.capture());
//     User savedUser = userCaptor.getValue();
//     assertEquals("Updated First Name", savedUser.getUser_fname());
//     assertEquals("Updated Last Name", savedUser.getUser_Lname());
//     assertEquals("updateduser@example.com", savedUser.getEmail());
//     assertEquals("Updated Address", savedUser.getUser_address());
// }

// // Utility method to convert objects to JSON string
// private static String asJsonString(final Object obj) {
//     try {
//         return new ObjectMapper().writeValueAsString(obj);
//     } catch (Exception e) {
//         throw new RuntimeException(e);
//     }
// }

}
