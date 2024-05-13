package com.example.demo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.http.ResponseEntity;
import jakarta.servlet.http.HttpSession;
import com.example.demo.services.UserService;
import com.example.demo.controllers.AuthenticationController;
import com.example.demo.dto.UserDTO;
import org.springframework.validation.FieldError;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private AuthenticationController authenticationController;

    @Test
    public void testLogin() {
        AuthenticationController controller = new AuthenticationController();
        Model model = new ExtendedModelMap();
        UserDTO userDTO = new UserDTO();
        assertEquals("login.html", controller.login(userDTO, model).getViewName());
    }
    
    @Test
    public void testLogin_ValidUser_ReturnsRedirect() {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("validemail@example.com");
        userDTO.setUserPassword("validpassword");

        BindingResult bindingResult = mock(BindingResult.class);
        HttpSession session = mock(HttpSession.class);

        when(userService.getUser("validemail@example.com", "validpassword", session)).thenReturn(true);

        ModelAndView mav = authenticationController.getUser(userDTO, bindingResult, session);

        assert mav.getViewName().equals("redirect:/");
    }
    @Test
    public void testLogin_InvalidUser_ReturnsError() {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("invalidemail@example.com");
        userDTO.setUserPassword("invalidpassword");

        BindingResult bindingResult = new BeanPropertyBindingResult(userDTO, "userDTO");
        HttpSession session = mock(HttpSession.class);

        when(userService.getUser("invalidemail@example.com", "invalidpassword", session)).thenReturn(false);

        ModelAndView mav = authenticationController.getUser(userDTO, bindingResult, session);

        assert mav.getViewName().equals("login.html");
        assert bindingResult.hasErrors();

        if (bindingResult.hasErrors()) {
            System.out.println("Errors found in BindingResult:");
            for (FieldError error : bindingResult.getFieldErrors()) {
                System.out.println("Field: " + error.getField() + ", Message: " + error.getDefaultMessage());
            }
        }
        else {
            System.out.println("No errors found in BindingResult");
        }
 
    }

   @Test
public void testSignup_ValidUser_ReturnsRedirect() {
    UserDTO userDTO = new UserDTO();
    userDTO.setEmail("newuser@example.com");
    userDTO.setUserPassword("validpassword");
    userDTO.setCpassword("validpassword");

    BindingResult bindingResult = mock(BindingResult.class);

    // Mocking UserService to simulate that email does not exist
    when(userService.existEmail("newuser@example.com")).thenReturn(false);

    // Invoke the controller method
    ModelAndView mav = authenticationController.signup(userDTO, bindingResult);

    // Ensure that there are no validation errors
    assertFalse(bindingResult.hasErrors());

    // Ensure that the returned ModelAndView redirects to the login page
    assertEquals("redirect:/auth/login", mav.getViewName());
}

@Test
public void testSignup_RequiredFieldsMissing_ReturnsError() { 
    UserDTO userDTO = new UserDTO();

    userDTO.setUserPassword("longpassword"); // Password length greater than 8 characters
    userDTO.setCpassword("longpassword"); // Password and confirm password match
  
    //userDTO.userAddress("provided"); //ana 3mla fel controllor en lazm yba fe address and password to match and >8 low kol da i set 
    //ybaa el test hy fail ashan howa by check low fe errors wla la so there wont be any errors 

    BindingResult bindingResult = new BeanPropertyBindingResult(userDTO, "userDTO");

    ModelAndView mav = authenticationController.signup(userDTO, bindingResult);

    // Ensure that there are validation errors
    assertTrue(bindingResult.hasErrors());

    // Ensure that the returned ModelAndView object stays on the signup page
    assertEquals("signup.html", mav.getViewName());
}

}
